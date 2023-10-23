package com.example.chatapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.databinding.ActivityOtpactivityBinding
import com.google.firebase.auth.FirebaseAuth


class OTPActivity : AppCompatActivity() {
    private lateinit var binding :ActivityOtpactivityBinding
    var verificationId:String ?=null
    var auth:FirebaseAuth?=null
    var dialog:ProgressDialog ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = ProgressDialog(this@OTPActivity)
        dialog !! .setMessage("Sending OTP.....")
        dialog!!.show()

        auth = FirebaseAuth.getInstance()

        val phoneNumber = intent.getStringExtra("phoneNumber")

        binding.phnTxt.text = "Verify $phoneNumber"



    }
}