package com.example.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.databinding.ContactListBinding


class ContactAdapter(var contactlist:List<ContactData>): RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding=
            ContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactlist.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact=contactlist.get(position)
        val binding=holder.binding
        binding.tvName.text=currentContact.Name
        binding.tvNumber.text=currentContact.Number
        binding.tvEmail.text=currentContact.EmailAddress
    }
}
class ContactViewHolder(val binding: ContactListBinding): RecyclerView.ViewHolder(binding.root) {
}