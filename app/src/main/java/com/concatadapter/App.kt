package com.concatadapter

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun launchActivity() {
       GlobalScope.launch(Dispatchers.Main) {
           val intent = Intent(this@App, MainActivity::class.java)
           intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
           startActivity(intent)
       }
    }
}
