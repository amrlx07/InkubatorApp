package com.wmktefaternak.inkubatorapp.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wmktefaternak.inkubatorapp.uistate.MonitorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlin.math.roundToInt

class MonitorViewModel: ViewModel() {

    private val _state = MutableStateFlow(MonitorUiState())
    val state = _state.asStateFlow()

    fun roundup(formatted: Double?): Double {
        val roundup = formatted!! * 100.0
        return roundup.roundToInt() / 100.0
    }

    private fun getDataFromFirebase() {
        val databaseReference = Firebase.database.reference
            .child("InkubatorAppID2023v1").child("monitoring").child("data")

        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val getData = snapshot.getValue<MonitorUiState?>()
                 _state.update { it.copy(
                     temperature = getData?.temperature,
                     humidity = getData?.humidity
                 ) }
            }

            override fun onCancelled(error: DatabaseError) {
                _state.update { it.copy(
                    temperature = 0.0,
                    humidity = 0.0
                ) }
            }
        })
    }



    init {
        getDataFromFirebase()
    }

}