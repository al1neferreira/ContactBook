package br.com.aline.contactbook

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

data class Contacts(
    val id: Int,
    val name: String,
    val cpf: String,
    val birthDate: String,
    val uf: String,
    val phone: String,
    val createdAt: Date
)

@RequiresApi(Build.VERSION_CODES.O)
fun getFakeList(): List<Contacts> {
    return listOf<Contacts>(
        Contacts(
            1, "Aline",
            "123.456.789-10",
            "17-12-1989",
            "SP",
            "(11)95209-1460",
            Date.from(Instant.now())
        ),
        Contacts(
            2, "Michelli",
            "234.758.876-oo",
            "15-04-1983",
            "RJ", "(11)90909-99999",
            Date.from(Instant.now())
        ),
        Contacts(
            3,
            "Artur",
            "356.856.987-12",
            "14-08-1998",
            "MG",
            "(11)933333-4160",
            Date.from(Instant.now())
        ),
        Contacts(
            4,
            "Tânia",
            "125.789.326-98",
            "18-09-1968",
            "AL",
            "11)85264-1460",
            Date.from(Instant.now())
        ),
        Contacts(
            5,
            "Afrânio",
            "231.546.123-00",
            "02-12-1966",
            "RS",
            "(11)93209-1660",
            Date.from(Instant.now())
        )

    )
}