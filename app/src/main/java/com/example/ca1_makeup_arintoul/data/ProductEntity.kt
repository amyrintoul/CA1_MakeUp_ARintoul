package com.example.ca1_makeup_arintoul.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ca1_makeup_arintoul.NEW_PRODUCT_ID
import java.io.StringBufferInputStream
import java.util.*


@Entity(tableName = "products")
//add data so the class is going to have some property's
data class ProductEntity(
    //declaring the property's of the class.
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var date: Date,
    var name: String,
    var description: String,
    var price: Int,
    var category: String,
    var brand: String

) {

    //Products arguments constructor.
    constructor() : this(NEW_PRODUCT_ID, Date(), "", "", 0, "", "")

    //Second primary constructor, don't know the ID for.
    constructor(
        date: Date,
        name: String,
        description: String,
        price: Int,
        category: String,
        brand: String
    ) :
            this(NEW_PRODUCT_ID, date, name, description, price, category, brand)

}