package com.ian.sendwave.binaria.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ian.sendwave.binaria.model.CurrencyAPIResponse
import com.ian.sendwave.binaria.repository.CurrencyRepo
import com.ian.sendwave.binaria.utils.APP_ID
import com.ian.sendwave.binaria.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.net.ssl.SSLEngineResult


@HiltViewModel
class SendMoneyViewModel@Inject constructor(
    private val repository: CurrencyRepo
) : ViewModel() {
    val currencyapi = MutableLiveData<Resource<CurrencyAPIResponse>>()


    fun getLatestCurrency() {

        viewModelScope.launch {
          currencyapi.postValue(repository.getCurrencyResponse(APP_ID))
        }
    }

    fun getListResultState(): MutableLiveData<Resource<CurrencyAPIResponse>> {
        return currencyapi
    }
}
