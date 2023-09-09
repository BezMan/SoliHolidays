package com.example.soliapp.di

import com.example.soliapp.HolidayRepository
import com.example.soliapp.HolidayRepositoryImpl
import com.example.soliapp.data.HolidayApiService
import com.example.soliapp.data.HolidayDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String {
        return "https://date.nager.at/"
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // Add any OkHttpClient configurations here (e.g., timeouts, interceptors)
            .build()
    }

    @Provides
    @Singleton
    fun provideHolidayApiService(okHttpClient: OkHttpClient): HolidayApiService {
        return HolidayApiService(okHttpClient)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHolidayRepository(
        holidayApiService: HolidayApiService,
        holidayDao: HolidayDao
    ): HolidayRepository {
        return HolidayRepositoryImpl(holidayApiService, holidayDao)
    }

    // Other repository-related dependencies can be provided here
}
