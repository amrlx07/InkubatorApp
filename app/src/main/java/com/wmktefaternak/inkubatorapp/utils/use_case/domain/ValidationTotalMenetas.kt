package com.wmktefaternak.inkubatorapp.utils.use_case.domain

class ValidationTotalMenetas {
    fun execute(countMenetas: String): ValidationResult {
        if (countMenetas.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Belum terisi"
            )
        }
        return ValidationResult(successful = true)
    }
}