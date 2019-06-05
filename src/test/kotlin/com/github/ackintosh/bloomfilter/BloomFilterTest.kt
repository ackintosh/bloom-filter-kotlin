package com.github.ackintosh.bloomfilter

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BloomFilterTest {
    @Test
    fun test() {
        val bloomFilter = BloomFilter(100)
        bloomFilter.add("hello".toByteArray())
        bloomFilter.add("bloom filter".toByteArray())

        Assertions.assertTrue(bloomFilter.mightContains("hello".toByteArray()))
        Assertions.assertTrue(bloomFilter.mightContains("bloom filter".toByteArray()))
        Assertions.assertFalse(bloomFilter.mightContains("xxx".toByteArray()))
    }
}