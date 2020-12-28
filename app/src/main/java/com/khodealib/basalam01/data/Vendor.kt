package com.khodealib.basalam01.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class Vendor(
    @ColumnInfo(name="vendor_name")
    val name: String
):Parcelable