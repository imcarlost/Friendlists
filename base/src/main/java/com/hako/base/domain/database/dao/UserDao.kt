package com.hako.base.domain.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.base.domain.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<UserEntity>)

    @Query("UPDATE ${UserEntity.TABLE_NAME} SET isFavorite = :favorite WHERE id = :id")
    fun saveFavorite(id: Int, favorite: Boolean): Int

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} ORDER BY id ASC")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} WHERE isFavorite = 1 ORDER BY id ASC")
    fun getFavoriteUsers(): List<UserEntity>

    @Query("SELECT COUNT(*) FROM ${UserEntity.TABLE_NAME}")
    fun count(): Int

    @Query("DELETE FROM ${UserEntity.TABLE_NAME}")
    fun nukeDatabase()

}
