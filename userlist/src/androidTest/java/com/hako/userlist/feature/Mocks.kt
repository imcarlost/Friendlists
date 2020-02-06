package com.hako.userlist.feature

import com.hako.base.domain.database.dao.UserDao
import com.hako.base.domain.database.entities.UserEntity
import com.hako.userlist.domain.datasource.UserlistRemoteApi
import com.hako.userlist.model.User
import io.reactivex.Single

class MockUserDao(private val userList: List<UserEntity>) : UserDao {
    override fun save(entity: UserEntity) {}

    override fun saveAll(entities: List<UserEntity>) {}

    override fun saveFavorite(id: Int, favorite: Boolean) = 1

    override fun getAllUsers() = userList

    override fun getFavoriteUsers() = userList.filter { it.isFavorite }

    override fun count() = userList.count()

    override fun nukeDatabase() {}
}

class MockUserApi(private val userList: List<User>) : UserlistRemoteApi {
    override fun getUsers() = Single.fromCallable { getAllUsers() }

    private fun getAllUsers() = userList
}