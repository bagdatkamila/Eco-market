package com.example.ecomarket.network

import com.airbnb.lottie.BuildConfig
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

// если не будет работать надо будет закомитить этот код и дргую часть provideRetrofitInstance
object Constants {
    const val BASE_URL = "https://neobook.online/ecobak/product-list/"
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Предоставляет HttpLoggingInterceptor для логирования HTTP запросов и ответов
    @Singleton
    @Provides
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return httpLoggingInterceptor
    }

    // Предоставляет OkHttpClient с настроенным HttpLoggingInterceptor
    @Singleton
    @Provides
    fun provideHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build()
    }

    // Предоставляет GsonConverterFactory для преобразования JSON данных
    @Singleton
    @Provides
    fun provideConvertFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        httpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).client(httpClient)
                .addConverterFactory(gsonConverterFactory).build()
    }

    // Предоставляет реализацию ApiFactory с использованием Retrofit
    @Singleton
    @Provides
    fun provideApiFactory(retrofit: Retrofit): ApiFactory {
        return retrofit.create(ApiFactory::class.java)

    }
}