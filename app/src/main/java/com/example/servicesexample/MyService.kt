package com.example.servicesexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val TAG = "MyService"

    init{
        Log.d(TAG, "Service is running...")

    }

    //Needs when several clients needs to connect to your service at one time
    override fun onBind(intent: Intent?): IBinder? =   null

    //Very useful function allows to get data from intents which runs the service
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Log.d(TAG, dataString)
        }
        //Because of that fact that Service by default runs on main thread - you should avoid doing hard tasks OR run them in different thread like this:
        Thread{
            //for example while loop (there is no need to create while loop, just an example)
            while(true){
                //some important work here
            }
        }.start()


        // If Android system kills your service, so it won't start again when android system have resources again
        //return START_NOT_STICKY

        // If Android kills service because of lack memory, after there will be free memory - it will start again
        return START_STICKY

        // Same as START_STICKY but recreates service with last intent data (so intent won't be null)
        //return START_REDELIVER_INTENT
    }

    //called when service destroyed
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service is being killed...")
    }

}