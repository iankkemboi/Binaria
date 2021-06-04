package com.ian.sendwave.binaria

import com.ian.sendwave.binaria.utils.Utility
import junit.framework.Assert.assertEquals
import org.junit.Test


class SendMoneyUnitTest {

    private var utility: Utility = Utility()


    @Test
    fun `convert Binary To Decimal is Correct`() {
        //1100 to decimal is 12
        assertEquals(12.0, utility.convertBinaryToDecimal("1100".toLong()))
    }

    @Test
    fun `convert Binary To Decimal is Incorrect`() {
        //1100 to decimal is 12
        assertEquals(200.0, utility.convertBinaryToDecimal("1100".toLong()))
    }

    @Test
    fun `convert Decimal to Binary is Correct`() {
        //12 to decimal is 1100
        assertEquals(utility.convertDecimalToBinary(12), "1100")
    }

    @Test
    fun `check country code to currency code is correct`() {
        //01100 to decimal is 12
        assertEquals("NGN", utility.getCurrencyCode("NG"))
    }


}
