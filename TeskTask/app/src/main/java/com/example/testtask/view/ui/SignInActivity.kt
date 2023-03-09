package com.example.testtask.view.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.widget.Toast
import com.example.testtask.R
import com.example.testtask.databinding.SignInActivityBinding
import com.example.testtask.helper.Utils

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: SignInActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        binding = SignInActivityBinding.bind(findViewById(R.id.rootSignIn))

        binding.apply {
            btnSignIn.setOnClickListener {
                checkValidationOfData {
                    signIn()
                }
            }
            btnHaveAccount.setOnClickListener {
                startLogin()
            }
        }
    }

    private fun checkValidationOfData(callback: () -> Unit) {
        binding.apply {
            if (etFirstName.text.toString() == "" && etLastName.text.toString() == "") {
                Toast.makeText(this@SignInActivity, "Please enter your First and Last names *", Toast.LENGTH_SHORT).show()
            } else if (etFirstName.text.toString() == "") {
                Toast.makeText(this@SignInActivity, "Please enter your First name *", Toast.LENGTH_SHORT).show()
            } else if (etLastName.text.toString() == "") {
                Toast.makeText(this@SignInActivity, "Please enter your Last name *", Toast.LENGTH_SHORT).show()
            } else {
                if (Utils.isValidEmail(etEmail.text.toString())) {
                    callback.invoke()
                } else if (etEmail.text.toString() == "") {
                    Toast.makeText(this@SignInActivity, "Please enter your Email *", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@SignInActivity, "Please enter correct Email *", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun signIn() {

    }

    private fun startLogin() {
        val intent = Intent(this@SignInActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}