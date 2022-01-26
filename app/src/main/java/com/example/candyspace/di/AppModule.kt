package com.example.candyspace.di

import com.example.candyspace.data.Constant.BASE_URL
import com.example.candyspace.data.remote.StackExchangeApi
import com.example.candyspace.data.remote.StackExchangeRemoteDataSource
import com.example.candyspace.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): StackExchangeApi {
        return retrofit.create(StackExchangeApi::class.java)

    }

    @Provides
    fun provideNetworkDataSource(api: StackExchangeApi): StackExchangeRemoteDataSource {
        return StackExchangeRemoteDataSource(api)
    }

    @Provides
    fun provideUserRepository(dataSource: StackExchangeRemoteDataSource):UserRepository{
        return UserRepository(dataSource)
    }

}