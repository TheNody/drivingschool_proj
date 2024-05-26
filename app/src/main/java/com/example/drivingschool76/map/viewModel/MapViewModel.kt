package com.example.drivingschool76.map.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.drivingschool76.R
import com.example.drivingschool76.map.contentManager.ContentManager
import com.example.drivingschool76.map.data.PointsData
import com.example.drivingschool76.map.model.PlacemarkContent
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.runtime.image.ImageProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MapViewModel : ViewModel() {
    private val contentManager = ContentManager()

    private val _selectedContent = MutableStateFlow<PlacemarkContent?>(null)
    val selectedContent: StateFlow<PlacemarkContent?> = _selectedContent.asStateFlow()

    private val _selectedPoint = MutableStateFlow<Point?>(null)

    private val _showSheet = MutableStateFlow(false)
    val showSheet: StateFlow<Boolean> = _showSheet.asStateFlow()

    private fun onPlacemarkClicked(id: Int, point: Point) {
        val pointWithID = PointsData.points.find { it.id == id }
        pointWithID?.let {
            _selectedPoint.value = it.point
            _selectedContent.value = contentManager.getContentById(id)
            _showSheet.value = true
            Log.d("MapViewModel", "Placemark with ID $id tapped at latitude: ${it.point.latitude}, longitude: ${it.point.longitude}")
        } ?: run {
            _selectedContent.value = null
            _showSheet.value = false
            Log.d("MapViewModel", "No content found for ID: $id")
        }
    }

    fun onHideBottomSheet() {
        _showSheet.value = false
        _selectedContent.value = null
        _selectedPoint.value = null
    }

    fun setupPlacemarks(mapObjects: MapObjectCollection, context: Context) {
        PointsData.points.forEach { pointWithID ->
            val placemark = mapObjects.addPlacemark(pointWithID.point)
            placemark.setIcon(ImageProvider.fromResource(context, R.drawable.car_icon_for_map))
            placemark.addTapListener { _, _ ->
                onPlacemarkClicked(pointWithID.id, pointWithID.point)
                true
            }
        }
    }
}