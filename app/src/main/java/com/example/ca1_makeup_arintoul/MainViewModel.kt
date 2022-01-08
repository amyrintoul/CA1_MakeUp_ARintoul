package com.example.ca1_makeup_arintoul

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.ca1_makeup_arintoul.api.RetrofitInstance
import com.example.ca1_makeup_arintoul.data.AppDatabase
import com.example.ca1_makeup_arintoul.data.ProductEntity
import com.example.ca1_makeup_arintoul.data.SampleDataProvider
import com.google.android.gms.analytics.ecommerce.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//create a public property named productsList
//LiveDate jetpack library
//ProductsList will be an instant of mutable live data. Which means it can be changed at run time
//calling a constructor

class MainViewModel(app: Application) : AndroidViewModel(app) {


    private val _products: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>>
        get() = _products

    private val _isLoading =  MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val database = AppDatabase.getInstance(app)
    val productsList = database?.productDao()?.getAll()

    init{
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val fetchedProducts = RetrofitInstance.api.getProducts()
                Log.i(TAG, "Fetched products: $fetchedProducts")
       //         database?.productDao()?.insertAll(fetchedProducts.products as ArrayList<ProductEntity>)
            }
        }

    }

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