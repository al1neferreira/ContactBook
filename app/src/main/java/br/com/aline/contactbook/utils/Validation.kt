package br.com.aline.contactbook.utils

import android.content.Context
import android.widget.Toast

class Validation(
    val context: Context

    ) {

    fun isNameValid(name: String): Boolean {
        if (name.trim().isEmpty()) {
            Toast.makeText(context, "Por favor, insira o seu nome e sobrenome", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }

    fun isUfValid(uf: String, cpf: String): Boolean {
        if (uf == "SP" || uf == "sp" && cpf!!.isEmpty()) {
            Toast.makeText(context, "Por favor, digite o seu CPF", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    fun isBirthDateValid(birthDate: String): Boolean {
        if (birthDate <= 2006.toString()) {
            Toast.makeText(context, "Cadastro não permitido", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
    fun isPhoneValid(phone: String): Boolean {
        if (phone.trim().isEmpty()) {
            Toast.makeText(context, "Please enter a phone number", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}










