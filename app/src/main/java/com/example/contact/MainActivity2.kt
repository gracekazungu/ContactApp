package com.example.contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.contact.databinding.ActivityMain2Binding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.sql.RowId

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
//    lateinit var etName: TextInputEditText
//    lateinit var etContact: TextInputEditText
//    lateinit var etEmail: TextInputEditText
//    lateinit var etlink: TextInputEditText
//    lateinit var btnbutton2: Button
//    lateinit var tilName: TextInputLayout
//    lateinit var tilEmail: TextInputLayout
//    lateinit var tillink: TextInputLayout
//    lateinit var tilContact: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main2)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        castViews()

    }

    override fun onResume() {
        super.onResume()
        binding.btnbutton2.setOnClickListener {
            clearErrors()
            validatePage()
        }
    }
//fun castViews(){
//etName=findViewById(R.id.etName)
//    etlink=findViewById(R.id.etlink)
//    etContact=findViewById(R.id.etContact)
//    etEmail=findViewById(R.id.etEmail)
//   tillink=findViewById(R.id.tillink)
//   tilName=findViewById(R.id.tilName)
//   tilContact=findViewById(R.id.tilContact)
//   tilEmail=findViewById(R.id.tilEmail)
//    btnbutton2=findViewById(R.id.btnbutton2)
//}

fun validatePage() {
    val firstName = binding.etName.text.toString()
    val contact = binding.etContact.text.toString()
    val email = binding.etEmail.text.toString()
    val link = binding.etlink.text.toString()
    var error = false

    if (firstName.isBlank()) {
        binding.tilName.error = "First name is required"
        error = true
    }
    if (contact.isBlank()) {
        binding.tilContact.error = "Contact is required"
        error = true
    }
    if (email.isBlank()) {
        binding.tilEmail.error = "Email is required"
        error = true
    }

    if (link.isBlank()) {
        binding.tillink.error = "photolink is required"
        error = true
    }

    if (!error) {
        Toast.makeText(this, "Hello your contact has been added successfully to your contact list", Toast.LENGTH_LONG).show()
    }
}

fun clearErrors() {
    binding.tilName.error = null
    binding.tilContact.error = null
    binding.tilEmail.error = null
    binding.tillink.error = null
}
}