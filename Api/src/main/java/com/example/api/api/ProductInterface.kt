package com.example.api.api

import retrofit2.Call
import retrofit2.http.GET

interface ProductInterface {
    @GET("/products")
    suspend fun getProducts(): Call<ApiResponse>
}


