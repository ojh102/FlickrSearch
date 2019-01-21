package com.github.ojh102.flickersearch.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.github.ojh102.flickersearch.data.local.FlickerDatabaseService
import com.github.ojh102.flickersearch.data.local.FlickerDatabaseServiceImpl
import com.github.ojh102.flickersearch.data.local.FlickerRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule.ProvideModule::class])
internal interface DatabaseModule {
    companion object {
        private const val DB_NAME = "flicker-db"
    }

    @Module
    class ProvideModule {
        @Provides
        @Singleton
        fun provideSharedPreferences(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        @Provides
        @Singleton
        fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
            return sharedPreferences.edit()
        }

        @Provides
        @Singleton
        fun provideFlickerRoomDatabase(context: Context): FlickerRoomDatabase {
            return Room.databaseBuilder(context, FlickerRoomDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    @Binds
    @Singleton
    fun bindFlickerDatabaseService(flickerDatabaseServiceImpl: FlickerDatabaseServiceImpl): FlickerDatabaseService
}