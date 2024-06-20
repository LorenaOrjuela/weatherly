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

fun Double.degreesToCardinal(): String {
    val directions = arrayOf("N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSO", "SO", "OSO", "O", "ONO", "NO", "NNO")
    val index = ((this / 22.5) + 0.5).toInt()
    return directions[index % 16]
}

fun Int.degreesToCardinal(): String {
    val directions = arrayOf("N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSO", "SO", "OSO", "O", "ONO", "NO", "NNO")
    val index = ((this / 22.5) + 0.5).toInt()
    return directions[index % 16]
}
