package com.stevejonnunez.hingehomework.model

import com.google.gson.annotations.SerializedName

data class Config(
    @SerializedName("profile")
    val profile: List<String>
)