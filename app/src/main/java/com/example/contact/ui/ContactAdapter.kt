package com.example.contact.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.model.ContactData
import com.example.contact.R
import com.example.contact.databinding.ContactListBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class ContactAdapter(var contactlist:List<ContactData>, var context:Context): RecyclerView.Adapter<ContactViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding=
            ContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactlist.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contactlist.get(position)
        val binding = holder.binding
        binding.tvName.text = currentContact.Name
        binding.tvNumber.text = currentContact.Number
        binding.tvEmail.text = currentContact.EmailAddress

        if (currentContact.Image.isNotBlank()) {
            Picasso
                .get()
                .load(currentContact.Image)
//            .resize(80,80)
//            .centerCrop()
                .placeholder(R.drawable.image)
                .error(R.drawable.image)
                .transform(CropCircleTransformation())
                .into(binding.imageView)

        }
        binding.cvContact.setOnClickListener {
            val intent=Intent(context,ContactDetailsActivity::class.java)
            intent.putExtra("CONTACT_ID",currentContact.contactId)
            context.startActivity(intent)
        }
    }
}
class ContactViewHolder(val binding: ContactListBinding): RecyclerView.ViewHolder(binding.root) {
}