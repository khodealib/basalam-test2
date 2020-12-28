package com.khodealib.basalam01.data.database.product

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.khodealib.basalam01.data.Photo
import com.khodealib.basalam01.data.Product
import com.khodealib.basalam01.data.Rating
import com.khodealib.basalam01.data.Vendor

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val appDao: AppDao
}