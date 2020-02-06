package com.hako.userlist.domain.usecase

import com.hako.base.domain.database.dao.UserDao
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

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
