package com.app.ecom.hilt

import com.app.ecom.common.Constants
import com.app.ecom.data.remote.ApiService
import com.app.ecom.data.repository.HomeRepistoryImpl
import com.app.ecom.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object HiltModules {
    @Provides
    @Singleton
    @Named("HomeAPI")
    fun provideAPI(): ApiService{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }


    @Provides
    fun provideHomeRepository(@Named("HomeAPI") apiService: ApiService): HomeRepository{
        return HomeRepistoryImpl(apiService)
    }
}