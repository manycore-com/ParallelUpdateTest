package io.manycore.threadsecurity;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApplication extends Application {

    public static ExecutorService executorService =null;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("menglog", "rest of Application.onCreate");
        if (null == executorService) {
            executorService = Executors.newFixedThreadPool(8);
        }

    }

}
