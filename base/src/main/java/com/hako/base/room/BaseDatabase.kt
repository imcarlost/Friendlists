package com.hako.base.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hako.base.room.dao.AlbumDao
import com.hako.base.room.dao.PhotoDao
import com.hako.base.room.dao.UserDao
import com.hako.base.room.entities.AlbumEntity
import com.hako.base.room.entities.PhotoEntity
import com.hako.base.room.entities.UserEntity

@Database(entities = [UserEntity::class, AlbumEntity::class, PhotoEntity::class], version = 1, exportSchema = false)
abstract class BaseDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun albumDao(): AlbumDao
    abstract fun photoDao(): PhotoDao
}