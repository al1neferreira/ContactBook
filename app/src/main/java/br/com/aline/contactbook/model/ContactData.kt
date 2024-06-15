package br.com.aline.contactbook.model

import java.io.Serializable

@kotlinx.serialization.Serializable
data class ContactData (
    val id: String? = null,
    val name: String? = null,
    val cpf: String ? = null,
    val birthDate: String ? = null,
    val uf: String? = null,
    val phone: String ? = null,
    //val createdAt: Date? = null
):Serializable
