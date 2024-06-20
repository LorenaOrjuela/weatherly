package com.globant.weatherly.extensions

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

fun List<Int>.getAverageAngle(): Double {
    var sumSin = 0.00
    var sumCos = 0.00

    forEach {
        val rad = Math.toRadians(it.toDouble())
        sumSin += sin(rad)
        sumCos += cos(rad)
    }
    val angle = atan2(sumSin, sumCos)
    var averageDegrees = Math.toDegrees(angle)
    if (averageDegrees < 0) averageDegrees += 360.0
    return averageDegrees
}