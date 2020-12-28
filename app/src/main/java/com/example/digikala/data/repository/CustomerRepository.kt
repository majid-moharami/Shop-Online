package com.example.digikala.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.digikala.data.database.carttabledatabase.CustomerDatabase
import com.example.digikala.data.model.customer.Customer
import com.example.digikala.data.network.retrofit.RetrofitInstance
import com.example.digikala.data.network.retrofit.WooCommerceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CustomerRepository {

    private var mWooCommerceService: WooCommerceService =
            RetrofitInstance.registerCustomer().create(WooCommerceService::class.java)
    private var isEmailExistLiveData = MutableLiveData<Boolean>()
    private var isPostCustomerLiveData = MutableLiveData<Boolean>()

    private var mDatabase : CustomerDatabase? = null

    var customer: Customer? = null

    fun createDatabase(context : Context){
        mDatabase = Room.databaseBuilder(
                context.applicationContext,
                CustomerDatabase::class.java,
                "customer.db"
        ).allowMainThreadQueries().build()
    }

    fun postCustomer(customer: Customer) {
        val call = mWooCommerceService.registerCustomer(customer)
        call.enqueue(object : Callback<Customer> {
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                isPostCustomerLiveData.value = response.isSuccessful

            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                Log.d("CustomerRepository", "!isSuccessful", t.cause)
            }

        })
    }

    fun checkEmailExist(email:String){
        val call = mWooCommerceService.getCustomerByEmail(email)
        call.enqueue(object : Callback<List<Customer>>{
            override fun onResponse(call: Call<List<Customer>>, response: Response<List<Customer>>) {
                if (response.isSuccessful){
                    if (response.body()?.isEmpty() == true) {
                        isEmailExistLiveData.value = true
                    }else   isEmailExistLiveData.value = false
                }
            }

            override fun onFailure(call: Call<List<Customer>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })
    }

    fun buildDatabase(context : Context){
        mDatabase = CustomerDatabase.getDatabase(context)
    }

    fun insert(customer: com.example.digikala.data.database.entity.Customer){
        mDatabase?.getCustomerDao()?.insert(customer)
    }

    fun getAllCustomers(): List<com.example.digikala.data.database.entity.Customer>? {
        return mDatabase?.getCustomerDao()?.getAllCustomers()
    }

    fun getEmailExistLiveData() : MutableLiveData<Boolean>{
        return isEmailExistLiveData
    }


    fun getIsPostCustomerLiveData() : MutableLiveData<Boolean>{
        return isPostCustomerLiveData
    }
}
