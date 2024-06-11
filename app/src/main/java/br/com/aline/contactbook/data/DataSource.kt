package br.com.aline.contactbook.data

import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    fun addContact(
        name: String,
        cpf: String,
        birthDate: String,
        uf: String,
        phone: String,
        createdAt: LocalDateTime
    ) {

        val contactsMap = hashMapOf(
            "name" to name,
            "cpf" to cpf,
            "birthDate" to birthDate,
            "uf" to uf,
            "phone" to phone,
            "createdAt" to createdAt


        )
        db.collection("contacts").document(name).set(contactsMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }
}