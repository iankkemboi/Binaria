package com.ian.sendwave.binaria.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrencyAPIResponse {
    @SerializedName("disclaimer")
    @Expose
    var disclaimer: String? = null

    @SerializedName("license")
    @Expose
    var license: String? = null

    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null

    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("rates")
    @Expose
    var rates: Rates? = null
}