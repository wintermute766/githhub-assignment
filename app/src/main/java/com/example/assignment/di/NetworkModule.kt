package com.example.assignment.di

import com.example.assignment.data.api.GithubEndpoint
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
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideHttpClient(): OkHttpClient {
        try {
            val builder = OkHttpClient.Builder()
            builder.readTimeout(RETROFIT_TIMEOUT, TimeUnit.MILLISECONDS)
            builder.connectTimeout(RETROFIT_TIMEOUT, TimeUnit.MILLISECONDS)
            return builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    @Singleton
    @Provides
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideGithubApiService(
        client: OkHttpClient,
        loggingInterceptor: HttpLoggingInterceptor
    ): GithubEndpoint {
        val restClient = restClientBuilder(
            client,
            loggingInterceptor
        ).build()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(restClient)
            .build()
            .create(GithubEndpoint::class.java)
    }

    companion object {
        const val RETROFIT_TIMEOUT = 30000L
        fun restClientBuilder(
            okHttpClient: OkHttpClient,
            loggingInterceptor: HttpLoggingInterceptor,
        ) = okHttpClient.newBuilder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(RETROFIT_TIMEOUT, TimeUnit.MILLISECONDS)
    }
}
