package com.sagamagus.mediacatalog.data.repository

import com.sagamagus.mediacatalog.data.repository.ShowRepositoryImpl
import com.sagamagus.mediacatalog.domain.repository.ShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindShowRepository(
        impl: ShowRepositoryImpl
    ): ShowRepository
}