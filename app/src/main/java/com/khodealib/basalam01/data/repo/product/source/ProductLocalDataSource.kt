package com.khodealib.basalam01.data.repo.product.source

import com.khodealib.basalam01.data.Product
import com.khodealib.basalam01.data.database.product.AppDao
import io.reactivex.Single

class ProductLocalDataSource(private val productDao: AppDao):ProductDataSource {

    override fun getProducts(): Single<List<Product>> {

        return productDao.getAll()

    }

    fun addProducts(products :List<Product>) {

        productDao.addAll(products)

    }
}