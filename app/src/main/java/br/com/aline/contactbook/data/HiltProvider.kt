package br.com.aline.contactbook.data

import android.app.Application
import android.content.Context
import br.com.aline.contactbook.repository.ContactsRepository
import br.com.aline.contactbook.viewModel.ContactsViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltProvider {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun fireBaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun dataSource(firestore: FirebaseFirestore, context: Context): DataSource {
        return DataSource(firestore, context)
    }

    @Provides
    @Singleton
    fun ContactsRepository(dataSource: DataSource): ContactsRepository {
        return ContactsRepository(dataSource)

    }

    @Provides
    @Singleton
    fun ContactsViewlModel(repository: ContactsRepository): ContactsViewModel {
        return ContactsViewModel(repository)
    }

}
