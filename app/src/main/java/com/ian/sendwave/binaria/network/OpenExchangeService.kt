package com.ian.sendwave.binaria.network


import com.ian.sendwave.binaria.model.CurrencyAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenExchangeService {


    @GET("latest.json")
    suspend fun getCurrencyAPIResponse(@Query("app_id") app_id: String): Response<CurrencyAPIResponse>
}