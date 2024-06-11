package br.com.aline.contactbook.repository

import br.com.aline.contactbook.data.DataSource
import java.time.LocalDateTime

class ContactsRepository{

    private val dataSource = DataSource()

    fun addContact(
        name: String,
        cpf: String,
        birthDate: String,
        uf: String,
        phone: String,
        createdAt: LocalDateTime
    ){
        dataSource.addContact(name, cpf, birthDate, uf, phone, createdAt)

    }
}