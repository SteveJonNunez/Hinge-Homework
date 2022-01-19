package com.stevejonnunez.hingehomework.dependencyInjection

import com.stevejonnunez.hingehomework.dependencyInjection.qualifier.BaseEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object EndpointModule {
    @BaseEndpoint
    @Provides
    fun endpoint() =
        "http://hinge-ue1-dev-cli-android-homework.s3-website-us-east-1.amazonaws.com/"
}