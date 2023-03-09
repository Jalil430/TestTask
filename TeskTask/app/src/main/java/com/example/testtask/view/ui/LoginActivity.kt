package com.example.testtask.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testtask.R
import com.example.testtask.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        binding = LoginActivityBinding.bind(findViewById(R.id.rootLogin))

        binding.apply {

        }
    }
}