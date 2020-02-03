package com.hako.userlist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hako.base.domain.network.RequestStatus
import com.hako.base.domain.network.RequestStatus.Ready
import com.hako.base.domain.network.RequestStatus.Loading
import com.hako.base.domain.network.RequestStatus.Errored
import com.hako.base.domain.Either
import com.hako.userlist.domain.usecase.GetUsers
import com.hako.userlist.model.UserViewable
import org.koin.core.KoinComponent
import org.koin.core.get

class UserlistViewmodel : ViewModel(), KoinComponent {

    val data = MutableLiveData<Either<Throwable, List<UserViewable>>>()
    val requestStatus = MutableLiveData<RequestStatus>()

    private val getUsers: GetUsers = get()

    fun fetchUsers() {
        getUsers.execute(
            onSuccess = {
                requestStatus.postValue(Ready)
                data.postValue(Either.Right(it))
            },
            onLoading = {
                requestStatus.postValue(Loading)
            },
            onError = {
                requestStatus.postValue(Errored)
                data.postValue(Either.Left(it))
            })
    }
}
