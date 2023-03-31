package com.example.amphibians

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com/"

@OptIn(kotlinx.serialization.ExperimentalSerializationApi::class)
val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface AmphibianApiService{
    @GET("amphibians")
    suspend fun getAmphibians(): List<AmphibianData>
}

// creates singleton instance of API to ensure that only 1 can be created and used
object AmphibianApi {
    val retrofitService: AmphibianApiService by lazy {
        retrofit.create(AmphibianApiService::class.java)
    }
}