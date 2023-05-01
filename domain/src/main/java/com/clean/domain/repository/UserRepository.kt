package com.clean.domain.repository

import com.clean.domain.User
import com.clean.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId: String): Flow<User>
    fun getUserList(): Flow<List<User>>
    fun updateUser(user: User): Flow<Resource<Unit>>
}