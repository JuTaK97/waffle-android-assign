package com.example.assignment4.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.assignment4.BuildConfig
import com.example.assignment4.login.LoginService
import com.example.assignment4.signup.SignUpService
import com.example.assignment4.ui.seminar.SeminarService
import com.example.assignment4.ui.user.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(sharedPreferences: SharedPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor{chain : Interceptor.Chain ->
                val newRequest = chain.request().newBuilder()
                val token = sharedPreferences.getString("token","nothing")
                if(token!="nothing") {
                    newRequest.addHeader("Authorization", "JWT $token")
                }
                chain.proceed(newRequest.build())}
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }


    @InstallIn(SingletonComponent::class)
    @Module
    object PreferenceModule {
        @Provides
        @Singleton
        fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
            return context.getSharedPreferences("assignment4.preference", Context.MODE_PRIVATE)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideSignupService(retrofit: Retrofit) : SignUpService {
        return retrofit.create(SignUpService::class.java)
    }

    @Provides
    @Singleton
    fun provideSeminarService(retrofit: Retrofit) : SeminarService {
        return retrofit.create(SeminarService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit) : UserService {
        return retrofit.create(UserService::class.java)
    }

}
