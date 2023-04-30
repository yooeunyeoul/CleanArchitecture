package com.clean.data.repository

import com.clean.data.repository.datasource.UserRemoteDataSource
import com.clean.domain.User
import com.clean.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource : UserRemoteDataSource
) : UserRepository {
    override fun getUser(userId:String): Flow<User> {
        return userRemoteDataSource.getUser(userId = userId)
    }
}