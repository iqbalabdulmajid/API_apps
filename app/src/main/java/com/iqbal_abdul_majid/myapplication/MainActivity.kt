package com.iqbal_abdul_majid.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import org.chromium.base.Callback
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import okhttp3.*

class MainActivity : AppCompatActivity() {
    private lateinit var listViewDetail: ListView
    var arrayListDetail: ArrayList<Model> = ArrayList()
    private val client = OkHttpClient()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listViewDetail = findViewById<ListView>(R.id.listView)
        run("https://ermaweb.com/praktekmobile/index.html")
    }

    private fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("NOT YET IMPLEMENTED")
            }

            override fun onResponse(call: Call, response: Response) {
                val strResponse = response.body()!!.string()
                val jsonContact = JSONObject(strResponse)
                val jsonarrayInfo: JSONArray = jsonContact.getJSONArray("info")
                var i = 0
                val size: Int = jsonarrayInfo.length()
                arrayListDetail = ArrayList()
                for (i in 0 until size) {
                    val jsonObjectDetail: JSONObject = jsonarrayInfo.getJSONObject(i)
                    val model = Model()
                    model.id = jsonObjectDetail.getString("id")
                    model.name = jsonObjectDetail.getString("name")
                    model.email = jsonObjectDetail.getString("email")
                    arrayListDetail.add(model)
                }
                runOnUiThread {
                    val objectAdapter: CustomAdapter
                    objectAdapter = CustomAdapter(applicationContext, arrayListDetail)
                    listViewDetail.adapter = objectAdapter
                }
            }
        })
    }
}