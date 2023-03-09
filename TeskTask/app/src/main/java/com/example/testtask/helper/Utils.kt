package com.example.testtask.helper

import android.util.Patterns

object Utils {

    fun isValidEmail(target: String?): Boolean {
        return target != null && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

}