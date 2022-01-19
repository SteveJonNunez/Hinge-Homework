package com.stevejonnunez.hingehomework.data

import com.stevejonnunez.hingehomework.api.HingeProfileService
import com.stevejonnunez.hingehomework.model.Config
import com.stevejonnunez.hingehomework.model.Users
import com.stevejonnunez.hingehomework.model.Result
import retrofit2.Response
import javax.inject.Inject

class HingeProfileDataSource @Inject constructor(private val hingeProfileService: HingeProfileService) {
    suspend fun fetchUsers(): Result<Users> {
        return getResponse(
            request = { hingeProfileService.fetchUsers() }
        )
    }

    suspend fun fetchConfig(): Result<Config> {
        return getResponse (
            request = { hingeProfileService.fetchConfig() }
        )
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                Result.error("${result.code()}: ${result.message()}", null)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error: $e", null)
        }
    }
}