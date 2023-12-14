package com.example.api.api

import com.example.api.model.Product


data class ApiResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)