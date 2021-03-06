package com.example.ca1_makeup_arintoul.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {
    //insert a product
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: ProductEntity)

    //ignore a product
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(products: List<ProductEntity>)

    //returns a live data object.
    @Query("SELECT * FROM products ORDER BY date ASC")
    fun getAll(): LiveData<List<ProductEntity>>

    //return a single note object
    @Query("SELECT * FROM products WHERE id = :id")
    fun getProductById(id: Int): ProductEntity?

    //query the database and find out how many rows there are.
    @Query("SELECT COUNT(*) from products")
    fun getCount(): Int

    @Delete //delete selected products
    fun deleteProducts(selectedProducts: List<ProductEntity>): Int

    @Query("DELETE FROM products") //deletes all products
    fun deleteAll():Int

}