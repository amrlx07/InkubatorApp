@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.wmktefaternak.inkubatorapp.screen

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.wmktefaternak.inkubatorapp.uistate.Form1
import com.wmktefaternak.inkubatorapp.uistate.Form2
import com.wmktefaternak.inkubatorapp.uistate.Form3
import com.wmktefaternak.inkubatorapp.uistate.FormEventCountTelur
import com.wmktefaternak.inkubatorapp.uistate.FormEventDate
import com.wmktefaternak.inkubatorapp.uistate.FormEventMenetasTelur
import com.wmktefaternak.inkubatorapp.uistate.FormEventVertilInvertilTelur
import com.wmktefaternak.inkubatorapp.viewModel.FormViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormScreen() {
    DropdownMenuWithForms()
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuWithForms() {
    var selectedForm by remember { mutableIntStateOf(0) }
    var isExpanded by remember { mutableStateOf(false) }
    var formulir by remember { mutableStateOf("Formulir Masuk Telur") }
    val forms = listOf("Formulir Masuk Telur", "Formulir Vertil/Invertil", "Formulir Hasil Penetasan")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp),
                modifier = Modifier

                    .width(350.dp)
                    .height(53.dp)
                    .background(color = Color(0xFFF7F2FA), shape = RoundedCornerShape(size = 14.dp))
            ) {
                Text(text = "Form Inkubator",
                    modifier = Modifier
                        .padding(top = 13.dp)
                        .align(Alignment.CenterHorizontally),
                    style = TextStyle(
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        //fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(400),

                        textAlign = TextAlign.Center,
                    )
                    )


            }
            Spacer(modifier = Modifier.height(15.dp))
            ElevatedCard(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp),
                modifier = Modifier
                    .width(350.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Silahkan Untuk Memilih Form :",
                    modifier = Modifier.padding(start = 38.dp, top = 16.dp))
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it })
                {
                    OutlinedTextField(
                        value = formulir,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .padding(start = 38.dp, top = 16.dp)
                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false })
                    {
                        forms.forEachIndexed { index, form ->
                            DropdownMenuItem(
                                text = { Text(text = form) },
                                onClick = {
                                    formulir = forms[index]
                                    selectedForm = index
                                    isExpanded = false
                                })
                        }
                    }

                }
                when (selectedForm) {
                    0 -> FormMasukTelur()
                    1 -> FormVertil()
                    2 -> FormPenetasan()
                }
            }


        }


    }




@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormMasukTelur() {
    val focusChange = remember { FocusRequester() }
    //var countEggIn by remember { mutableStateOf("") }
    var dateTime by remember { mutableStateOf("") }
    val viewModel: FormViewModel = viewModel()
    val state = viewModel.stateForm1
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when(event) {
                is FormViewModel.ValidationEvent.Success ->
                    Toast.makeText(
                        context,
                        "Data Terkirim",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
    Column(
        modifier = Modifier.padding(38.dp)
    ) {
        Text(text = "Form Masuk Telur")
         dateTime = datePickerTextField(
            focusRequest = focusChange

            )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = state.jumlah,
            onValueChange = { viewModel.onEventScreen1(FormEventCountTelur.CountChanged(it)) },
            label = { Text("Jumlah Telur Masuk") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            isError = state.jumlahError != null
        )
        if (state.jumlahError != null) {
            Text(text = state.jumlahError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        val userInput = Form1(jumlah = state.jumlah)
        Button(
            onClick = {
                viewModel.onEventScreen1(FormEventCountTelur.Submit).run {
                    viewModel.sendForm1(userData = userInput, date = dateTime)
                }
                 },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 15.dp)
        ) {
            Text(text = "kirim")
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormVertil() {
    var dateTime by remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel: FormViewModel = viewModel()
    val state = viewModel.stateForm2
    val focusChange = remember {
        FocusRequester()
    }
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when(event) {
                is FormViewModel.ValidationEvent.Success ->
                    Toast.makeText(
                        context,
                        "Data Terkirim",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
    Column(
        modifier = Modifier.padding(38.dp)
    ) {
        Text(text = "Form Vertil/Invertil")
        dateTime = datePickerTextField(
            focusRequest = focusChange)

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = state.vertil,
            onValueChange = { viewModel.onEventScreen2(FormEventVertilInvertilTelur.VertilChanged(it)) },
            isError = state.vertilError != null,
            label = { Text("Jumlah Telur vertil") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        if (state.vertilError  != null) {
            Text(
                text = state.vertilError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            isError = state.invertilError != null,
            value = state.invertil,
            onValueChange = { viewModel.onEventScreen2(FormEventVertilInvertilTelur.InvertilChanged(it)) },
            label = { Text("Jumlah Telur Invertil") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )
        if (state.invertilError  != null) {
            Text(
                text = state.invertilError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        val userInput = Form2( vertil = state.vertil, invertil = state.invertil)
        Button(
            onClick = {
                viewModel.onEventScreen2(FormEventVertilInvertilTelur.Submit).run {
                    viewModel.sendForm2(userData = userInput, date = dateTime)
                }

                      },
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 15.dp)
        ) {
            Text(text = "kirim")
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormPenetasan() {
    var dateTime by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel: FormViewModel = viewModel()
    val state = viewModel.stateForm3
    val focusChange = remember {
        FocusRequester()
    }
    //var crackEnEgg by remember { mutableStateOf("") }
    //var failCrackEgg by remember { mutableStateOf("") }
    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when(event) {
                is FormViewModel.ValidationEvent.Success ->
                    Toast.makeText(
                        context,
                        "Data Terkirim",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
    Column(
        modifier = Modifier.padding(38.dp)
    ) {
        Text(text = "Form Hasil Penetasan")
         dateTime = datePickerTextField(
            focusRequest = focusChange,

         )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            isError = state.menetasError != null,
            value = state.menetas,
            onValueChange = { viewModel.onEventScreen3(FormEventMenetasTelur.MenetasChanged(it)) },
            label = { Text("Jumlah Menetas") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        if (state.menetasError != null) {
            Text(
                text = state.menetasError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            isError = state.gagalMenetasError != null,
            value = state.gagalMenetas,
            onValueChange = { viewModel.onEventScreen3(FormEventMenetasTelur.GagalMenetasChanged(it)) },
            label = { Text("Jumlah Gagal Menetas") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
        if (state.gagalMenetasError != null) {
            Text(
                text = state.gagalMenetasError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
        val userInput = Form3( menetas = state.menetas, gagalMenetas = state.gagalMenetas)
            Button(
                onClick = {
                    viewModel.onEventScreen3(FormEventMenetasTelur.Submit).run {
                        viewModel.sendForm3(userData = userInput, date = dateTime)
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 15.dp)
            ) {
                Text(text = "kirim")
            }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun datePickerTextField(focusRequest: FocusRequester): String {

    val viewModel: FormViewModel = viewModel()
    val state = viewModel.stateDateForm

    val stateDate = state.tanggal

    val context = LocalContext.current
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    var dateTextField by remember {
        mutableStateOf(stateDate)
    }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd-MM-yyyy")
                .format(pickedDate)
        }
    }
    val dateDialogState = rememberMaterialDialogState()

    Column {
        OutlinedTextField(
            isError = state.tanggalError != null,
            supportingText = { Text(text = "Format: Tanggal-Bulan-Tahun") },
            label = { Text(text = "Masukan tanggal") },
            value = dateTextField,
            onValueChange = { viewModel.onEventDate(FormEventDate.TanggalChanged(it)) },
            modifier = Modifier.focusRequester(focusRequest),
            trailingIcon = {
                IconButton(onClick = { dateDialogState.show() }) {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        if (state.tanggalError != null) {
            Text(
                text = state.tanggalError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
    MaterialDialog(
        dialogState = dateDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                viewModel.onEventDate(FormEventDate.Submit)
                dateTextField = formattedDate.toString()
                Toast.makeText(
                    context,
                    "Tanggal diatur",
                    Toast.LENGTH_LONG
                ).show()
            }
            negativeButton(text = "Cancel")
        }
    ) {
        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
        ) {
            pickedDate = it
        }
    }
    return dateTextField
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DatePickerTextFieldPreview() {
    remember {
        FocusRequester()
    }
    //DatePickerTextField(focusChange)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DropdownMenuPreview() {
    DropdownMenuWithForms()
}

