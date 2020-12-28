package com.khodealib.basalam01.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tbl_product")
@Parcelize
data class Product(

    @PrimaryKey
    val id: String,
    val name: String,
    @Embedded
    val photo: Photo,
    val price: Int,
    @Embedded
    val rating: Rating,
    @Embedded
    val vendor: Vendor,
    val weight: Int

):Parcelable