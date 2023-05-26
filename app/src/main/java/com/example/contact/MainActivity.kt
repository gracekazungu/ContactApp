package com.example.contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        displayTweets()
    }
    fun displayTweets(){
        val contact1=ContactData("","Grace","0746404458","kazungungrace@gmail.com")
        val contact2=ContactData("","Rebecca","07895634823","srebeccanangila@gmail.com")
        val contact3=ContactData("","Pauline","0789563423","paulineochieng@gmail.com")
        val contact4=ContactData("","Rose","0756342345","rosekivuvan@gmail.com")
        val contact5= ContactData("","Eunice","075634231234","mjeraeunice@gmail.com")

        val contactList= listOf(contact1,contact2,contact3,contact4,contact5)
        val twtAdapter= ContactAdapter(contactList)
        binding.rvRecycler.layoutManager = LinearLayoutManager(this)
        binding.rvRecycler.adapter=twtAdapter
    }
}