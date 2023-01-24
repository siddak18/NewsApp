package com.example.news

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), Help {
    private lateinit var adapter:NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview.layoutManager=LinearLayoutManager(this)

       val url= "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
      /*  val jsonObj=JSONObject().getJSONObject(url)
        val help=jsonObj.getJSONArray("articles")
        val help2=ArrayList<String>()
        for(i in 0 until help.length()){
            val help3=help.getJSONObject(i).getString("title")
            help2.add(help3)
        }*/
        Volley.newRequestQueue(this)
        val help=ArrayList<News>()
        adapter= NewsAdapter(help,this)



        val jsonObjectRequest=JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {

                  val jsonObject=it.getJSONArray("articles")
                  for(i in 0 until jsonObject.length()){
                      val h=jsonObject.getJSONObject(i)
                      val las= News(h.getString("title"),
                      h.getString("urlToImage"),
                      h.getString("url"))
                      help.add(las)

                  }

              },
            {
                Toast.makeText(this,"could not fetched data ",Toast.LENGTH_LONG).show()
            }

        )



       adapter.update(help)
        Mysinglton.getInstance(this).addrequesttoqueue(jsonObjectRequest)
        recyclerview.adapter=adapter
    }

    override fun onclick(item:News) {


        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

}