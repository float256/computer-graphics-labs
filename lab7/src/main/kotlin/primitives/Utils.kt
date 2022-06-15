package primitives

import java.lang.Double.longBitsToDouble

/*
fun fastInvSqrt(x: Double): Double {
    val xHalf = 0.5 * x
    var i = java.lang.Double.doubleToLongBits(x)
    i = 0x5fe6ec85e7de30daL - (i shr 1)
    var result = longBitsToDouble(i)
    result *= 1.5 - xHalf * x * x
    return result
}

fun fastSqrt(x: Double): Double = 1 / fastInvSqrt(x)
 */