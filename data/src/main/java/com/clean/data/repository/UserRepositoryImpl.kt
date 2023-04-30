package com.clean.data.repository

import com.clean.data.mappers.toUser
import com.clean.data.repository.datasource.UserRemoteDataSource
import com.clean.domain.User
import com.clean.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {
    override fun getUser(userId: String): Flow<User> {
        return userRemoteDataSource.getUser(userId = userId).map { it.toUser() }
    }

    override fun getUserList(): Flow<List<User>> {
        return userRemoteDataSource.getUserList().map { it.map { it.toUser() } }
    }
}