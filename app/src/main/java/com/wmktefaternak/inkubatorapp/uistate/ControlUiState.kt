package com.wmktefaternak.inkubatorapp.uistate

data class ControlUiState(
    var kipas: String? = null,
    var isFanOn: Boolean = false,
    val heater: String? = null,
    val isHeaterOn: Boolean = false,
    val motor : String? = null,
    val isMotorOn: Boolean = false
)

data class DeviceCondision(
    val kipas: Int? = 0,
    val heater: Int? = 0,
    val motor: Int? = 0
)

