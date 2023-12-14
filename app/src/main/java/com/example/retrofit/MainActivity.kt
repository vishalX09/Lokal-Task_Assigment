package com.example.retrofit

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.Adapters.ProductAdapter
import com.example.retrofit.api.RetrofitInstance
import com.example.retrofit.databinding.ActivityMainBinding
import okio.IOException

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getProducts()
            } catch (d: IOException) {
                Log.d("MainActivity", "IOException, you might not have internet connection")
                return@launchWhenCreated
            } catch (d: HttpException) {
                Log.d("MainActivity", "HttpException, unexpected responses")
                return@launchWhenCreated
            }
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse != null) {
                    productAdapter.products = apiResponse.products
                } else {
                    Log.d("MainActivity", "ApiResponse is null")
                }
            } else {
                Log.d("MainActivity", "Response not successful. Code: ${response.code()}")
            }
        }
    }

    private fun setupRecyclerView() = binding.rvProducts.apply {
        productAdapter = ProductAdapter()
        adapter = productAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }

}
