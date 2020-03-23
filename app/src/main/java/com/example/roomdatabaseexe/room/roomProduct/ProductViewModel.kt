package com.example.roomdatabaseexe.room.roomProduct

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository
    val allProducts: LiveData<List<Product>>
    lateinit var product: LiveData<Product>
    lateinit var productsByPriceRange: LiveData<List<Product>>

    init {
        val databaseDao = ProductDatabase.getDatabase(application).productDatabaseDao
        repository = ProductRepository(databaseDao)
        allProducts = repository.allProducts
    }

    fun insert(product: Product) = viewModelScope.launch {
        repository.insert(product)
    }

    fun update(product: Product) = viewModelScope.launch {
        repository.update(product)
    }

    fun delete(product: Product) = viewModelScope.launch {
        repository.delete(product)
    }

    fun clear() = viewModelScope.launch {
        repository.clear()
    }

    fun getProduct(name: String) = viewModelScope.launch {
        repository.getProduct(name)
        product = repository.product
    }

    fun deleteById(id: Int) = viewModelScope.launch {
        repository.deleteById(id)
    }

    fun getProductByPriceRange(rangeStart: Double, rangeEnd: Double) = viewModelScope.launch {
        repository.getProductByPriceRange(rangeStart, rangeEnd)
        productsByPriceRange = repository.productsByPriceRange
    }
}