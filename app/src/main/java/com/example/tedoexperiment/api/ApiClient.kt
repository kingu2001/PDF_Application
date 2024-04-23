package com.example.tedoexperiment.api

import com.example.tedoexperiment.data.TestDocumentResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiClient {
    private val base_url = "http://10.0.2.2:5297/api/Document/GetAllDocumentInformation/"
        /* "http://localhost:5297/api/Document/GetAllDocumentInformation" */
    private val retrofit : Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService : ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}

interface ApiService{
    @GET("testDocument")
    fun fetchTestDocuments(@Query("testDocument") page:String): Call<TestDocumentResponse>
}