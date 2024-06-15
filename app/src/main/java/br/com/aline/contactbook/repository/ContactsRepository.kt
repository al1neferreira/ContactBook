package br.com.aline.contactbook.repository

import br.com.aline.contactbook.data.DataSource
import br.com.aline.contactbook.model.ContactData
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime


class ContactsRepository() {

    private val dataSource= DataSource()

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

    fun getContacts(): Flow<MutableList<ContactData>> {
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