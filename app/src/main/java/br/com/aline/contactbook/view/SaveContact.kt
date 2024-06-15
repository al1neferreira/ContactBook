package br.com.aline.contactbook.view

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.aline.contactbook.repository.ContactsRepository
import br.com.aline.contactbook.ui.theme.NewPurple
import br.com.aline.contactbook.ui.theme.ShapeEditText
import br.com.aline.contactbook.components.CustomTextField
import br.com.aline.contactbook.model.ContactData
import br.com.aline.contactbook.utils.Validation
import br.com.aline.contactbook.viewModel.ContactsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SaveContact(
    navController: NavController,
    viewModel: ContactsViewModel

) {

    var name by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var uf by remember { mutableStateOf("") }
    var savedAt by remember { mutableStateOf(LocalDateTime.now()) }

    var nameError by mutableStateOf(false)
    var ufError by mutableStateOf(false)
    var birthDateError by mutableStateOf(false)
    var phoneError by mutableStateOf(false)

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var contacts: ContactData? = null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        Text(
            text = "Adicionar Contato",
            fontWeight = FontWeight.Bold,
            color = NewPurple,
            fontSize = 25.sp,
            modifier = Modifier
                .padding(top = 50.dp)
                .align(Alignment.CenterHorizontally)
        )

        CustomTextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 50.dp, end = 50.dp),
            label = "Nome ",
            maxLines = 1,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            value = cpf,
            onValueChange = {
                cpf = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 50.dp, end = 50.dp),
            label = "CPF",
            maxLines = 1,
            keyboardType = KeyboardType.Number,


            )
        CustomTextField(
            value = phone,
            onValueChange = {
                phone = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 50.dp, end = 50.dp),
            label = "Telefone",
            maxLines = 1,
            keyboardType = KeyboardType.Number
        )
        CustomTextField(
            value = birthDate,
            onValueChange = {
                birthDate = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 50.dp, end = 50.dp),
            label = "Data de nascimento",
            maxLines = 1,
            keyboardType = KeyboardType.Number
        )
        CustomTextField(
            value = uf,
            onValueChange = {
                uf = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 50.dp, end = 50.dp),
            label = "UF",
            maxLines = 1,
            keyboardType = KeyboardType.Number,

            )

        //DatePickerBirthDate()

        //RegionDropdownMenu { regionContact = it }

        fun isAnyChangeAvailable(
            name: String,
            cpf: String,
            phone: String,
            birthDate: String,
            uf: String
        ): Boolean {
            if (
                name?.equals(contacts?.name) == true &&
                cpf?.equals(contacts?.cpf) == true &&
                phone?.equals(contacts?.phone) == true &&
                uf?.equals(contacts?.uf) == true &&
                birthDate?.equals(contacts?.birthDate) == true
            ) {
                return false
            }
            return true
        }

        fun isContactValid(
            name: String,
            cpf: String,
            phone: String,
            birthDate: String,
            uf: String
        ): Boolean {
            return Validation(
                context = context,
                name = name,
                cpf = cpf,
                phone = phone,
                birthDate = birthDate,
                uf = uf
            ).run {
                isNameValid().also {
                    nameError = !it
                } && isUfValid().also {
                    ufError = !it
                } && isBirthDateValid().also {
                    birthDateError = !it

                } && isPhoneValid().also {
                    phoneError = !it
                } && isAnyChangeAvailable(
                    name = name,
                    cpf = cpf,
                    phone = phone,
                    birthDate = birthDate,
                    uf = uf
                )

            }
        }

        Button(
            onClick = {
                if (!isContactValid(
                        name,
                        cpf,
                        phone,
                        birthDate,
                        uf
                    )
                ) return@Button

                viewModel.addContact(
                    name = name, cpf = cpf, phone = phone,
                    birthDate = birthDate, uf = uf, savedAt
                )

                var message = true

                scope.launch(Dispatchers.IO) {
                    if (name.isEmpty()
                        || phone.isEmpty()
                        || uf.isEmpty()
                        || birthDate.isEmpty()
                    ) {


                    } else {

                        message = false

                    }

                }


                scope.launch(Dispatchers.Main) {
                    if (message) {
                        Toast.makeText(context, "Contato adicionado!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = NewPurple),
            shape = ShapeEditText.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 55.dp, end = 55.dp, top = 10.dp)

        ) {
            Text(text = "SALVAR")
        }

    }
}



