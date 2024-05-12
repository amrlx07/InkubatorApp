package com.wmktefaternak.inkubatorapp.utils.use_case.domain

class ValidationTotalInvetil {
    fun execute(countInvertil: String): ValidationResult {
        if (countInvertil.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Belum terisi"
            )
        }
        return ValidationResult(successful = true)
    }
}