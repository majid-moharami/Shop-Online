package com.example.digikala.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.digikala.data.database.entity.Customer

@Dao
interface CustomerDao {
    @Insert
    fun insert(customer: Customer)

    @Delete
    fun delete(customer: Customer)

    @Query("SELECT * FROM `customer.table`")
    fun getAllCustomers(): List<Customer>

}