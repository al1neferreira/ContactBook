package br.com.aline.contactbook


import  android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddContactScreen() {
    var contactName by rememberSaveable { mutableStateOf("") }
    var contactCPF by rememberSaveable { mutableStateOf("") }
    var savedAt by rememberSaveable { mutableStateOf(LocalDateTime.now()) }
    var phoneContact by rememberSaveable { mutableStateOf("") }
    var regionContact by rememberSaveable { mutableStateOf(Region.MG.name) }
    var birthDateContact by rememberSaveable {mutableStateOf(Date().time)
    }


    Column(
        modifier = Modifier
            .padding(16.dp, 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Adicionar Contato",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Nome",
            style = MaterialTheme.typography.bodyLarge
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = contactName,
            onValueChange = { contactName = it },
            placeholder = { Text(text = "Aline Ferreira") },
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "CPF",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = contactName,
            onValueChange = { contactCPF = it },
            placeholder = { Text(text = "155.155.155-55") },
        )
        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Telefone",
            style = MaterialTheme.typography.bodyLarge
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneContact,
            onValueChange = { phoneContact = it },
            placeholder = { Text(text = "(11) 99100-9000") },
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Data de nascimento + Date Picker",
            style = MaterialTheme.typography.bodyLarge
        )
        //BirthContactDatePicker {birthDateContact = it }

        Spacer(modifier = Modifier.padding(8.dp))

        RegionDropdownMenu { regionContact = it }

        BirthContactDatePicker {
            birthDateContact = it
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionDropdownMenu(region: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Text(
            text = "Região",
            style = MaterialTheme.typography.bodyLarge
        )

        val options = getRegionList().map { it.name }
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                value = selectedOptionText, onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = {
                expanded = false
            }
            ) {
                options.forEach { selectedOption ->
                    DropdownMenuItem(
                        text = { selectedOption },
                        onClick = {
                            selectedOptionText = selectedOption
                            region(selectedOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BirthContactDatePicker(birthDate: (Long) -> Unit) {
    Text(
        text = "Data de nascimento",
        style = MaterialTheme.typography.bodyLarge
    )
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    val currentDate = Date().toString()
    var selectedDate by rememberSaveable { mutableStateOf(currentDate) }

    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val mont: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

}




            
