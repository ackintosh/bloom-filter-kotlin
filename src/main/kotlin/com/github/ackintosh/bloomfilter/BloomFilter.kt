package com.github.ackintosh.bloomfilter

import java.math.BigInteger
import java.security.MessageDigest
import java.util.BitSet

class BloomFilter(private val size: Int) {
    private val bits = BitSet(size)
    private val hash = Hash(size)

    fun add(value: ByteArray) {
        // TODO: race condition
        for (seed in 1..3) {
            bits.set(hash.hash(seed, value))
        }
    }

    fun mightContains(value: ByteArray): Boolean {
        for (seed in 1..3) {
            if (!bits.get(hash.hash(seed, value))) {
                return false
            }
        }
        return true
    }

    private class Hash(val filterSize: Int) {
        private val md5 = MessageDigest.getInstance("MD5")

        fun hash(seed: Int, value: ByteArray) =
            BigInteger(1, md5.digest(value + seed.toByte()))
                .remainder(BigInteger.valueOf(filterSize.toLong()))
                .toInt()
    }
}
