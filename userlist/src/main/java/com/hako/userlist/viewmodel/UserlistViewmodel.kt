package com.hako.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.base.domain.network.RequestStatus
import com.hako.base.domain.Either
import com.hako.base.domain.network.RequestStatus.*
import com.hako.userlist.domain.usecase.GetFavoriteUsers
import com.hako.userlist.domain.usecase.GetUsers
import com.hako.userlist.domain.usecase.SetFavoriteStatus
import com.hako.userlist.model.UserViewable
import org.koin.core.KoinComponent
import org.koin.core.get

class UserlistViewmodel : ViewModel(), KoinComponent {

    val userList = MutableLiveData<Either<Throwable, List<UserViewable>>>()
    val favoriteError = MutableLiveData<Int>()
    val emptyMessage = MutableLiveData<String>()
    val requestStatus = MutableLiveData<RequestStatus>()

    private val getUsers: GetUsers = get()
    private val getFavoriteUsers: GetFavoriteUsers = get()
    private val setFavoriteStatus: SetFavoriteStatus = get()

    fun fetchUsers() {
        getUsers.execute(
            onSuccess = {
                requestStatus.postValue(Ready)
                userList.postValue(Either.Right(it))
            },
            onLoading = {
                requestStatus.postValue(Loading)
            },
            onError = {
                requestStatus.postValue(Errored)
                userList.postValue(Either.Left(it))
            },
            onEmpty = {
                requestStatus.postValue(Empty)
                emptyMessage.postValue("No se encontró ningún usuario")
            })
    }

    fun fetchFavoriteUsers() {
        getFavoriteUsers.execute(
            onSuccess = {
                requestStatus.postValue(Ready)
                userList.postValue(Either.Right(it))
            },
            onError = {
                requestStatus.postValue(Errored)
                userList.postValue(Either.Left(it))
            },
            onEmpty = {
                requestStatus.postValue(Empty)
                emptyMessage.postValue("No tienes favoritos!")
            })
    }

    fun updateUserFavoriteStatus(userId: Int, status: Boolean) {
        setFavoriteStatus.execute(userId, status,
            onError = {
                favoriteError.postValue(it)
            })
    }
}
