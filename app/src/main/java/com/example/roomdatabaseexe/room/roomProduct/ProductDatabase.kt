package com.example.roomdatabaseexe.room.roomProduct

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Product::class), version = 12, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract val productDatabaseDao: ProductDatabaseDao

    companion object {

        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ProductDatabase::class.java,
                    "products_database"
                ).build()

                INSTANCE = instance
                return instance
            }
//            synchronized(this) {
//                var instance = INSTANCE
//                if (instance == null) {
//                    instance = databaseBuilder(
//                        context.applicationContext,
//                        ProductDatabase::class.java,
//                        "product_database"
//                    ).fallbackToDestructiveMigration().build()
//                    INSTANCE = instance
//                }
//                return instance
        }
    }
}