package com.hako.userlist.domain.clients

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hako.userlist.domain.datasource.UserDao
import com.hako.userlist.model.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class LocalClient : RoomDatabase() {
    abstract fun userDao(): UserDao
}