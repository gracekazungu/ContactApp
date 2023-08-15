package com.example.contact.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact.model.ContactData
import com.example.contact.databinding.ActivityMainBinding
import com.example.contact.viewmodel.ContactsViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val contactsViewModel: ContactsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        contactsViewModel.getContacts()
            .observe(this, Observer{contactList->displayContacts(contactList)})
        binding.btnbutton.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
    fun displayContacts(contactList:List<ContactData>){
        val twtAdapter= ContactAdapter(contactList,this)
        binding.rvRecycler.layoutManager = LinearLayoutManager(this)
        binding.rvRecycler.adapter=twtAdapter
    }
}