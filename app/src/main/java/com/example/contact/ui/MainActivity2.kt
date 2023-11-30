package com.example.contact.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Looper
import android.provider.MediaStore
import android.view.Gravity
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.example.contact.R
import com.example.contact.databinding.ActivityMain2Binding
import com.example.contact.model.ContactData
import com.example.contact.viewmodel.ContactsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.io.File

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    val contactsViewModel: ContactsViewModel by viewModels()

    lateinit var photoFile: File
    lateinit var fusedLocationClient :FusedLocationProviderClient
    lateinit var mylocation:Location

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if (result.resultCode==Activity.RESULT_OK) {
            Picasso
                .get()
                .load(
                    File(photoFile.absolutePath))
                .resize(120,120)
                .centerCrop(Gravity.CENTER)
                .placeholder(R.drawable.image)
                .error(R.drawable.image)
                .transform(CropCircleTransformation())
                .into(binding.avatar)
        }
    }
    val locationRequest = registerForActivityResult(ActivityResultContracts.RequestPermission()){result->
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED){

            getLocationUpdates()
        }
        else{
            Toast.makeText(this,"Please grant location permission",Toast.LENGTH_LONG).show()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onResume() {
        super.onResume()
        binding.btnbutton2.setOnClickListener {
            clearErrors()
            validatePage()
        }

        binding.avatar.setOnClickListener {
            capturePhoto()
        }
        getLocationUpdates()
    }

    private fun getLocationUpdates(){
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            val locationRequest = com.google.android.gms.location.LocationRequest.Builder(10000)
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            val locationCallBack = object : LocationCallback(){
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)

                    for (location in p0.locations){
                        Toast.makeText(baseContext, "Lat: ${location.longitude}, Long:${location.longitude}",
                            Toast.LENGTH_LONG).show()

                    }
                }

            }
            fusedLocationClient.requestLocationUpdates(locationRequest,locationCallBack, Looper.getMainLooper())

//            getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY,
//                CancellationTokenSource().token
//            )
//             .addOnSuccessListener { location ->
//                mylocation = location
//                Toast.
//                makeText(
//                    this, "Lat:${location.latitude},Long:${location.longitude}",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
        }
        else{
            locationRequest.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
    }

    private fun capturePhoto(){
        val photoIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile=getPhotoFile("photo_${System.currentTimeMillis()}")
        val fileUri = FileProvider.getUriForFile(this,"com.example.contact.provider",photoFile)
        photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        cameraLauncher.launch(photoIntent)
    }
    private fun getPhotoFile(fileName:String):File{
        val folder=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val tempFile=File.createTempFile(fileName,".jpeg",folder)

        return tempFile
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
            val newContact = ContactData(0, Image=photoFile.absolutePath, Name, Number, EmailAddress,)

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