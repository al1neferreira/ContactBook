package br.com.aline.contactbook.data

import br.com.aline.contactbook.model.Contacts
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDateTime

class DataSource {

    private val db = FirebaseFirestore.getInstance()

    private val _allContacts = MutableStateFlow<MutableList<Contacts>>(mutableListOf())
    private val allContats: StateFlow<MutableList<Contacts>> = _allContacts

    fun addContact(
        name: String,
        cpf: String,
        phone: String,
        birthDate: String,
        uf: String,
        createdAt: LocalDateTime
    ) {

        val contactsMap = hashMapOf(
            "name" to name,
            "cpf" to cpf,
            "phone" to phone,
            "birthDate" to birthDate,
            "uf" to uf,
            "createdAt" to createdAt


        )
        db.collection("contacts").document(name).set(contactsMap).addOnCompleteListener {

        }.addOnFailureListener {

        }
    }

    fun getContacts(): Flow<MutableList<Contacts>> {

        val contactList : MutableList<Contacts> = mutableListOf()
        db.collection("contacts").get().addOnCompleteListener { querySnapshot ->
            if (querySnapshot.isSuccessful) {
                for (document in querySnapshot.result) {
                    val contact = document.toObject(Contacts::class.java)
                    contactList.add(contact)
                    _allContacts.value = contactList
                }
            }
        }
        return allContats
    }

    fun deleteContact(contact: String){
        db.collection("contacts").document(contact).delete().addOnCompleteListener {

        }.addOnFailureListener{

        }

    }
}

