package com.wmktefaternak.inkubatorapp.utils.use_case.domain

class ValidationTotalVertil {
    fun execute(countVertil: String): ValidationResult {
        if (countVertil.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Belum terisi"
            )
        }
        return ValidationResult(successful = true)
    }
}