package com.example.roomdatabaseexe.room.roomProduct

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(itemProduct: Product)

    @Update
    suspend fun update(itemProduct: Product)

    @Delete
    suspend fun delete(itemProduct: Product)

    @Query("DELETE FROM product_table")
    suspend fun clear()

    @Query("SELECT * FROM product_table ORDER BY product_name DESC")
    fun getAllProducts(): LiveData<List<Product>>

    @Query("SELECT * FROM product_table WHERE product_name = :name")
    fun getProduct(name: String): LiveData<Product>

    @Query("DELETE FROM product_table WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM product_table WHERE product_price BETWEEN :rangeStart and :rangeEnd")
    fun getProductByPriceRange(rangeStart: Double, rangeEnd: Double): LiveData<List<Product>>
}