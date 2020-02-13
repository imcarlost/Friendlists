package com.hako.albumlist.domain.clients

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hako.albumlist.domain.datasource.AlbumDao
import com.hako.albumlist.model.AlbumEntity

@Database(entities = [AlbumEntity::class], version = 1, exportSchema = false)
abstract class LocalClient : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}