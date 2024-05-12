package com.wmktefaternak.inkubatorapp.uistate

data class MonitorUiState(
    val temperature: Double? = 0.0,
    val humidity: Double? = 0.0
)
data class GetChildPath(
    val serialNumber: String? = ""
)
