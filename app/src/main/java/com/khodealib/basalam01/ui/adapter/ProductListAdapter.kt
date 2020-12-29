package com.khodealib.basalam01.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.khodealib.basalam01.R
import com.khodealib.basalam01.data.Product
import com.squareup.picasso.Picasso

class ProductListAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    class ProductItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val productImg: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val productCountTv: TextView = itemView.findViewById(R.id.tv_product_count)
        private val productRateTv: TextView = itemView.findViewById(R.id.tv_product_rate)
        private val productNameTv: TextView = itemView.findViewById(R.id.tv_product_name)
        private val productVendorTv: TextView = itemView.findViewById(R.id.tv_product_vendor)
        private val productPriceTv: TextView = itemView.findViewById(R.id.tv_product_price)
        private val productWeightTv: TextView = itemView.findViewById(R.id.tv_product_weight)

        fun bind(product: Product) {
            Picasso.get()
                .load(product.photo.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(productImg)

            productCountTv.apply {
                " (${product.rating.count}) ".also { text = it }
            }

            productRateTv.apply {
                text = "${product.rating.rating}"
            }

            productNameTv.apply {
                text = product.name
            }

            productVendorTv.apply {
                text = product.vendor.name
            }

            productPriceTv.apply {
                "${product.price} تومان ".also { text = it }
            }

            productWeightTv.apply {
                "${product.weight}کیلوگرم ".also { text = it }
            }
        }
    }
}