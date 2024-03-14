package com.example.shopper.presentation.di

import com.example.shopper.data.api.ShopApiService
import com.example.shopper.data.db.ShopDAO
import com.example.shopper.data.repository.datasource.ShopLocalDataSource
import com.example.shopper.data.repository.datasource.ShopRemoteDataSource
import com.example.shopper.data.repository.datasourceImpl.ShopLocalDataSourceImpl
import com.example.shopper.data.repository.datasourceImpl.ShopRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(shopDAO: ShopDAO): ShopLocalDataSource {
        return ShopLocalDataSourceImpl(shopDAO)
    }

    @Singleton
    @Provides
    fun providesShopRemoteDataSource(shopApiService: ShopApiService): ShopRemoteDataSource {
        return ShopRemoteDataSourceImpl(shopApiService)
    }
}