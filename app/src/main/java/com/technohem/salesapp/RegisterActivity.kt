package com.technohem.salesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        reg_signup.setOnClickListener {

            if (reg_password.text.toString().equals(reg_confirm.text.toString()))
            {
                var url = "http://192.168.0.107/SalesWeb/add_user.php?mobile="+reg_mobile.text.toString()+"&password="+
                        reg_password.text.toString()+"&name="+reg_name.text.toString()+"&address="+reg_address.text.toString()

                var rq:RequestQueue=Volley.newRequestQueue(this)
                var sr = StringRequest(Request.Method.GET,url,Response.Listener { response ->
                    if(response.equals("0"))
                    {
                        Toast.makeText(this,"Mobile Already Used", Toast.LENGTH_LONG).show()
                    }
                    else {
                        Toast.makeText(this,"User Created Successfully", Toast.LENGTH_LONG).show()
                        UserInfo.mobile=reg_mobile.text.toString()
                        var i = Intent(this,HomeActivity::class.java)
                        startActivity(i)
                    }

                },Response.ErrorListener { error ->
                    Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
                })
                //make connection
                rq.add(sr)
            }
            else
                Toast.makeText(this,"Password not match", Toast.LENGTH_LONG).show()

        }
    }
}
