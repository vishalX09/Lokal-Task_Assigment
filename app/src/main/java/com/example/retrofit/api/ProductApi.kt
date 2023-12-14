package com.example.retrofit.api

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getProducts(): Response<ApiResponse>

    @GET("/products")
    suspend fun getTags(): Response<ApiResponse>
}
