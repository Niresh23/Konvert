package com.niresh23.konvert.di

import com.niresh23.konvert.common.Constants
import com.niresh23.konvert.api.ExchangeRatesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
object HttpModule {

    @Provides
    fun createExchangeratesService(retrofit: Retrofit): ExchangeRatesService =
        retrofit.create(ExchangeRatesService::class.java)

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.URL)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request: Request = chain.request()

                val newRequest: Request = request.newBuilder()
                .addHeader("apikey", Constants.API_KEY)
                .build()

                chain.proceed(newRequest)
            }
            .addInterceptor(HttpLoggingInterceptor())
            .connectTimeout(2000L, TimeUnit.MILLISECONDS)
            .build()

        return client
    }

}