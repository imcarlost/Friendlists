package com.hako.base.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hako.base.domain.database.dao.AlbumDao
import com.hako.base.domain.database.dao.PhotoDao
import com.hako.base.domain.database.dao.UserDao
import com.hako.base.domain.database.entities.AlbumEntity
import com.hako.base.domain.database.entities.PhotoEntity
import com.hako.base.domain.database.entities.UserEntity

@Database(entities = [UserEntity::class, AlbumEntity::class, PhotoEntity::class], version = 1, exportSchema = false)
abstract class DatabaseClient : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao
}