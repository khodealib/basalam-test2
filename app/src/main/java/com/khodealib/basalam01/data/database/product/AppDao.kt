package com.khodealib.basalam01.data.database.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khodealib.basalam01.data.Product
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(product: List<Product>)

    @Query("select * from tbl_product")
    fun getAll(): Single<List<Product>>
}