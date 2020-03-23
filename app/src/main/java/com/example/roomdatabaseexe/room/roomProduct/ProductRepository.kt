package com.example.roomdatabaseexe.room.roomProduct

import androidx.lifecycle.LiveData

class ProductRepository(private val productDatabaseDao: ProductDatabaseDao) {
    val allProducts = productDatabaseDao.getAllProducts()
    lateinit var product: LiveData<Product>
    lateinit var productsByPriceRange: LiveData<List<Product>>

    suspend fun insert(product: Product) {
        productDatabaseDao.insert(product)
    }

    suspend fun update(product: Product) {
        productDatabaseDao.update(product)
    }

    suspend fun delete(product: Product) {
        productDatabaseDao.delete(product)
    }

    suspend fun clear() {
        productDatabaseDao.clear()
    }

    fun getProduct(name: String) {
        product = productDatabaseDao.getProduct(name)
    }

    suspend fun deleteById(id: Int) {
        productDatabaseDao.deleteById(id)
    }

    fun getProductByPriceRange(rangeStart: Double, rangeEnd: Double) {
       productsByPriceRange = productDatabaseDao.getProductByPriceRange(rangeStart, rangeEnd)
    }

}