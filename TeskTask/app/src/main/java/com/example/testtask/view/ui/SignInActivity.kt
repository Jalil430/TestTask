package com.example.testtask.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.testtask.R
import com.example.testtask.databinding.SignInActivityBinding
import com.example.testtask.helper.Utils
import com.example.testtask.model.repository.User
import com.example.testtask.model.repository.UserDao
import com.example.testtask.model.repository.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: SignInActivityBinding

    private var localDatabase: UserRoomDatabase? = null
    private var userDao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)
        binding = SignInActivityBinding.bind(findViewById(R.id.rootSignIn))

        initializeDatabase()

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
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            if (userDao?.login(firstName, email) == true) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(this@SignInActivity, "This account already exists, log in", Toast.LENGTH_SHORT).show()
                }
            } else {
                userDao?.insertUser(User(0, firstName, lastName, email))

                /*val intent = Intent(this, page1Activity::class.java)
                startActivity(intent)
                finish()*/
            }
        }
    }

    private fun startLogin() {
        val intent = Intent(this@SignInActivity, LoginActivity::class.java)
        startActivity(intent)
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