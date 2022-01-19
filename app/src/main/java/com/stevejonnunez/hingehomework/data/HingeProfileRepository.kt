package com.stevejonnunez.hingehomework.data

import com.stevejonnunez.hingehomework.model.Config
import com.stevejonnunez.hingehomework.model.Users
import com.stevejonnunez.hingehomework.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HingeProfileRepository @Inject constructor(private val hingeProfileDataSource: HingeProfileDataSource) {
    suspend fun fetchUsers(): Flow<Result<Users>> {
        return flow {
            emit(Result.loading())
            val result = hingeProfileDataSource.fetchUsers()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchConfig(): Flow<Result<Config>> {
        return flow {
            emit(Result.loading())
            val result = hingeProfileDataSource.fetchConfig()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }
}