package com.github.ojh102.flickrsearch.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.ojh102.flickrsearch.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.plug.android.di.qualifier.HttpLogging
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [HttpModule.ProvideModule::class])
internal interface HttpModule {
    @Module
    class ProvideModule {
        companion object {
            const val TIMEOUT = 20L
        }

        @Provides
        @HttpLogging
        fun provideHttpLoggingInterceptor(): Interceptor {
            return HttpLoggingInterceptor().apply {
                level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        }


        @Provides
        @Singleton
        fun provideOkHttpClient(
                @HttpLogging httpLoggingInterceptor: Interceptor

        ): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addNetworkInterceptor(httpLoggingInterceptor)

            if(BuildConfig.DEBUG) {
                okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
            }

            return okHttpClientBuilder.build()
        }

        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder()
                    .setPrettyPrinting()
                    .create()
        }
    }

}