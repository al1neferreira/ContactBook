package br.com.aline.contactbook.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aline.contactbook.model.Contacts
import br.com.aline.contactbook.repository.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository
):ViewModel() {

    private val _allContacts = MutableStateFlow<MutableList<Contacts>>(mutableListOf())
    private val allContats: StateFlow<MutableList<Contacts>> = _allContacts

    private val _contact = MutableStateFlow("")
    private val contact: StateFlow<String> = _contact


    fun addContact(
        name: String,
        cpf: String,
        phone: String,
        birthDate: String,
        uf: String,
        createdAt: LocalDateTime
    ) {
        viewModelScope.launch {

            contactsRepository.addContact(name, cpf, phone, birthDate, uf, createdAt)
        }


    }

    fun getContacts(): Flow<MutableList<Contacts>> {

        viewModelScope.launch {

            contactsRepository.getContacts().collect {

                _allContacts.value = it


            }

        }

        return allContats

    }

    fun deleteContact(name: String) {

        viewModelScope.launch {

            contactsRepository.deleteContact(name)

        }
    }

    fun updateContact(name: String, cpf:String, phone:String, birthDate:String, uf: String, createdAt: Date) {

        viewModelScope.launch {

            contactsRepository.updateContact(name, cpf, phone, birthDate, uf, createdAt.toString())

        }

    }

}
