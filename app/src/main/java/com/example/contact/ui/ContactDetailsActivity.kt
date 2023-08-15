package com.example.contact.ui

import android.annotation.SuppressLint
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.contact.R
import com.example.contact.databinding.ActivityContactDetailsBinding
import com.example.contact.model.ContactData
import com.example.contact.viewmodel.ContactsViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactDetailsActivity : AppCompatActivity() {
    var contactId = 0
  private  lateinit var viewModel: ContactsViewModel
    lateinit var binding: ActivityContactDetailsBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
      viewModel= ContactsViewModel()

     val contactId=intent.getIntExtra("CONTACT_ID",0)
        viewModel.getContactById(contactId).observe(this, Observer {contact->
            binding.button.setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    deleteContact(contact)
                }
            }
            if (contact !=null){
                contactDetails(contact)
            }
        })
//       v val bundle=intent.extras
//        if(bundle!=null){
//            contactId=bundle.getInt("CONTACT_ID",0)
//            Toast.makeText(this,"$contactId", Toast.LENGTH_SHORT).show()
//        }

//        binding.button.setOnClickListener {
//            deleteContact()
//        }
    }

  private  fun contactDetails(contact:ContactData){
       binding.tvName.text=contact.Name
        binding.tvNumber.text=contact.Number
        if(!contact.Image.isNullOrEmpty()){
            Picasso.get()
                .load(contact.Image)
                .into(binding.imageView4)
        }
    }

    private suspend fun deleteContact(contact: ContactData) {
        viewModel.deleteContact(contact)
        finish()
    }

}