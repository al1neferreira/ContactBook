package br.com.aline.contactbook.repository

import androidx.hilt.navigation.compose.hiltViewModel
import br.com.aline.contactbook.data.DataSource
import br.com.aline.contactbook.model.Contacts
import br.com.aline.contactbook.viewModel.ContactsViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

@ViewModelScoped
class ContactsRepository @Inject constructor(private val dataSource: DataSource) {

    fun addContact(
        name: String,
        cpf: String,
        phone: String,
        birthDate: String,
        uf: String,
        createdAt: LocalDateTime
    ) {
        dataSource.addContact(name, cpf, phone, birthDate, uf, createdAt)

    }

    fun getContacts(): Flow<MutableList<Contacts>> {
        return dataSource.getContacts()

    }

    fun deleteContact(contact: String) {
        dataSource.deleteContact(contact)
    }

    fun updateContact(
        id: String,
        name: String,
        cpf: String,
        phone: String,
        birthDate: String,
        uf: String
    ) {
        dataSource.updateContact(id,name,cpf,phone,birthDate,uf)

    }

}