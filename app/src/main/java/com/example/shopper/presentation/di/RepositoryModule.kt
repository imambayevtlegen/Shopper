package com.example.shopper.presentation.di

import com.example.shopper.data.repository.ShopRepositoryImpl
import com.example.shopper.data.repository.datasource.ShopLocalDataSource
import com.example.shopper.data.repository.datasource.ShopRemoteDataSource
import com.example.shopper.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesShopRepository(shopRemoteDataSource: ShopRemoteDataSource, localDataSource: ShopLocalDataSource): ShopRepository{
        return ShopRepositoryImpl(shopRemoteDataSource, localDataSource)
    }
}