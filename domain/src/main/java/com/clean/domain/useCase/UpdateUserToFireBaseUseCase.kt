package com.clean.domain.useCase

import com.clean.domain.User
import com.clean.domain.repository.UserRepository
import com.clean.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateUserToFireBaseUseCase @Inject constructor(private val userRepository: UserRepository) {
    operator fun invoke(user:User): Flow<Resource<Unit>> = userRepository.updateUser(user)
}