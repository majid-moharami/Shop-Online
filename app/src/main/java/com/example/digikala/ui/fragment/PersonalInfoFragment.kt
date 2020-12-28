package com.example.digikala.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.digikala.R
import com.example.digikala.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {

    private lateinit var mBinding:FragmentPersonalInfoBinding
    private var email : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        email = arguments?.let { PersonalInfoFragmentArgs.fromBundle(it).email}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            if (binding.editTextFirstName.text?.isEmpty() == true ||
                    binding.editTextLastName.text?.isEmpty() == true  ||
                    binding.editTextPassword.text?.isEmpty() == true ){
                Toast.makeText(context , "جاهای خالی را پر کنید" ,Toast.LENGTH_SHORT).show()
            }else{
               var action : PersonalInfoFragmentDirections.ActionPersonalInfoFragmentToShippingInfoFragment =
                       PersonalInfoFragmentDirections.actionPersonalInfoFragmentToShippingInfoFragment(
                               email!!,binding.editTextFirstName.text.toString(),binding.editTextLastName.text.toString())
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic fun newInstance(param1: String, param2: String) =
                PersonalInfoFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}