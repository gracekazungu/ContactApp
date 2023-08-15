package com.example.contact.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contact.model.ContactData
import com.example.contact.repository.ContactsRepository
import kotlinx.coroutines.launch

class ContactsViewModel: ViewModel() {
    val contactsRepo=ContactsRepository()

    fun saveContact(contact:ContactData){
        viewModelScope.launch {
            contactsRepo.saveContact(contact)
        }
    }
    fun getContacts():LiveData<List<ContactData>>{
        return  contactsRepo.getAllContacts()
    }
    fun getContactById(contactId:Int):LiveData<ContactData>{
        return contactsRepo.getContactById(contactId)
    }

    fun deleteContact(contact: ContactData) {
        viewModelScope.launch {
            contactsRepo.deleteContactById(contact)
        }
    }
}