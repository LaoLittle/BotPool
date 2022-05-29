package org.laolittle

import kotlin.system.measureTimeMillis
import kotlin.test.Test

class Test {
    @Test fun set() {
        println(measureTimeMillis {
            val dd = intArrayOf(1,1,1)
            val bb = intArrayOf(1,1,1)
            val a = hashSetOf(dd, intArrayOf(1,1,1), intArrayOf(1,1,1), intArrayOf(1,1,1), intArrayOf(1,1,1), intArrayOf(1,1,1), intArrayOf(1,1,1))
            println(dd.hashCode())
            println(bb.hashCode())

            dd[2] = 213

            a.remove(bb)
            println(a.size)
        })
    }


}