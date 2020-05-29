package com.dima.ticketholder.utils

import android.content.Context
import android.util.TypedValue

fun getPixelsFromDp(context: Context, dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    context.resources.displayMetrics
).toInt()

