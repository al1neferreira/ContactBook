package br.com.aline.contactbook

import java.time.LocalDateTime

data class Contacts(
    val id: Int,
    val name: String,
    val cpf: String,
    val birthDate: String,
    val uf: String,
    val phone: String,
    val dateTime: LocalDateTime
)
