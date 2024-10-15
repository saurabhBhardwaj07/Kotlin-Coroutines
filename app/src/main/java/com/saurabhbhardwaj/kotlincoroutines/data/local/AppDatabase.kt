package com.saurabhbhardwaj.kotlincoroutines.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saurabhbhardwaj.kotlincoroutines.data.local.dao.UserDao
import com.saurabhbhardwaj.kotlincoroutines.data.local.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}