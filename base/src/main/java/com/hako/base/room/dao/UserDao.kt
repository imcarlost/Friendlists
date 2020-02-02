package com.hako.base.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hako.base.room.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entity: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(entities: List<UserEntity>)

    @get:Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    val all: List<UserEntity>

    @Query("SELECT COUNT(*) FROM ${UserEntity.TABLE_NAME}")
    fun count(): Int

    @Query("DELETE FROM ${UserEntity.TABLE_NAME}")
    fun nukeDatabase()

}
