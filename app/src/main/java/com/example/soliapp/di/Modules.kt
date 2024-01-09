package com.example.soliapp.di

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.soliapp.domain.IRepository
import com.example.soliapp.data.RepositoryImpl
import com.example.soliapp.data.db.AppDatabase
import com.example.soliapp.data.models.CountryData
import com.example.soliapp.data.network.HolidayApiService
import com.example.soliapp.ui.HolidayViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import java.util.Calendar
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCountryData(context: Application): CountryData {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val cCode = telephonyManager.networkCountryIso
        return CountryData(cCode)
    }


    @Provides
    fun provideCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }


    // Add this method to provide CoroutineDispatcher
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @ViewModelScoped
    fun provideHolidayVM(
        repository: IRepository,
        countryData: CountryData,
        currentYear: Int,
    ): ViewModel {
        return HolidayViewModel(repository, countryData, currentYear)
    }

    @Provides
    @ViewModelScoped
    fun provideHolidayRepository(
        holidayApiService: HolidayApiService,
        database: AppDatabase,
        coroutineDispatcher: CoroutineDispatcher,
    ): IRepository {
        return RepositoryImpl(holidayApiService, database, coroutineDispatcher)
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
