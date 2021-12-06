package com.example.ca1_makeup_arintoul

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca1_makeup_arintoul.data.AppDatabase
import com.example.ca1_makeup_arintoul.data.ProductEntity
import com.example.ca1_makeup_arintoul.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//create a public property named productsList
//LiveDate jetpack library
//ProductsList will be an instant of mutable live data. Which means it can be changed at run time
//calling a constructor
class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val database = AppDatabase.getInstance(app)

    val productsList = database?.productDao()?.getAll()

    fun sampleData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val sampleProducts = SampleDataProvider.getProducts()
                database?.productDao()?.insertAll(sampleProducts)
            }
        }

    }

    fun deleteProducts(selectedProducts: List<ProductEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
               database?.productDao()?.deleteProducts(selectedProducts)
            }
        }

    }

    fun deleteAllProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database?.productDao()?.deleteAll() //called the deleteAll function from the DAO.
            }
        }

    }
}