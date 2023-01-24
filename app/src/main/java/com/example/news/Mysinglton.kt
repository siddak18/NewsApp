package com.example.news

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class Mysinglton constructor(context:Context){
    companion object{
        @Volatile
        private var INSTANCE:Mysinglton?=null
        fun getInstance(context: Context)=
            INSTANCE?: synchronized(this){
                INSTANCE?:Mysinglton(context).also {
                    INSTANCE=it
                }
            }
    }
    private val requestQueue:RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }
    fun<T>addrequesttoqueue(req:Request<T>){
        requestQueue.add(req)
    }
}