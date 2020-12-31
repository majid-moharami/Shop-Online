package com.example.digikala.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.digikala.data.model.customer.Customer
import com.example.digikala.data.model.customer.Shipping
import com.example.digikala.databinding.FragmentShippingInfoBinding
import com.example.digikala.viewmodel.CustomerViewModel

class ShippingInfoFragment : Fragment() {
    private lateinit var binding  : FragmentShippingInfoBinding
    private lateinit var mViewModel : CustomerViewModel
    private lateinit var mEmail : String
    private lateinit var mFirstName : String
    private lateinit var mLastName : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
        mEmail = arguments?.let { ShippingInfoFragmentArgs.fromBundle(it).email }.toString()
        mFirstName = arguments?.let { ShippingInfoFragmentArgs.fromBundle(it).firstName }.toString()
        mLastName = arguments?.let { ShippingInfoFragmentArgs.fromBundle(it).lastName }.toString()

        mViewModel.getIsPostCustomerLiveData().observe(this, Observer {
            if (it){
                mViewModel.insert(com.example.digikala.data.database.entity.Customer(mFirstName,mLastName,mEmail))
                Navigation.findNavController(binding.root).navigate(ShippingInfoFragmentDirections.actionShippingInfoFragmentToUserInfoFragment())
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding  = FragmentShippingInfoBinding.inflate(inflater,container,false)
        binding.button.setOnClickListener {
            if (binding.editTextCountry.text?.isEmpty() == true ||
                    binding.editTextAddressOne.text?.isEmpty() == true ||
                    binding.editTextCity.text?.isEmpty() == true ||
                    binding.editTextPostCode.text?.isEmpty() == true ||
                    binding.editTextState.text?.isEmpty() == true ){
                Toast.makeText(context , "جاهای خالی را پر کنید" , Toast.LENGTH_SHORT).show()
            }else{
                var sh = Shipping(mFirstName,mLastName,binding.editTextAddressOne.text.toString(),
                        binding.editTextPostCode.text.toString(),binding.editTextCity.text.toString(),
                        binding.editTextState.text.toString(),binding.editTextCountry.text.toString())
                var customer = Customer(mEmail,mFirstName,mLastName,sh)
                mViewModel.postCustomer(customer)
            }
        }

        binding.buttonMap.setOnClickListener {
            Navigation.findNavController(binding.root)
                    .navigate(ShippingInfoFragmentDirections.actionShippingInfoFragmentToMapFragment())
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ShippingInfoFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}