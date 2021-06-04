package com.ian.sendwave.binaria.network

import javax.inject.Inject

class CurrencyDataSource @Inject constructor(
    private val openExchangeService: OpenExchangeService
) : BaseDataSource() {

    suspend fun getCurrencyAPIResponse(app_id: String) =
        getResult { openExchangeService.getCurrencyAPIResponse(app_id) }
}