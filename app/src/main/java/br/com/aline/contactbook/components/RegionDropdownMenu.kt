import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.aline.contactbook.model.getRegionList
import br.com.aline.contactbook.ui.theme.ShapeEditText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionDropdownMenu(region: (String) -> Unit) {

    val options = getRegionList().map { it.name }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        OutlinedTextField(
            shape = ShapeEditText.small,
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp)
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
