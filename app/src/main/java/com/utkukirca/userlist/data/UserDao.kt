package com.utkukirca.userlist.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.utkukirca.userlist.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser( user : User)

    @Update
    fun updateUser(user:User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY uid ASC")
    fun readAllData(): LiveData<List<User>>

}