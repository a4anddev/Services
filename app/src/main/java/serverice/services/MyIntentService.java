package serverice.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {

    private final String TAG =  MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("myWorkedThread");  // give the name to the worker thread

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "On Create, Thread name : " + Thread.currentThread().getName());


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

       int sleepTime = intent.getIntExtra("sleepTime",1 );
        int ctr = 1;
        // dummy long operation
        while (ctr <= sleepTime){
            Log.i(TAG,"Counter is now " + ctr);


            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctr++;
        }

        Log.i(TAG, "on HandleIntent, Thread name : " + Thread.currentThread().getName());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "On Destroy, Thread name : " + Thread.currentThread().getName());
    }
}
