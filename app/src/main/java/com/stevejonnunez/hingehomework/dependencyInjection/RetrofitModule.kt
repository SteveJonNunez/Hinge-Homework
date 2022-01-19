package com.stevejonnunez.hingehomework.dependencyInjection

import com.stevejonnunez.hingehomework.dependencyInjection.qualifier.BaseEndpoint
import com.stevejonnunez.hingehomework.api.HingeProfileService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    fun hingeRetrofit(@BaseEndpoint base_endpoint: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(base_endpoint)
            .build()
    }

    @Provides
    fun hingeProfileService(retrofit: Retrofit): HingeProfileService {
        return retrofit.create(HingeProfileService::class.java)
    }
}