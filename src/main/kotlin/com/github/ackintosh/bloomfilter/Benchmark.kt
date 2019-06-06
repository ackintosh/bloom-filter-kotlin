package com.github.ackintosh.bloomfilter

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
open class Benchmark {
    private val bloomFilter = BloomFilter(100)
    private val testData = List(10000) { Random.nextBytes(10) }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    fun add() = testData.map { bloomFilter.add(it) }
}

fun main() {
    Runner(
            OptionsBuilder()
                    .include(com.github.ackintosh.bloomfilter.Benchmark::class.java.simpleName)
                    .build()
    ).run()
}