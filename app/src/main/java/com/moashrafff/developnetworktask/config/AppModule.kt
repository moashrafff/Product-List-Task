package com.moashrafff.developnetworktask.config

import android.app.Application
import androidx.room.Room
import com.moashrafff.developnetworktask.data.source.UserDataBaseClient
import com.moashrafff.developnetworktask.interfaces.EndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): EndPoints =
        retrofit.create(EndPoints::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : UserDataBaseClient =
        Room.databaseBuilder(app, UserDataBaseClient::class.java, "database")
            .build()

}