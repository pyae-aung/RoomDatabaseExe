package com.example.roomdatabaseexe

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_product.*

class AddProductActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        btnSave.setOnClickListener {
            val word: String = editText.text.toString()
            val price = editTextprice.text.toString().toDouble()
            val replyIntent = Intent()

            replyIntent.putExtra(EXTRA_REPLY_NAME, word)
            replyIntent.putExtra(EXTRA_REPLY_PRICE, price)
            setResult(Activity.RESULT_OK, replyIntent)

        finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_NAME = "EXTRA REPLY_NAME"
        const val EXTRA_REPLY_PRICE = "EXTRA_REPLY_PRICE"
    }
}
