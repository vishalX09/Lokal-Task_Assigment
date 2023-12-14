package com.example.api

import com.example.api.api.ProductInterface
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiInstance {
    val api: ProductInterface by lazy {

        Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ProductInterface::class.java)
    }
}