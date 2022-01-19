package com.stevejonnunez.hingehomework.model

data class Result<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {
        fun <T> success(data: T?): Result<T> =
            Result(Status.SUCCESS, data, null)

        fun <T> error(message: String, data: T?): Result<T> =
            Result(Status.ERROR, data, message)

        fun <T> loading(data: T? = null): Result<T> =
            Result(Status.LOADING, data, null)
    }

}