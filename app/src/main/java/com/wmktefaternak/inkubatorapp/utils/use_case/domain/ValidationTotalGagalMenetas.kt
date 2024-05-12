package com.wmktefaternak.inkubatorapp.utils.use_case.domain

class ValidationTotalGagalMenetas {
    fun execute(countGagalMenetas: String): ValidationResult {
        if (countGagalMenetas.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Belum terisi"
            )
        }
        return ValidationResult(successful = true)
    }
}