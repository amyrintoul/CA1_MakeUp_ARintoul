package com.example.ca1_makeup_arintoul

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca1_makeup_arintoul.data.AppDatabase
import com.example.ca1_makeup_arintoul.data.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel(app: Application) : AndroidViewModel(app) {


    private val database = AppDatabase.getInstance(app)
    val currentProduct = MutableLiveData<ProductEntity>()

    fun getProductById(productId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val product =
                    if(productId != NEW_PRODUCT_ID) {
                        database?.productDao()?.getProductById(productId)
                    }else {
                        ProductEntity()
                    }
                currentProduct.postValue(product)
            }
        }
    }
}