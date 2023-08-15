package com.example.contact.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.contact.R
import com.example.contact.databinding.ActivityMain2Binding
import com.example.contact.model.ContactData
import com.example.contact.viewmodel.ContactsViewModel

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    val contactsViewModel: ContactsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        binding.btnbutton2.setOnClickListener {
            clearErrors()
            validatePage()
        }
    }

    fun validatePage() {
        val Name = binding.etName.text.toString()
        val Number = binding.etContact.text.toString()
        val EmailAddress = binding.etEmail.text.toString()
        var error = false

        if (Name.isBlank()) {
            binding.tilName.error = getString(R.string.first_name_is_required)
            error = true
        }
        if (Number.isBlank()) {
            binding.tilContact.error = getString(R.string.contact_is_required)
            error = true
        }
        if (EmailAddress.isBlank()) {
            binding.tilEmail.error = getString(R.string.email_is_required)
            error = true
        }


        if (!error) {
            val newContact = ContactData(0, "", Name, Number, EmailAddress)

            contactsViewModel.saveContact(newContact)
            Toast.makeText(this, getString(R.string.contact_saved), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun clearErrors() {
        binding.tilName.error = null
        binding.tilContact.error = null
        binding.tilEmail.error = null
    }
}