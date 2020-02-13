package com.hako.photolist.domain.clients

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hako.photolist.domain.datasource.PhotoDao
import com.hako.photolist.model.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class LocalClient : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}