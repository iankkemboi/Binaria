package com.ian.sendwave.binaria.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ian.sendwave.binaria.network.CurrencyDataSource
import com.ian.sendwave.binaria.network.OpenExchangeService
import com.ian.sendwave.binaria.repository.CurrencyRepo
import com.ian.sendwave.binaria.utils.BASE_URL
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

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCurrencyService(retrofit: Retrofit): OpenExchangeService = retrofit.create(OpenExchangeService::class.java)

    @Singleton
    @Provides
    fun provideCurrencyRemoteDataSource(CurrencyService: OpenExchangeService) = CurrencyDataSource(CurrencyService)



    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CurrencyDataSource) =
        CurrencyRepo(remoteDataSource)
}