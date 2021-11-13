package com.concatadapter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SimStateReceiver: BroadcastReceiver() {

    private val TAG = "SimStateReceiver"

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d(TAG, "isAppLication Type: ${context?.applicationContext is App}")
        if (context?.applicationContext is App){
            (context.applicationContext as App).launchActivity()
        }
    }
}