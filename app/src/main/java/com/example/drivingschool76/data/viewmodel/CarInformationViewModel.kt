package com.example.drivingschool76.data.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.drivingschool76.data.network.SupabaseClient.client
import com.example.drivingschool76.utils.AuthTokenManager
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import io.ktor.http.headersOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarInformationViewModel(application: Application) : AndroidViewModel(application) {
    val carBrand = MutableLiveData("")
    val carModel = MutableLiveData("")
    val letter1 = MutableLiveData("")
    val numbers = MutableLiveData("")
    val letter2 = MutableLiveData("")
    val regionPart = MutableLiveData("")

    private val _uiState = MutableLiveData<UIState>()
    val uiState: LiveData<UIState> = _uiState

    val isButtonEnabled: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().also { mediator ->
        listOf(carBrand, carModel, letter1, numbers, letter2, regionPart).forEach { field ->
            mediator.addSource(field) { mediator.value = validateInputs() }
        }
    }

    private val _showDialog = MutableLiveData(false)
    val showDialog: LiveData<Boolean> = _showDialog

    private val authTokenManager = AuthTokenManager(application.applicationContext)

    private val _uploadStatuses = MutableLiveData<List<String?>>()

    fun toggleDialog(show: Boolean) {
        _showDialog.value = show
    }

    private fun validateInputs(): Boolean {
        val isBrandValid = !carBrand.value.isNullOrEmpty()
        val isModelValid = !carModel.value.isNullOrEmpty()
        val isLetter1Valid = letter1.value?.length == 1
        val isNumbersValid = numbers.value?.length == 3
        val isLetter2Valid = letter2.value?.length == 2
        val isRegionValid = regionPart.value?.length == 2 || regionPart.value?.length == 3

        val isValid = isBrandValid && isModelValid && isLetter1Valid && isNumbersValid && isLetter2Valid && isRegionValid

        _uiState.value = UIState(
            brandError = !isBrandValid,
            modelError = !isModelValid,
            letter1Error = !isLetter1Valid,
            numbersError = !isNumbersValid,
            letter2Error = !isLetter2Valid,
            regionError = !(isRegionValid)
        )

        return isValid
    }

    private fun uploadImageToSupabase(uri: Uri?, context: Context): LiveData<String?> {
        val uploadStatus = MutableLiveData<String?>()

        if (uri != null) {
            viewModelScope.launch {
                try {
                    val inputStream = context.contentResolver.openInputStream(uri)
                    val byteArray = inputStream?.readBytes()
                    val fileName = "car_images/${System.currentTimeMillis()}.jpg"

                    if (byteArray != null) {
                        val result = client.storage.from("instruckor-cars")
                            .upload(fileName, byteArray)

                        uploadStatus.postValue(result)
                    } else {
                        uploadStatus.postValue("Error: InputStream is null")
                    }
                } catch (e: Exception) {
                    uploadStatus.postValue("Exception: ${e.message}")
                }
            }
        } else {
            uploadStatus.postValue(null)
        }

        return uploadStatus
    }

    private fun uploadImages(imageUris: List<Uri>, context: Context) {
        viewModelScope.launch {
            val statuses = imageUris.map { uri ->
                uploadImageToSupabase(uri, context).value
            }
            _uploadStatuses.postValue(statuses)
        }
    }

    fun collectDataAndLog(imageUris: List<Uri>, context: Context) {

        uploadImages(imageUris, context)
        val data = "Марка: ${carBrand.value}, Модель: ${carModel.value}, Номер: ${letter1.value}${numbers.value}${letter2.value} ${regionPart.value}"
        Log.d("Confirmation", data)

    }

    fun updateCarInformation() {
        val licensePlate = "${letter1.value}${numbers.value}${letter2.value} ${regionPart.value}"
        val uuid = authTokenManager.getUserUUID()
        val role = authTokenManager.getRole()

        if (uuid != null && role == "instructor") {
            viewModelScope.launch {
                try {
                    val token = authTokenManager.getToken()
                    client.postgrest["public","InstructorCars"].update(
                        mapOf(
                            "make" to carBrand.value,
                            "model" to carModel.value,
                            "licensePlate" to licensePlate
                        )
                    ) {
                        headersOf("Authorization", "Bearer $token")
                        filter {
                            eq("id", uuid)
                        }
                    }

                    Log.d("UpdateCar", "Car updated successfully for UUID: $uuid")
                } catch (e: Exception) {
                    Log.e("UpdateCar", "Exception during car update: ${e.printStackTrace()}")
                }
            }
        } else {
            Log.e("UpdateCar", "UUID is null or role is not instructor, cannot update car information")
        }
    }

    @SuppressLint("Range")
    fun getImageDetails(context: Context, imageUri: Uri): LiveData<Pair<String, Long>> {
        val imageDetails = MutableLiveData<Pair<String, Long>>()

        viewModelScope.launch(Dispatchers.IO) {
            context.contentResolver.query(imageUri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val name = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    val size = cursor.getLong(cursor.getColumnIndex(OpenableColumns.SIZE))
                    imageDetails.postValue(Pair(name, size))
                }
            }
        }

        return imageDetails
    }
}

data class UIState(
    val brandError: Boolean = false,
    val modelError: Boolean = false,
    val letter1Error: Boolean = false,
    val numbersError: Boolean = false,
    val letter2Error: Boolean = false,
    val regionError: Boolean = false
)



