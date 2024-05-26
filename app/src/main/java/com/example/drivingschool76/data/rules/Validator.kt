package com.example.drivingschool76.data.rules

object Validator {


    fun validateFirstName(fName:String) :ValidationResult {
    return ValidationResult(
        (fName.isNotEmpty() && fName.length>=3)
        )
    }

    fun validateLastName(lName:String) :ValidationResult{
        return ValidationResult(
            (lName.isNotEmpty() && lName.length>=3)
        )
    }

    fun validateEmail(email:String) :ValidationResult{
        return ValidationResult(
            (email.isNotEmpty())
        )
    }

    fun validatePassword(password:String) :ValidationResult{
        return ValidationResult(
            (password.isNotEmpty() && password.length>=4)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue: Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )
    }

}

data class ValidationResult(
    val status: Boolean = false
)