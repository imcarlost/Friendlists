package com.hako.albumlist.domain.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.albumlist.model.AlbumEntity

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: AlbumEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<AlbumEntity>)

    @Query("SELECT * FROM ${AlbumEntity.TABLE_NAME} WHERE userId = :userId ORDER BY id ASC")
    fun getAlbums(userId: Int): List<AlbumEntity>

    @Query("SELECT COUNT(*) FROM ${AlbumEntity.TABLE_NAME}")
    fun count(): Int

    @Query("DELETE FROM ${AlbumEntity.TABLE_NAME}")
    fun nukeDatabase()

}
