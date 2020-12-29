package com.khodealib.basalam01.data.repo.product

import com.khodealib.basalam01.data.Product
import com.khodealib.basalam01.data.repo.product.source.ProductLocalDataSource
import com.khodealib.basalam01.data.repo.product.source.ProductRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class ProductRepositoryImpl(
    private val productRemoteDataSource: ProductRemoteDataSource,
    private val productLocalDataSource: ProductLocalDataSource
) : ProductRepository {


    override fun refreshProduct():Completable{

        return productRemoteDataSource.getProducts()
            .doOnSuccess {
                productLocalDataSource.addProducts(it)
            }.ignoreElement()

    }


    override fun getProducts(): Single<List<Product>> {

        return productRemoteDataSource.getProducts()
    }

}
