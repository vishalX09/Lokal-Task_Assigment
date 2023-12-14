package com.example.retrofit.api

import com.example.retrofit.model.Products

data class ApiResponse(
    val products: List<Products>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
