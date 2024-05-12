package com.wmktefaternak.inkubatorapp.utils.use_case.domain

class ValidateDate {
    fun execute(tanggal: String): ValidationResult {
        if (tanggal.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Tanggal Belum Terisi"
            )
        }
        return ValidationResult(successful = true)
    }
}