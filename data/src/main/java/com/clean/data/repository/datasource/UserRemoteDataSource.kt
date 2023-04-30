package com.clean.data.repository.datasource

import com.clean.data.model.UserDto
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    fun getUser(userId: String) : Flow<UserDto>

    fun getUserList() : Flow<List<UserDto>>
}