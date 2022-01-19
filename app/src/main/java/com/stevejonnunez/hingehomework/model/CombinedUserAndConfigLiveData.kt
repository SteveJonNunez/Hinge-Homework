package com.stevejonnunez.hingehomework.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class CombinedUserAndConfigLiveData(
    userLiveData: LiveData<Result<Users>>,
    configLiveData: LiveData<Result<Config>>
) : MediatorLiveData<Pair<Result<Users>, Result<Config>>>() {

    private var user: Result<Users> = Result.loading()
    private var config: Result<Config> = Result.loading()

    init {
        value = Pair(user, config)
        addSource(userLiveData) {
            if (it != null)
                user = it
            value = Pair(user, config)
        }

        addSource(configLiveData) {
            if (it != null)
                config = it
            value = Pair(user, config)
        }
    }
}