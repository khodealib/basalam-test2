package com.khodealib.basalam01.data.repo.product.source

import com.khodealib.basalam01.data.Product
import com.khodealib.basalam01.service.api.ApiService
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class ProductRemoteDataSource(private val apiService: ApiService) : ProductDataSource {

    override fun getProducts(): Single<List<Product>> {
        val queryParams = JSONObject().apply {
            put(
                "query",
                "{productSearch(size: 20) {products {id name photo(size: LARGE) { url } vendor { name } weight price rating { rating count: signals } } } }"
            )
        }

        val requestBody = RequestBody.create(
            MediaType.parse("application/json; charset=utf-8"),
            queryParams.toString()
        )
        return apiService.getProductsList(requestBody).map { productResponse ->
            productResponse.data.productSearch.products
        }
    }
}