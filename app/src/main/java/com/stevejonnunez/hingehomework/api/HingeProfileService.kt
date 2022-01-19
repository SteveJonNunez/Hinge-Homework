package com.stevejonnunez.hingehomework.api

import com.stevejonnunez.hingehomework.model.Config
import com.stevejonnunez.hingehomework.model.Users
import retrofit2.Response
import retrofit2.http.GET

interface HingeProfileService {

    @GET("users")
    suspend fun fetchUsers(): Response<Users>

    @GET("config")
    suspend fun fetchConfig(): Response<Config>
}