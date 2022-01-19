package com.stevejonnunez.hingehomework.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevejonnunez.hingehomework.data.HingeProfileRepository
import com.stevejonnunez.hingehomework.model.CombinedUserAndConfigLiveData
import com.stevejonnunez.hingehomework.model.Config
import com.stevejonnunez.hingehomework.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.stevejonnunez.hingehomework.model.Result


@HiltViewModel
class HingeProfileViewModel @Inject constructor(private val hingeProfileRepository: HingeProfileRepository) :
    ViewModel() {

    private val users = MutableLiveData<Result<Users>>()
    private val config = MutableLiveData<Result<Config>>()

    init {
        fetchUsers()
        fetchConfig()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            hingeProfileRepository.fetchUsers().collect {
                users.value = it
            }
        }
    }

    private fun fetchConfig() {
        viewModelScope.launch {
            hingeProfileRepository.fetchConfig().collect {
                config.value = it
            }
        }
    }

    fun getAllData(): CombinedUserAndConfigLiveData {
        return CombinedUserAndConfigLiveData(users, config)
    }
}