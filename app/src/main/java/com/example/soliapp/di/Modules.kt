package com.example.soliapp.di

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager
import androidx.room.Room
import com.example.soliapp.domain.IRepository
import com.example.soliapp.data.RepositoryImpl
import com.example.soliapp.data.db.AppDatabase
import com.example.soliapp.data.models.CountryData
import com.example.soliapp.data.network.HolidayApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountryData(context: Application): CountryData {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val cCode = telephonyManager.networkCountryIso
        return CountryData(cCode)
    }


    @Provides
    @Singleton
    fun provideCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
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
        database: AppDatabase
    ): IRepository {
        return RepositoryImpl(holidayApiService, database)
    }

    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

}
