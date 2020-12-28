package com.example.digikala.data.database.carttabledatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digikala.data.database.dao.CustomerDao
import com.example.digikala.data.database.entity.Customer

@Database(entities = arrayOf(Customer::class), version = 1, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase() {
    abstract fun getCustomerDao(): CustomerDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getDatabase(context: Context): CustomerDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        CustomerDatabase::class.java,
                        "customer.db"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}