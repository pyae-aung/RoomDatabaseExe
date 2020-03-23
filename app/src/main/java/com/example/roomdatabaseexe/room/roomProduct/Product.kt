package com.example.roomdatabaseexe.room.roomProduct

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "product_name")
    var name: String? = "",
    @ColumnInfo(name = "product_price")
    var price: Double = 0.0,
    @ColumnInfo(name = "product_quantity")
    var quantity: Int = 0
)