package com.example.digikala.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.digikala.R
import com.example.digikala.databinding.FragmentChoosingCustomerBinding
import com.example.digikala.databinding.FragmentLoadUserBinding
import com.example.digikala.databinding.FragmentPersonalInfoBinding
import com.example.digikala.viewmodel.CustomerViewModel

class ChoosingCustomerFragment : Fragment() {

    private var mViewModel : CustomerViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentChoosingCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (  mViewModel?.getCustomersDatabase() == null || mViewModel!!.getCustomersDatabase()?.isEmpty() == true){
            Navigation.findNavController(view).navigate(ChoosingCustomerFragmentDirections.actionChoosingCustomerFragmentToNavMyDigikalaFragment())

        }else{
            Navigation.findNavController(view).navigate(ChoosingCustomerFragmentDirections.actionChoosingCustomerFragmentToUserInfoFragment())
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ChoosingCustomerFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}