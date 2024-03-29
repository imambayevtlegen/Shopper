package com.example.shopper.presentation.di

import com.example.shopper.presentation.adapter.CartAdapter
import com.example.shopper.presentation.adapter.HomeAdapter
import com.example.shopper.presentation.adapter.SearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun providesHomeAdapter(): HomeAdapter {
        return HomeAdapter()
    }

    @Singleton
    @Provides
    fun providesCartAdapter(): CartAdapter {
        return CartAdapter()
    }

    @Singleton
    @Provides
    fun providesSearchAdapter(): SearchAdapter {
        return SearchAdapter()
    }

}