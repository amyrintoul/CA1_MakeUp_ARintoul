package com.example.ca1_makeup_arintoul

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ca1_makeup_arintoul.data.ProductEntity
import com.example.ca1_makeup_arintoul.data.SampleDataProvider

//create a public property named productsList
//LiveDate jetpack library
//ProductsList will be an instant of mutable live data. Which means it can be changed at run time
//calling a constructor
class MainViewModel : ViewModel() {

   val productsList = MutableLiveData<List<ProductEntity>>()

    //section of code which will be executed as the class is initialised

    init {
        productsList.value = SampleDataProvider.getProducts()

    }
}