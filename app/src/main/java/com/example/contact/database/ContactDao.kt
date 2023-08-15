package com.example.contact.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.contact.model.ContactData

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: ContactData)

   @Query("SELECT * FROM Contacts ORDER BY Name")
   fun getAllContacts():LiveData<List<ContactData>>

   @Query("SELECT * FROM Contacts WHERE  contactId=:contactId")
   fun getContactById(contactId:Int):LiveData<ContactData>


   @Delete
   suspend fun deleteContactById(contact: ContactData)
//    @Query("DELETE FROM Contacts WHERE contactId=:contactId")
//    suspend fun deleteContactById()
}