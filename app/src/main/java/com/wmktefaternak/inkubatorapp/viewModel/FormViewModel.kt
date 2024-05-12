package com.wmktefaternak.inkubatorapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.wmktefaternak.inkubatorapp.uistate.Form1
import com.wmktefaternak.inkubatorapp.uistate.Form2
import com.wmktefaternak.inkubatorapp.uistate.Form3
import com.wmktefaternak.inkubatorapp.uistate.FormDate
import com.wmktefaternak.inkubatorapp.uistate.FormEventCountTelur
import com.wmktefaternak.inkubatorapp.uistate.FormEventDate
import com.wmktefaternak.inkubatorapp.uistate.FormEventMenetasTelur
import com.wmktefaternak.inkubatorapp.uistate.FormEventVertilInvertilTelur
import com.wmktefaternak.inkubatorapp.utils.use_case.domain.ValidateDate
import com.wmktefaternak.inkubatorapp.utils.use_case.domain.ValidationTotalCount
import com.wmktefaternak.inkubatorapp.utils.use_case.domain.ValidationTotalGagalMenetas
import com.wmktefaternak.inkubatorapp.utils.use_case.domain.ValidationTotalInvetil
import com.wmktefaternak.inkubatorapp.utils.use_case.domain.ValidationTotalMenetas
import com.wmktefaternak.inkubatorapp.utils.use_case.domain.ValidationTotalVertil
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class FormViewModel(
    private val validateTanggal: ValidateDate = ValidateDate(),
    private val validateCount: ValidationTotalCount = ValidationTotalCount(),
    private val validateVertil: ValidationTotalVertil = ValidationTotalVertil(),
    private val validateInvertil: ValidationTotalInvetil = ValidationTotalInvetil(),
    private val validateMenetas: ValidationTotalMenetas = ValidationTotalMenetas(),
    private val validateGagalMenetas: ValidationTotalGagalMenetas = ValidationTotalGagalMenetas()
) : ViewModel() {
    var stateForm1 by mutableStateOf(Form1())
    var stateForm2 by mutableStateOf(Form2())
    var stateForm3 by mutableStateOf(Form3())
    var stateDateForm by mutableStateOf(FormDate())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    //private val _stateForm1 = MutableStateFlow(Form1())
    //val state = _stateForm1.asStateFlow()

    fun onEventDate(event: FormEventDate) {
        when (event) {
            is FormEventDate.TanggalChanged ->
                stateDateForm = stateDateForm.copy(tanggal = event.tanggal)
            is FormEventDate.Submit ->
                submitDataFormDate()
        }
    }

    fun onEventScreen1(event: FormEventCountTelur) {
        when (event) {

            is FormEventCountTelur.CountChanged ->
                stateForm1 = stateForm1.copy(jumlah = event.jumlah)
            is FormEventCountTelur.Submit ->
                submitDataForm1()

        }
    }
    fun onEventScreen2(event: FormEventVertilInvertilTelur) {
        when (event) {

            is FormEventVertilInvertilTelur.InvertilChanged ->
                stateForm2 = stateForm2.copy(invertil = event.invertil)
            is FormEventVertilInvertilTelur.VertilChanged ->
                stateForm2 = stateForm2.copy(vertil = event.vertil)
            is FormEventVertilInvertilTelur.Submit ->
                submitDataForm2()
        }
    }

    fun onEventScreen3(event: FormEventMenetasTelur) {
        when (event) {
            is FormEventMenetasTelur.GagalMenetasChanged ->
                stateForm3 = stateForm3.copy(gagalMenetas = event.gagalMenetas)
            is FormEventMenetasTelur.MenetasChanged ->
                stateForm3 = stateForm3.copy(menetas = event.menetas)
            is FormEventMenetasTelur.Submit ->
                submitDataForm3()
        }
    }

    fun sendForm1 (userData: Form1, date: String) {
        val user = FirebaseAuth.getInstance().currentUser
        val sendData = Form1(jumlah = userData.jumlah)
        user?.run {
            val userIdReference = Firebase.database.reference
                .child("UsersData").child(user.uid).child("Form telur masuk").child(date)

            userIdReference.setValue(sendData)
        }

    }
    fun sendForm2 (userData: Form2, date: String) {
        val user = FirebaseAuth.getInstance().currentUser

        val sendData = Form2(vertil = userData.vertil, invertil = userData.invertil)
        user?.run {
            val userIdReference =Firebase.database.reference
                .child("UsersData").child(user.uid).child("Form vertil atau invertil telur").child(date)

            userIdReference.setValue(sendData)
        }
    }
    fun sendForm3 (userData: Form3, date: String) {
        val user = FirebaseAuth.getInstance().currentUser

        val sendData= Form3(menetas = userData.menetas, gagalMenetas = userData.gagalMenetas)
        user?.run {
            val userIdReference =Firebase.database.reference
                .child("UsersData").child(user.uid).child("Form menetas atau gagal menetes telur").child(date)

            userIdReference.setValue(sendData)
        }
    }

    private fun submitDataForm1() {

        val countResult = validateCount.execute(stateForm1.jumlah)

        val hasError = listOf(

            countResult
        ).any{!it.successful}

        if (hasError) {
            stateForm1 = stateForm1.copy(
                jumlahError = countResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    private fun submitDataForm2() {

        val vertilResult = validateVertil.execute(stateForm2.vertil)
        val invertilResult = validateInvertil.execute(stateForm2.invertil)

        val hasError = listOf(

            vertilResult,
            invertilResult
        ).any{!it.successful}

        if (hasError) {
            stateForm2 = stateForm2.copy(

                vertilError = vertilResult.errorMessage,
                invertilError = vertilResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    private fun submitDataForm3() {

        val menetasResult = validateMenetas.execute(stateForm3.menetas)
        val gagalMenetas = validateGagalMenetas.execute(stateForm3.gagalMenetas)

        val hasError = listOf(
            menetasResult,
            gagalMenetas
        ).any{!it.successful}

        if (hasError) {
            stateForm3 = stateForm3.copy(

                menetasError = menetasResult.errorMessage,
                gagalMenetasError = gagalMenetas.errorMessage
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    private fun submitDataFormDate() {
        val tanggalResult = validateTanggal.execute(stateDateForm.tanggal)

        val hasError = listOf(
            tanggalResult,
        ).any{!it.successful}

        if (hasError) {
            stateDateForm= stateDateForm.copy(
                tanggalError = tanggalResult.errorMessage,
            )
            return
        }
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }
    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }



}




