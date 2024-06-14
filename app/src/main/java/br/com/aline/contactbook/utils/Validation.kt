package br.com.aline.contactbook.utils

import android.content.Context
import android.widget.Toast

class Validation(
    val context: Context,
    private val name: String? = null,
    private val cpf: String? = null,
    private val birthDate: String? = null,
    private val uf: String? = null,
    private val phone: String? = null,

    ) {

    fun isNameValid(name: String? = this.name): Boolean {
        if (name == null || name.trim().isEmpty()) {
            Toast.makeText(context, "Por favor, insira o seu nome e sobrenome", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    fun isUfValid(uf: String? = this.uf, cpf: String? = this.cpf): Boolean {
        if (uf == "SP" || uf == "sp" && cpf!!.isEmpty()) {
            Toast.makeText(context, "Por favor, digite o seu CPF", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun isBirthDateValid(birthDate: String? = this.birthDate): Boolean {
        if (birthDate == null) {
            Toast.makeText(context, "Por favor, insira o seu nome e sobrenome", Toast.LENGTH_SHORT)
                .show()
        } else if (birthDate <= 2006.toString()) {
            Toast.makeText(context, "Cadastro não permitido", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    fun isPhoneValid(phone: String? = this.phone): Boolean {
        if (phone == null || phone.trim().isEmpty()) {
            Toast.makeText(context, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

}










