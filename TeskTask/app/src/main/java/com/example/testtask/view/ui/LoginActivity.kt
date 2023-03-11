package com.example.testtask.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.testtask.R
import com.example.testtask.databinding.LoginActivityBinding
import com.example.testtask.model.repository.User
import com.example.testtask.model.repository.UserDao
import com.example.testtask.model.repository.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding

    private var localDatabase: UserRoomDatabase? = null
    private var userDao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        binding = LoginActivityBinding.bind(findViewById(R.id.rootLogin))

        initializeDatabase()

        binding.apply {
            btnLogin.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        val firstName = binding.etFirstName2.text.toString()
        val email = binding.etPassword.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            if (userDao?.login(firstName, email) == false) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@LoginActivity, "Incorrect Name or Email", Toast.LENGTH_SHORT).show()
                }
            } else {
                /*val intent = Intent(this, page1Activity::class.java)
                  startActivity(intent)
                  finish()*/
            }
        }
    }

    private fun initializeDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            localDatabase = Room.databaseBuilder(
                applicationContext,
                UserRoomDatabase::class.java, "books-database"
            ).build()

            userDao = localDatabase?.userDao()
        }
    }
}