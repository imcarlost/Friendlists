package com.hako.base.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.base.domain.database.entities.PhotoEntity

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<PhotoEntity>)

    @get:Query("SELECT * FROM ${PhotoEntity.TABLE_NAME}")
    val all: List<PhotoEntity>

    @Query("SELECT * FROM ${PhotoEntity.TABLE_NAME} WHERE albumId = :albumId ORDER BY id ASC")
    fun getPhotos(albumId: Int): List<PhotoEntity>

    @Query("SELECT COUNT(*) FROM ${PhotoEntity.TABLE_NAME}")
    fun count(): Int

    @Query("DELETE FROM ${PhotoEntity.TABLE_NAME}")
    fun nukeDatabase()

}
