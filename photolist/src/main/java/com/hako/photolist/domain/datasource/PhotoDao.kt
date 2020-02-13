package com.hako.photolist.domain.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.photolist.model.PhotoEntity

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<PhotoEntity>)

    @Query("SELECT * FROM ${PhotoEntity.TABLE_NAME} WHERE albumId = :albumId ORDER BY id ASC")
    fun getPhotos(albumId: Int): List<PhotoEntity>

    @Query("SELECT COUNT(*) FROM ${PhotoEntity.TABLE_NAME}")
    fun count(): Int

    @Query("DELETE FROM ${PhotoEntity.TABLE_NAME}")
    fun nukeDatabase()

}
