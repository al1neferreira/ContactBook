package br.com.aline.contactbook

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.aline.contactbook.ui.theme.ContactBookTheme
import br.com.aline.contactbook.view.ContactList
import br.com.aline.contactbook.view.SaveContact
import com.google.firebase.BuildConfig

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiKey = br.com.aline.contactbook.BuildConfig.apiKey

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
                        route = "saveContact"
                    ) {
                        SaveContact(navController)
                    }
                }
            }
        }
    }
}


