package com.wmktefaternak.inkubatorapp.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wmktefaternak.inkubatorapp.uistate.ControlUiState
import com.wmktefaternak.inkubatorapp.uistate.DeviceCondision
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ControlViewModel : ViewModel() {

    private val databaseReference = Firebase.database.reference
        .child("InkubatorAppID2023v1").child("controling")

    private val _condision = MutableStateFlow(DeviceCondision())
    val condision = _condision.asStateFlow()

    private val _state = MutableStateFlow(ControlUiState())
    val kipas = _state.asStateFlow()

    fun kipasDevice(isOn: Boolean) {
        if (isOn) {
            controlKipasOn()
        } else {
            controlKipasOff()
        }
    }

    fun heaterDevice(isOn: Boolean) {
        if (isOn) {
            controlHeaterOn()
        } else {
            controlHeaterOff()
        }
    }

    fun motorDevice(isOn: Boolean) {
        if (isOn) {
            controlMotorOn()
        } else {
            controlMotorOff()
        }
    }
    private fun controlKipasOn() {
        _state.update {
            it.copy(
                kipas = "1",
                isFanOn = true
            )
        }
        databaseReference.child("kipas").setValue(1)
    }

    private fun controlKipasOff() {
        _state.update {
            it.copy(
                kipas = "0",
                isFanOn = false
            )
        }
        databaseReference.child("kipas").setValue(0)
    }

    private fun controlHeaterOn() {
        _state.update {
            it.copy(
                kipas = "1",
                isHeaterOn = true
            )
        }
        databaseReference.child("heater").setValue(1)
    }

    private fun controlHeaterOff() {
        _state.update {
            it.copy(
                kipas = "0",
                isHeaterOn = false
            )
        }
        databaseReference.child("heater").setValue(0)
    }

    private fun controlMotorOn() {
        _state.update {
            it.copy(
                kipas = "1",
                isMotorOn = true
            )
        }
        databaseReference.child("motor").setValue(1)
    }

    private fun controlMotorOff() {
        _state.update {
            it.copy(
                kipas = "0",
                isMotorOn = false
            )
        }
        databaseReference.child("motor").setValue(0)
    }

    private fun getDataDControlFromFirebase() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val getDataCondision = snapshot.getValue<DeviceCondision?>()
                _condision.update { it.copy(
                    kipas = getDataCondision?.kipas,
                    heater = getDataCondision?.heater,
                    motor = getDataCondision?.motor
                ) }
            }

            override fun onCancelled(error: DatabaseError) {
                _condision.update { it.copy(
                    kipas = 0,
                    heater = 0,
                    motor = 0
                ) }
            }

        })
    }
    init {
        getDataDControlFromFirebase()
    }


}

