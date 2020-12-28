package com.example.digikala.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.digikala.data.database.entity.Customer
import com.example.digikala.data.repository.CustomerRepository

class CustomerViewModel(application: Application) : AndroidViewModel(application) {
    private var mCustomerRepository = CustomerRepository
    init {
        mCustomerRepository.createDatabase(getApplication())
    }
    fun postCustomer(customer: com.example.digikala.data.model.customer.Customer){
        mCustomerRepository.postCustomer(customer)
    }
    fun checkEmailExist(email:String){
        mCustomerRepository.checkEmailExist(email)
    }

    fun getCustomersDatabase() : List<Customer>? {
        return mCustomerRepository.getAllCustomers()
    }

    fun insert(customer:Customer){
        mCustomerRepository.insert(customer)
    }

    fun getIsEmailExistLiveData() : MutableLiveData<Boolean> {
        return mCustomerRepository.getEmailExistLiveData()
    }

    fun getIsPostCustomerLiveData() : MutableLiveData<Boolean>{
        return  mCustomerRepository.getIsPostCustomerLiveData()
    }
    fun getCustomers() : List<com.example.digikala.data.database.entity.Customer>?{
        return mCustomerRepository.getAllCustomers()
    }
}