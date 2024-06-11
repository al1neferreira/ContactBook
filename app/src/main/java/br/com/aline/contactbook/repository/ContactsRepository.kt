package br.com.aline.contactbook.repository

import br.com.aline.contactbook.data.DataSource
import br.com.aline.contactbook.model.Contacts
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class ContactsRepository{

    private val dataSource = DataSource()

    fun addContact(
        name: String,
        cpf: String,
        phone: String,
        birthDate: String,
        uf: String,
        createdAt: LocalDateTime
    ){
        dataSource.addContact(name, cpf, phone, birthDate, uf, createdAt)

    }

    fun getContacts():Flow<MutableList<Contacts>>{
        return dataSource.getContacts()

    }

    fun deleteContact(contact: String){
        dataSource.deleteContact(contact)
    }


}