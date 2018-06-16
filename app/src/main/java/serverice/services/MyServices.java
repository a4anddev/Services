package serverice.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

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

        int sleepTime = intent.getIntExtra("sleepTime", 1);
        // this code hang our app. because long background task, we should use long backgroudn service
        // start sevice after not select check box

        new MyAsyncTask().execute(sleepTime);

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



    class MyAsyncTask extends AsyncTask<Integer, String, Void>{

        private final String TAG = MyAsyncTask.class.getSimpleName();



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute, Thread Name " + Thread.currentThread().getName());
        }



        @Override
        protected Void doInBackground(Integer... voids) {
            // work in background work  long perform tasks running , playing music, downloading etc
            Log.i(TAG, "doInBackground, Thread Name " + Thread.currentThread().getName());
            int sleepTime = voids[0];

            int ctr = 1;
            // dummy long operation
            while (ctr <= sleepTime){
                publishProgress("Counter is now " + ctr);


            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ctr++;
            }




            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(MyServices.this, "Counter Value" + values[0], Toast.LENGTH_SHORT).show();
            Log.i(TAG, "counter value " + values[0] + " onProgressUpdate, Thread Name " + Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i(TAG, "onPostExecute, Thread Name " + Thread.currentThread().getName());
        }
    }
}
