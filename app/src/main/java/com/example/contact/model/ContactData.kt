package com.example.contact.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class ContactData(
    @PrimaryKey(autoGenerate = true) var contactId: Int,
    var Image:String,
    var Name:String,
    var Number:String,
    var EmailAddress:String

)
