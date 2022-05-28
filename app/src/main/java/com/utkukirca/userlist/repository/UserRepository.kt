package com.utkukirca.userlist.repository

import androidx.lifecycle.LiveData
import com.utkukirca.userlist.data.UserDao
import com.utkukirca.userlist.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllData()

    fun addUser(user : User){
        userDao.addUser(user)
    }

    fun updateUser(user: User){
        userDao.updateUser(user)
    }

    fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    fun deleteAllUsers(){
       userDao.deleteAllUsers()
    }

}