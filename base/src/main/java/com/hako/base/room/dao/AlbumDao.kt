package com.hako.base.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.base.room.entities.AlbumEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<AlbumEntity>)

    @get:Query("SELECT * FROM ${AlbumEntity.TABLE_NAME}")
    val all: List<AlbumEntity>

    @Query("SELECT COUNT(*) FROM ${AlbumEntity.TABLE_NAME}")
    fun count(page: Int): Int

    @Query("DELETE FROM ${AlbumEntity.TABLE_NAME}")
    fun nukeDatabase()

}
