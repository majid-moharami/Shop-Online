package com.example.digikala.data.database.carttabledatabase

import androidx.room.Database
import com.example.digikala.data.database.dao.CustomerDao
import com.example.digikala.data.database.entity.Customer

@Database(entities = [Customer::class], version = 1, exportSchema = false)
abstract class CustomerDatabase {

    companion object{
        const val DATABASE_NAME = "cstomer.db"
    }
    abstract fun getCustomerDao() : CustomerDao
}