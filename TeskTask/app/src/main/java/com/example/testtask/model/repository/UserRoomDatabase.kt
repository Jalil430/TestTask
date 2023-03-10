package com.example.testtask.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
