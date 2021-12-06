package com.example.ca1_makeup_arintoul

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ca1_makeup_arintoul.data.AppDatabase
import com.example.ca1_makeup_arintoul.data.ProductDao
import com.example.ca1_makeup_arintoul.data.ProductEntity
import com.example.ca1_makeup_arintoul.data.SampleDataProvider
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: ProductDao
    private lateinit var database: AppDatabase

    @Before
    fun createDb(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao =  database.productDao()!! //!! - this has to be non null
    }


    @Test
    fun createProducts() {
        dao.insertAll(SampleDataProvider.getProducts())
        val count = dao.getCount()
        assertEquals(count, SampleDataProvider.getProducts().size)
    }

    @Test
    fun insertProduct(){
        val product = ProductEntity()
        product.name = "some text"
        dao.insertProduct(product)
        val savedProduct = dao.getProductById(1)
        assertEquals(savedProduct?.id ?: 0, 1)
    }
    @After
    //will have just one job which is closing the database.
    fun close(){
        database.close()
    }
}