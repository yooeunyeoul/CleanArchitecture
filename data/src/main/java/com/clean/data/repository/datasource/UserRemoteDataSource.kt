package com.clean.data.repository.datasource

import com.clean.domain.User
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getUser(userId: String) : Flow<User>
}