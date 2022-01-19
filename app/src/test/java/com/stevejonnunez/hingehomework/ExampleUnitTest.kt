package com.stevejonnunez.hingehomework

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        val json = "{\"users\": [{\"id\":1, \"name\":\"sam\"},{\"id\":2, \"name\":\"steve\"}]}"

        val cool = Gson().fromJson(json, Test2::class.java)
    }

    data class Test2(
        @SerializedName("users")
        val list: List<Test21>,
    )

    data class Test21(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}