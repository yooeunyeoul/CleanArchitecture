package com.clean.architecture.di

import com.clean.data.repository.UserRepositoryImpl
import com.clean.data.repository.datasource.UserRemoteDataSource
import com.clean.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource = userRemoteDataSource)
    }
}