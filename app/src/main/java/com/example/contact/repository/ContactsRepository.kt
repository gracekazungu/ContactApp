package com.example.contact.repository

import android.provider.ContactsContract.Contacts
import androidx.lifecycle.LiveData
import com.example.contact.MyContactsApp
import com.example.contact.database.ContactsDb
import com.example.contact.model.ContactData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsRepository {
    val database=ContactsDb.getDatabase(MyContactsApp.appContext)


    suspend fun saveContact(contact: ContactData){
        withContext(Dispatchers.IO){
            database.getContactDao().insertContact(contact)
        }
    }
    fun getAllContacts():LiveData<List<ContactData>>{
        return database.getContactDao().getAllContacts()
    }
    fun getContactById(contactId:Int): LiveData<ContactData> {
        return database.getContactDao().getContactById(contactId)
    }

    suspend fun deleteContactById(contact: ContactData) {
        withContext(Dispatchers.IO) {
            database.getContactDao().deleteContactById(contact)
        }
    }
}