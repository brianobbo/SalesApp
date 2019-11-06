package com.technohem.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_signup.setOnClickListener {
            var i = Intent(this,RegisterActivity::class.java)
            startActivity(i)
        }

        login_btn.setOnClickListener {
            var url = "http://192.168.0.109/SalesWeb/login.php"

            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr= object : StringRequest(Request.Method.POST,url, Response.Listener { response ->
                if(response.equals("0"))
                    Toast.makeText(this,"Login Fail", Toast.LENGTH_LONG).show()
                else
                {
                    UserInfo.mobile=login_mobile.text.toString()
                    var i=Intent(this,HomeActivity::class.java)
                    startActivity(i)
                }

            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {

                    //Creating parameters
                    val params = Hashtable<String, String>()

                    //Adding parameters
                    params["mobile"] = login_mobile.text.toString()
                    params["password"] = login_password.text.toString()

                    //returning parameters
                    return params
                }
            }

            rq.add(sr)
        }
    }
}
