package com.clean.data.repository.datasourceImpl

import android.util.Log
import com.clean.data.model.UserDto
import com.clean.data.repository.datasource.UserRemoteDataSource
import com.clean.domain.User
import com.clean.domain.util.Resource
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserRemoteDataSourceImpl(
    private val ref: DatabaseReference
) : UserRemoteDataSource {
    override fun getUser(userId: String): Flow<UserDto> = callbackFlow {
        ref.child("users").child(userId).get().addOnSuccessListener { snapshout ->

            val user = snapshout.getValue(UserDto::class.java)
            trySend(user ?: UserDto())


        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        awaitClose()
        ref.child("users").child(userId).get()
    }

    override fun getUserList(): Flow<List<UserDto>> =
        callbackFlow {
            ref.child("users").get().addOnSuccessListener { snapshot ->
                val userList = mutableListOf<UserDto>()
                val children = snapshot.children
                for (child in children) {
                    val user = child.getValue(UserDto::class.java)
                    user?.let { user -> userList.add(user) }
                }
                trySend(userList)
            }.addOnFailureListener {
                Log.e("firebase", "Error getting data", it)
            }
            awaitClose()
        }

    override fun updateUser(user: User): Flow<Resource<Unit>> = callbackFlow {
        when {
            user.userName?.isNotEmpty() == true -> {
                ref.child("users").child(user.userId ?: "emptyUserId").child("userName")
                    .setValue(user.userName)
                    .addOnSuccessListener {
                        Resource.Success(Unit)
                    }
                    .addOnFailureListener {e->
                        Resource.Error(message =e.message?:"",data = null )
                    }

            }

            user.gender != null -> {
                ref.child("users").child(user.userId ?: "emptyUserId").child("gender")
                    .setValue(user.gender)
                    .addOnSuccessListener {
                        Resource.Success(Unit)
                    }
                    .addOnFailureListener {e->
                        Resource.Error(message =e.message?:"",data = null )
                    }
            }
        }

        awaitClose()
    }

}