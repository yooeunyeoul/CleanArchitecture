package com.clean.data.repository.datasource

import com.clean.data.model.UserDto
import com.clean.domain.User
import com.clean.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getUser(userId: String) : Flow<UserDto>

    fun getUserList() : Flow<List<UserDto>>

    fun updateUser(user:User) : Flow<Resource<Unit>>
}