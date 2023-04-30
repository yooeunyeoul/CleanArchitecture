package com.clean.data.repository.datasourceImpl

import android.util.Log
import com.clean.data.repository.datasource.UserRemoteDataSource
import com.clean.domain.User
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val ref: DatabaseReference
) : UserRemoteDataSource {
    override fun getUser(userId: String): Flow<User> = callbackFlow {
        ref.child("users").child(userId).get().addOnSuccessListener {
            trySend(it.value as User)
        }.addOnFailureListener {
            Log.e("firebase", "Error getting data", it)
        }
        awaitClose{

        }
    }

}