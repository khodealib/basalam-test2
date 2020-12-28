package com.khodealib.basalam01.data.repo.product

import com.khodealib.basalam01.data.Product
import io.reactivex.Completable
import io.reactivex.Single

interface ProductRepository {

    fun refreshProduct(): Completable

    fun getProducts() : Single<List<Product>>
}