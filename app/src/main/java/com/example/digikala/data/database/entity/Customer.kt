package com.example.digikala.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer.table")
data class Customer(
        var mFistName : String? ,
        var mLastName : String? ,
        var mEmail : String?
        ){
        @PrimaryKey(autoGenerate = true) var mId : Int?=null
}
