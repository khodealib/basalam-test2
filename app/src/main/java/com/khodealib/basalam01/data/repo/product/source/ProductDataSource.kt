package com.khodealib.basalam01.data.repo.product.source

import com.khodealib.basalam01.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductDataSource {

    fun getProducts():Single<List<Product>>


}