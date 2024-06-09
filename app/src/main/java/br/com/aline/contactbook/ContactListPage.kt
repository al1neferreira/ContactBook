package br.com.aline.contactbook

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.aline.contactbook.ui.theme.PurpleGrey40
import java.text.SimpleDateFormat
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContactListPage() {
    val contactList = getFakeList()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)


    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
        ) {
            LazyColumn(
                content = {
                    itemsIndexed(contactList) { index: Int, item: Contacts ->
                        ContactItem(item = item)
                    }
                }
            )
        }

    }
}

@Composable
fun ContactItem(item: Contacts) {

    var expandedCard by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color = PurpleGrey40)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically



    ) {
        Column(
            modifier = Modifier
                .padding(all = 10.dp)
        )
        {
            Text(
                text = item.name,
                Modifier.padding(start = 2.dp),
                fontSize = 18.sp,
                color = Color.White
            )

            if (expandedCard) {

                Text(
                    text = "CPF: ${item.cpf}", Modifier.padding(5.dp),
                    color = Color.White
                )
                Text(
                    text = "Data de nascimento: ${item.birthDate}", Modifier.padding(5.dp),
                    color = Color.White
                )
                Text(
                    text = "UF: ${item.uf}", Modifier.padding(5.dp),
                    color = Color.White
                )
                Text(
                    text = "Telefone: ${item.phone}", Modifier.padding(4.dp),
                    color = Color.White
                )
                Text(
                    text = SimpleDateFormat(
                        "EEE, d MMM yyyy - HH:mm", Locale.ENGLISH)
                        .format(item.createdAt),
                    Modifier.padding(2.dp),
                    color = Color.Black
                )
            }
            IconButton(onClick = { expandedCard = !expandedCard }) {
                Icon(
                    tint = Color.White,
                    modifier = Modifier.size(25.dp),
                    imageVector = if (expandedCard)
                        Icons.Filled.Done else Icons.Filled.AccountBox,
                    contentDescription =
                    if (expandedCard) {
                        "show less"
                    } else {
                        "show more"
                    }
                )
            }
        }
    }
}