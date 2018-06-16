package serverice.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyServices extends Service {

    private static final String TAG = MyServices.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "On Create, Thread Name " + Thread.currentThread().getName());


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "On Start Command, Thread name " + Thread.currentThread().getName() );
        // perform tasks [Short duration Task : don't Block the ui]
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "OnBind, Thread Name " + Thread.currentThread().getName());

        /*Sometimes the android system may terminate the service. may be due to lack of memory
        *   START_STICKY
        *       service restarts automatically
        *       intent lost (becomes null)
        *   START_NOT_STICKY
        *       service not restated
        *       intent lost (becomes null)
        *  START_REDELIVER_INTENT
        *       service restarts automatically
        *       intent redelivered
        * */


//        return START_STICKY;
//        return START_NOT_STICKY;
//        return START_REDELIVER_INTENT;
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Stop Service , Thread Name " + Thread.currentThread().getName());

    }
}
