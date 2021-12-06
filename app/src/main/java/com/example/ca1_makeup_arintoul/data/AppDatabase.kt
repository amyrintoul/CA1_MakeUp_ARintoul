package com.example.ca1_makeup_arintoul.data

import android.content.Context
import androidx.room.*
import java.security.AccessControlContext
import java.util.*


//annotations
@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
//extends a class called roomDatabase
//abstract class
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao?

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "products.db"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}