package com.example.testtask.model.repository

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insertUser(vararg user: User)

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun updateData(vararg user: User)
//
//    @Delete
//    fun delete(user: User)
//
//    @Query("SELECT * FROM user")
//    fun get(): User
//
//    @Query("SELECT EXISTS(SELECT * FROM user)")
//    fun isExists(): Boolean

    @Query("SELECT EXISTS (SELECT * from User where first_name=:firstName)")
    fun isTaken(firstName: String): Boolean

    @Query("SELECT EXISTS (SELECT * from User where first_name=:firstName AND email=:email)")
    fun login(firstName: String, email: String): Boolean
}