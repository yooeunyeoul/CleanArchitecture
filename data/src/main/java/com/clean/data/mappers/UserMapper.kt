package com.clean.data.mappers

import com.clean.data.model.UserDto
import com.clean.domain.User

fun UserDto.toUser():User {
    return User(userId = userId, userName = userName, email, gender = gender)
}
