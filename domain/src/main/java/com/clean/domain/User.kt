package com.clean.domain

data class User(
    val userId: Int,
    val userName: String? = null,
    val email: String? = null,
    val gender: Int? = 0
) {


}