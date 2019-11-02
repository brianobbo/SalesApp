package com.technohem.salesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        var cat:String = intent.getStringExtra("cat")
        var url = "http://localhost/SalesWeb/get_items.php?category=$cat"

    }
}
