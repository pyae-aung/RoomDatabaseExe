package com.example.roomdatabaseexe

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexe.adapter.ProductAdapter
import com.example.roomdatabaseexe.room.roomProduct.Product
import com.example.roomdatabaseexe.room.roomProduct.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val addProductRequestCode = 1
    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productAdapter = ProductAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProducts.observe(this, Observer { products ->
            products?.let {
                productAdapter.setListProducts(it)
            }
        })

//        var word = intent.getStringExtra("product_name")

        btnAdd.setOnClickListener {
            var intent = Intent(this@MainActivity, AddProductActivity::class.java)
            startActivityForResult(intent, addProductRequestCode)
        }

        btnDelete.setOnClickListener {
            productViewModel.clear()
        }

        btnGetProductByName.setOnClickListener {
            val word = editTextProductName.text.toString()
            productViewModel.getProduct(word)

            productViewModel.product.observe(this, Observer { product ->
                product?.let {
                    txtProductId.text = it.id.toString()
                }
            })
        }

        btnDeleteById.setOnClickListener {
            val id = editTextDeleteById.text.toString()
            productViewModel.deleteById(id.toInt())
        }

        btnShowProductByRange.setOnClickListener {
            val rangeStart = editTextRangeStart.text.toString().toDouble()
            val rangeEnd = editTextRangeEnd.text.toString().toDouble()
            val productAdapter = ProductAdapter()

            recyclerProductByRange.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = productAdapter
            }

            productViewModel.getProductByPriceRange(rangeStart, rangeEnd)
            productViewModel.productsByPriceRange.observe(this, Observer {
                it.let {
                    productAdapter.setListProducts(it)
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == addProductRequestCode && resultCode == Activity.RESULT_OK) {
            var productName: String = ""
            var productPrice: Double = 0.0

            data?.getStringExtra(AddProductActivity.EXTRA_REPLY_NAME)?.let {
                productName = it
            }

            data?.getDoubleExtra(AddProductActivity.EXTRA_REPLY_PRICE, 0.0)?.let {
                productPrice = it
            }

            productViewModel.insert(Product(name = productName, price = productPrice))
//            var w = data?.getStringExtra(AddProductActivity.EXTRA_REPLY)
//            Log.d("ProductName: ", "$w")
        }


    }
}
//    private class AddProduct(var itemViewModel: ProductViewModel, var word: String?) :
//        AsyncTask<Void, Void, Boolean>() {
//
//        override fun doInBackground(vararg params: Void?): Boolean {
//            itemViewModel.insert(Product(name = word))
//            return true
//        }
//
//        override fun onPostExecute(bool: Boolean?) {
//            if (bool!!) {
//                Toast.makeText(MainActivity(), "Added to Database", Toast.LENGTH_LONG).show()
//            }
//        }
//    }


