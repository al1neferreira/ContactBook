package br.com.aline.contactbook.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.aline.contactbook.ui.theme.ShapeEditText
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerBirthDate() {

    var selectedDate by remember() { mutableStateOf("") }
    var showDatePickerDialog by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    val datePickerState = rememberDatePickerState()
    if (showDatePickerDialog) {

        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                selectedDate = millis.toBrazilianDateFormat()
                            }


                        showDatePickerDialog = false
                    }) {
                    Text(text = "SALVAR")
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = {},
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .padding( start = 40.dp, end = 40.dp, bottom = 10.dp)
            .onFocusChanged {
                if (it.isFocused) {
                    showDatePickerDialog = true
                    focusManager.clearFocus(force = true)
                }
            }, shape = ShapeEditText.small,
        label = {
            Text(text = "Data de nascimento")
        },
        readOnly = true

    )

}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy"
): String {
    val date = java.sql.Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DatePickerPreview() {
    DatePickerBirthDate()

}