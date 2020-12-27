package com.example.digikala.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer.table")
data class Customer(
        @PrimaryKey(autoGenerate = true) var mId : Int,
        var mFistName : String? ,
        var mLastName : String? ,
        var mEmail : String?
        )
