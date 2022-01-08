package com.example.ca1_makeup_arintoul.api

import com.example.ca1_makeup_arintoul.data.ProductEntity
import com.example.ca1_makeup_arintoul.models.Products
import com.google.android.gms.analytics.ecommerce.Product
import retrofit2.http.GET


interface MakeupApi {
    @GET("products.json")
    suspend fun getProducts(): List<Products>

}