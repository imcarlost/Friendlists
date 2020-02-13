package com.hako.userlist.domain.usecase

import com.hako.userlist.domain.datasource.UserDao
import com.hako.userlist.domain.datasource.UserlistRemoteApi
import com.hako.userlist.model.User
import com.hako.userlist.model.toUserEntity
import com.hako.userlist.model.toUserViewable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetUsers(private val dao: UserDao, private val api: UserlistRemoteApi) {

    fun execute(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit,
        onLoading: () -> Unit,
        onEmpty: () -> Unit
    ) {
        Single.fromCallable { dao.getAllUsers() }
            .subscribeOn(Schedulers.io())
            .doOnError { onError(it) }
            .doOnSuccess { dbUsers ->
                if (dbUsers.isEmpty()) {
                    api.getUsers()
                        .doOnSuccess {
                            if (it.isEmpty()) {
                                onEmpty()
                            } else {
                                dao.saveAll(it.map { user -> user.toUserEntity() })
                                onSuccess(dao.getAllUsers().map { user -> user.toUserViewable() })
                            }
                        }
                        .doOnSubscribe { onLoading() }
                        .subscribeOn(Schedulers.io())
                        .subscribe({}, { onError(it) })
                } else {
                    onSuccess(dbUsers.map { it.toUserViewable() })
                }
            }
            .subscribe()
    }
}
