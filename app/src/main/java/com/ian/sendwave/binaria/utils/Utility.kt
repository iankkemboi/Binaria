package com.ian.sendwave.binaria.utils

import kotlin.math.pow

class Utility {


    fun convertBinaryToDecimal(num: Long): Double {
        var num = num
        var decimalNumber = 0.0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            decimalNumber += (remainder * 2.0.pow(i.toDouble())).toInt()
            ++i
        }
        return decimalNumber
    }

    fun convertDecimalToBinary(num: Int): String {
        return Integer.toBinaryString(num)

    }

    fun getCurrencyCode(countryCode: String): String {
        var currcode = "KSH"
        when (countryCode) {
            "KE" -> currcode = "KSH"
            "NG" -> currcode = "NGN"
            "TZ" -> currcode = "TZS"
            "UG" -> currcode = "UGX"
        }

        return currcode

    }
}