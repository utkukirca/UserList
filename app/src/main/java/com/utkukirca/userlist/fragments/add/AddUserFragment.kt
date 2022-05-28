package com.utkukirca.userlist.fragments.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.utkukirca.userlist.R
import com.utkukirca.userlist.model.User
import com.utkukirca.userlist.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_user.*
import kotlinx.android.synthetic.main.fragment_add_user.view.*


class AddUserFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_user, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = firstName_et.text.toString()
        val lastName = lastName_et.text.toString()
        val age = age_et.text
        if (firstName == "" || lastName == "" || age.toString() == ""){
            Toast.makeText(requireContext(),"Please fill out all fields ",Toast.LENGTH_LONG).show()
        }
        else
        {
            //Creating user object
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
            //Adding to database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Succesfully Added ",Toast.LENGTH_LONG).show()
            //Navigate Back to List Fragment
            findNavController().navigate(R.id.action_addUserFragment_to_listFragment)

        }
    }

    /*
    private fun inputCheck(firstName : String, lastName : String, age : Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
    */



}