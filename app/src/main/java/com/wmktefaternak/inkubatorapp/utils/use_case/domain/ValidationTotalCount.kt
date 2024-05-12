package com.wmktefaternak.inkubatorapp.utils.use_case.domain

class ValidationTotalCount {
    fun execute(count: String): ValidationResult {
        if (count.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Belum terisi"
            )
        }
        return ValidationResult(successful = true)
    }
}