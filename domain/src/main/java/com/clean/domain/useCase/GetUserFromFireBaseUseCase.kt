package com.clean.domain.useCase

import com.clean.domain.User
import com.clean.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserFromFireBaseUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(userId:String): Flow<User> = userRepository.getUser(userId)
}