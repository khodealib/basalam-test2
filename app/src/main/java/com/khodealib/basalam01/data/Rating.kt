package com.khodealib.basalam01.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rating(
    val count: Int,
    val rating: Double
):Parcelable