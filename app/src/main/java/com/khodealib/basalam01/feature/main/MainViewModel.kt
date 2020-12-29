package com.khodealib.basalam01.feature.main

import androidx.lifecycle.MutableLiveData
import com.khodealib.basalam01.common.BaseObserver
import com.khodealib.basalam01.common.BaseViewModel
import com.khodealib.basalam01.data.Product
import com.khodealib.basalam01.data.repo.product.ProductRepository
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainViewModel(
    productRepository: ProductRepository,
):BaseViewModel() {

    val productLiveDate = MutableLiveData<List<Product>>()


    init {


        progressBarLiveData.value = true

        // refresh database
//        productRepository.refreshProduct()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object :CompletableObserver{
//                override fun onSubscribe(d: Disposable) {
//                    compositeDisposable.add(d)
//                }
//
//                override fun onComplete() {
//                    Timber.i("database refreshed")
//                }
//
//                override fun onError(e: Throwable) {
//                    Timber.i(e)
//                }
//
//            })

        productRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : BaseObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productLiveDate.value = t
                }
            })
    }
}