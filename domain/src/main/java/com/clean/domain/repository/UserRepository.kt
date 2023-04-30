package com.clean.domain.repository

import com.clean.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(userId:String): Flow<User>
}