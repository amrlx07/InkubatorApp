package com.wmktefaternak.inkubatorapp.utils.use_case.domain

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
