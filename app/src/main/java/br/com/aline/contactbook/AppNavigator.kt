package br.com.aline.contactbook

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.aline.contactbook.model.ContactData
import br.com.aline.contactbook.repository.ContactsRepository
import br.com.aline.contactbook.view.ContactList
import br.com.aline.contactbook.view.EditContact
import br.com.aline.contactbook.view.SaveContact
import br.com.aline.contactbook.viewModel.ContactsViewModel
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


sealed class Screen(val route: String) {
    data object ContactList : Screen("contactList")
    data object EditContact : Screen("editContact")
    data object SaveContact : Screen("saveContact")
}

@OptIn(ExperimentalEncodingApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigator(initialScreen:Screen) {
    val navController = rememberNavController()
    val viewModel = ContactsViewModel(ContactsRepository())


    NavHost(
        navController = navController,
        startDestination = initialScreen.route
    ) {
        composable(
            Screen.ContactList.route
        ) {
            ContactList(navController, viewModel)
        }
        composable(
            Screen.SaveContact.route
        ) {
            SaveContact(navController, viewModel)
        }
        composable(
            Screen.EditContact.route +"/{contactData}",
            arguments = listOf(
                navArgument("contactData") { NavType.SerializableType(ContactData::class.java) }
            )
        ) {backStackEntry ->
            val encodedModel = backStackEntry.arguments?.getString("contactData")
            val decodedModel =
                encodedModel?.let { Base64.UrlSafe.decode(it).toString(Charsets.UTF_8) }
            val contactData = Json.decodeFromString<ContactData>(decodedModel!!)

            EditContact(navController, contactData)
        }
    }

}