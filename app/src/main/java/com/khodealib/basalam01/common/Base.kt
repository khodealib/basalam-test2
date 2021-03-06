package com.khodealib.basalam01.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.khodealib.basalam01.R
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseFragment : Fragment(), BaseView {
    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout
    override val viewContext: Context?
        get() = context
}

abstract class BaseActivity : AppCompatActivity(), BaseView {
    
    override val rootView: CoordinatorLayout?
        get() {
            val viewGroup = window.decorView.findViewById(android.R.id.content) as ViewGroup

            if (viewGroup !is CoordinatorLayout) {
                viewGroup.children.forEach {
                    if (it is CoordinatorLayout)
                        return it
                }

                throw IllegalStateException("RootView must be instance of CoordinatorLayout")
            } else {
                return viewGroup
            }
        }
    override val viewContext: Context?
        get() = this
}

interface BaseView {

    val rootView:CoordinatorLayout?
    val viewContext:Context?

    fun setProgressIndicator(mustShow: Boolean){
        rootView?.let {

            viewContext?.let {context ->
                var loadingView=it.findViewById<View>(R.id.loadingView)
                if (loadingView==null && mustShow){
                    loadingView=LayoutInflater.from(context).inflate(R.layout.view_loading,it,false)
                    it.addView(loadingView)
                }

                loadingView?.visibility=if (mustShow) View.VISIBLE else View.GONE

            }

        }
    }
}

abstract class BaseViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()
    val progressBarLiveData= MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}

abstract class BaseObserver<T>(private val compositeDisposable: CompositeDisposable):SingleObserver<T>{

    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        Timber.i(e)
    }
}
