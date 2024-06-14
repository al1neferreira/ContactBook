package br.com.aline.contactbook

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.aline.contactbook.repository.ContactsRepository
import br.com.aline.contactbook.ui.theme.ContactBookTheme
import br.com.aline.contactbook.view.ContactList
import br.com.aline.contactbook.view.EditContact
import br.com.aline.contactbook.view.SaveContact
import br.com.aline.contactbook.viewModel.ContactsViewModel


class MainActivity : ComponentActivity() {

   lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiKey = br.com.aline.contactbook.BuildConfig.apiKey

        enableEdgeToEdge()
        setContent {
            ContactBookTheme {

                navController = rememberNavController()
                val viewModel = ContactsViewModel(ContactsRepository())


                NavHost(
                    navController = navController,
                    startDestination = "contactList"
                ) {
                    composable(
                        route = "contactList"
                    ) {
                        ContactList(navController, viewModel)

                    }
                    composable(
                        route = "saveContact"
                    ) {
                        SaveContact(navController)
                    }
                    composable(
                        route = "editContact"

                    ){
                        EditContact(navController)
                    }
                }
            }
        }
    }
}


