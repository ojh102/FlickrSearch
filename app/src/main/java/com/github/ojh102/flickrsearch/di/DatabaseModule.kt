package com.github.ojh102.flickrsearch.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import com.github.ojh102.flickrsearch.data.local.FlickrDatabaseService
import com.github.ojh102.flickrsearch.data.local.FlickrDatabaseServiceImpl
import com.github.ojh102.flickrsearch.data.local.FlickrRoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule.ProvideModule::class])
internal interface DatabaseModule {
    companion object {
        private const val DB_NAME = "flickr-db"
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
        fun provideFlickrRoomDatabase(context: Context): FlickrRoomDatabase {
            return Room.databaseBuilder(context, FlickrRoomDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    @Binds
    @Singleton
    fun bindFlickrDatabaseService(flickrDatabaseServiceImpl: FlickrDatabaseServiceImpl): FlickrDatabaseService
}