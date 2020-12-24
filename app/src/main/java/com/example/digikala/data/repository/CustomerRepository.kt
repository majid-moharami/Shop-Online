package com.example.digikala.data.repository

import android.util.Log
import com.example.digikala.data.model.customer.Customer
import com.example.digikala.data.network.retrofit.RetrofitInstance
import com.example.digikala.data.network.retrofit.WooCommerceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

object CustomerRepository {

    private var mRetrofit : Retrofit = RetrofitInstance.registerCustomer()
    private var mWooCommerceService : WooCommerceService = mRetrofit.create(WooCommerceService::class.java)
//    companion object{
//       private val sCustomerRepository : CustomerRepository by lazy{Holder.INSTANCE}
//    }
//
//    private object Holder {
//        val INSTANCE = CustomerRepository()
//    }
    fun postCustomer(customer: Customer){
    val call = mWooCommerceService.registerCustomer(customer)
        call.enqueue(object :Callback<Customer>{
            override fun onResponse(call: Call<Customer>, response: Response<Customer>) {
                if (response.isSuccessful){
                    Log.d("CustomerRepository", "isSuccessful")
                }
            }

            override fun onFailure(call: Call<Customer>, t: Throwable) {
                TODO("Not yet implemented")
                Log.d("CustomerRepository", "!isSuccessful",t.cause)
            }

        } )
    }
}