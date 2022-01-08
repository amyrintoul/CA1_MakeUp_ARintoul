package com.example.ca1_makeup_arintoul.models


import com.example.ca1_makeup_arintoul.data.ProductEntity
import java.util.*

data class Products(
    //declaring the property's of the class.

    var id: Int,
    var name: String,
    var description: String,
    var price: Int,
    var category: String,
    var brand: String

)