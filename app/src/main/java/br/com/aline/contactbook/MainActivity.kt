package br.com.aline.contactbook

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.aline.contactbook.ui.theme.ContactBookTheme
import java.lang.reflect.Modifier

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactBookTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "contactList") {
                    composable(
                        route = "contactList"
                    ) {
                        ContactList(navController)
                    }
                    composable(
                        route = "addContact"
                    ) {
                        AddContact(navController)
                    }
                }
            }
        }
    }
}


