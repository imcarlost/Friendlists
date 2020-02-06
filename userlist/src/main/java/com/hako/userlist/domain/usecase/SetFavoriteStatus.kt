package com.hako.userlist.domain.usecase

import com.hako.base.domain.database.dao.UserDao
import com.hako.base.extensions.wasUpdated
import com.hako.userlist.domain.datasource.UserlistRemoteApi
import com.hako.userlist.model.UserViewable
import com.hako.userlist.model.toUserEntity
import com.hako.userlist.model.toUserViewable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.get

class SetFavoriteStatus(private val dao: UserDao) {

    fun execute(
        userId: Int,
        favoriteStatus: Boolean,
        onError: (userId: Int) -> Unit
    ) {
        Single.fromCallable { dao.saveFavorite(userId, favoriteStatus) }
            .subscribeOn(Schedulers.io())
            .doOnError { onError(userId) }
            .subscribe()
    }
}
