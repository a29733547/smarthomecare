package com.czerny.smarthomecare

import androidx.core.math.MathUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class ExampleMultiplicationTest {
    @Test
    fun check() {
        var result = Math.multiplyExact(2,3)
        assertEquals(result, 6)
    }
}