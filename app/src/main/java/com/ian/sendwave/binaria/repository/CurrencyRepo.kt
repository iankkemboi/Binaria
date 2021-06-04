package com.ian.sendwave.binaria.repository


import com.ian.sendwave.binaria.network.CurrencyDataSource

import javax.inject.Inject

class CurrencyRepo @Inject constructor(
    private val remoteDataSource: CurrencyDataSource
) {

    suspend fun getCurrencyResponse(app_id: String) =
        remoteDataSource.getCurrencyAPIResponse(app_id)

}