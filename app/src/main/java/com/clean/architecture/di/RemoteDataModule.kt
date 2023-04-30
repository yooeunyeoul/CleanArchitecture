package com.clean.architecture.di

import com.clean.data.repository.UserRepositoryImpl
import com.clean.data.repository.datasource.UserRemoteDataSource
import com.clean.data.repository.datasourceImpl.UserRemoteDataSourceImpl
import com.clean.domain.repository.UserRepository
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(ref: DatabaseReference): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(ref = ref)
    }
}