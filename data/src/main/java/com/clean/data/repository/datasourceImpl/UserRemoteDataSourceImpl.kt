package com.clean.data.repository.datasourceImpl

import android.util.Log
import com.clean.data.model.UserDto
import com.clean.data.repository.datasource.UserRemoteDataSource
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class UserRemoteDataSourceImpl(
    private val ref: DatabaseReference
) : UserRemoteDataSource {
    override fun getUser(userId: String): Flow<UserDto> = callbackFlow {
        ref.child("users").child(userId).get().addOnSuccessListener {
            trySend(it.value as UserDto)
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        awaitClose {

        }
    }

    override fun getUserList(): Flow<List<UserDto>> =
        callbackFlow {
            ref.child("users").get().addOnSuccessListener {
                val value = it.value
                Log.d("value", value.toString())
            }.addOnFailureListener {

            }
        }
}