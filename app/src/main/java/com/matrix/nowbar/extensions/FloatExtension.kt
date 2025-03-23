package com.matrix.nowbar.extensions

fun Float.clamp(min: Float, max: Float) = if (this < min) min else if (this > max) max else this

fun Float.mapRange(
    fromMin: Float,
    fromMax: Float,
    toMin: Float,
    toMax: Float,
) = (((this - fromMin) / (fromMax - fromMin)) * (toMax - toMin) + toMin).clamp(toMin, toMax)
