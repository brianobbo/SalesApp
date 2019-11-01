package com.technohem.salesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var url = "http://192.168.0.109/SalesWeb/get_cat.php"
        var list = ArrayList<String>()
        var rq:RequestQueue = Volley.newRequestQueue(this)
        var jar = JsonArrayRequest(Request.Method.GET,url,null,Response.Listener { response ->

            // get data in 'list' array
            for (x in 0..response.length()-1)
                list.add(response.getJSONObject(x).getString("category"))

            // arrayAdapter is use to fill list view
            // ArrayAdapter(1,2,3)
            var adp = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
            home_cat.adapter = adp

        },Response.ErrorListener { error ->
            Toast.makeText(this,error.message,Toast.LENGTH_LONG).show()
        })

        rq.add(jar)
    }
}
