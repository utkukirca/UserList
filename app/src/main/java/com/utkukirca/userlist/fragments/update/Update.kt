package com.utkukirca.userlist.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.utkukirca.userlist.R
import com.utkukirca.userlist.model.User
import com.utkukirca.userlist.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class Update : Fragment() {

    private val args by navArgs<UpdateArgs>()

    private lateinit var mUserViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updateAge_et.setText(args.currentUser.age.toString())


        view.update_btn.setOnClickListener {
            updateItem()
        }
        //Add menu

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val firstName = updateFirstName_et.text.toString()
        val lastName = updateLastName_et.text.toString()
        val age = Integer.parseInt(updateAge_et.text.toString())

        if(firstName == "" || lastName == "" || age.toString() == ""){
            Toast.makeText(requireContext(),"Please fill out all fields", Toast.LENGTH_LONG).show()
        }else
        {
            // Creating user object

            val updatedUser = User(args.currentUser.uid,firstName,lastName,age)

            // Update current user

            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(),"Updated Succesfully", Toast.LENGTH_LONG).show()

            // Navigate back

            findNavController().navigate(R.id.action_update_to_listFragment)

        }
    }


    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater){
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete ){
             deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val alert = AlertDialog.Builder(requireContext())

        alert.setPositiveButton("Yes" ) {_,_ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),"Deleted Successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_update_to_listFragment)
        }

        alert.setNegativeButton("No"){_,_ ->}
        alert.setTitle("Delete ${args.currentUser.firstName}")
        alert.setMessage("Are you sure to delete ${args.currentUser.firstName} ?")
        alert.create().show()

    }


}