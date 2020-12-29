package com.khodealib.basalam01.feature.main

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.khodealib.basalam01.R
import com.khodealib.basalam01.common.BaseActivity
import com.khodealib.basalam01.ui.adapter.ProductListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {


    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var productRv:RecyclerView
    private lateinit var productAdapter:ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productRv = findViewById(R.id.rv_main_product)

        updateUi()



    }

    private fun updateUi() {

        mainViewModel.progressBarLiveData.observe(this) {
            setProgressIndicator(it)
        }

        mainViewModel.productLiveDate.observe(this) {

            productAdapter = ProductListAdapter(it)
            val gridLayoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
            productRv.apply {
                adapter = productAdapter
                layoutManager = gridLayoutManager
            }
        }
    }
}

