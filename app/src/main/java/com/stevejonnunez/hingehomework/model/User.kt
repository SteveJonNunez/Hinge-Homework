package com.stevejonnunez.hingehomework.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("users")
    val users: List<User>
)

data class User(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("photo")
    val photoURL: String?,
    @SerializedName("gender")
    val gender: Gender?,
    @SerializedName("about")
    val about: String?,
    @SerializedName("school")
    val school: String?,
    @SerializedName("hobbies")
    val hobbies: List<String>?
)

enum class Gender(val shortString: String, val longString: String) {
    @SerializedName("m") MALE("m", "Male"),
    @SerializedName("f") FEMALE("f", "female")
}

