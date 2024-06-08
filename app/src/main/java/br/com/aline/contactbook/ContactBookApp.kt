package br.com.aline.contactbook

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.aline.contactbook.ui.theme.ContactBookTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContactBookApp() {

    ContactBookTheme {
        Surface(modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AddContactScreen()
        }
    }

}