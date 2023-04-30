package com.clean.data.model

data class UserDto(
    val userId: Int,
    val userName: String? = null,
    val email: String? = null,
    val gender: Int? = 0
) {


}