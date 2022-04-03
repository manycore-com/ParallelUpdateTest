package io.manycore.threadsecurity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class TimedWorkGuiThreadActivity extends AppCompatActivity {
    public static Semaphore sem = new Semaphore(0);
    public AtomicInteger nbrJobs = new AtomicInteger();
    public AtomicInteger nbrRun = new AtomicInteger();

    // Execution times emu rerun 8000 jobs, last semaphore on ExecutorService thread:
    // 448 334 402 336 307 344 387
    // avg: 365

    // Execution time emu rerun 8000 jobs, last semaphore on UI thread:
    // 421 439 398 287 402 397 332 450 335
    // avg: 385

    // Execution time emu rerun 8000 jobs, Semaphore on ExecutorService last semaphore on UI thread:
    // 509 534 484 495 480 482 465 682 514
    // avg: 516


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Manycore::log", "TimedWorkGuiThreadActivity ------------------------------------------");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {

            public void run() {
                Log.i("Manycore::log", "TimedWorkGuiThreadActivity started root run()");

                long msStart = System.currentTimeMillis();

                for (int i = 0; i < 8000; i++) {

                    MyApplication.executorService.submit(new Runnable() {
                        public int seq = nbrJobs.incrementAndGet();

                        @Override
                        public void run() {
                            int randomInt = ThreadLocalRandom.current().nextInt(0, 3 + 1);
                            try {
                                int t = nbrRun.incrementAndGet();

                                TimedWorkGuiThreadActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                    }
                                });

                                //Log.i("menglog", "act updated textview " + randomInt + " #submit:" + seq + " #run:" + t);
                            } catch (Exception ex) {
                                Log.e("menglog", ex.toString());
                            }

                        }
                    });
                }

                Future<?> fut = MyApplication.executorService.submit(new Runnable() {
                    public void run() {
                        Log.i("Manycore::log", "TimedWorkGuiThreadActivity release from ExecutorService");
                        sem.release();
                    }
                });

                try {
                    Log.i("Manycore::log", "TimedWorkGuiThreadActivity waitfor sem from ExecutorService");
                    sem.acquire();
                } catch (InterruptedException e) {
                    Log.w("Manycore::log", "Interrupted sem");
                }

                Log.i("Manycore::log", "TimedWorkGuiThreadActivity now we know all UI thread jobs have been created");

                Log.i("Manycore::log", "TimedWorkGuiThreadActivity out of the loop, now on to sem nbrJobs:" + nbrJobs.get() + " #run so far:" + nbrRun.get());
                TimedWorkGuiThreadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.i("Manycore::log", "TimedWorkGuiThreadActivity release from main thread");
                        sem.release();
                    }
                });

                try {
                    Log.i("Manycore::log", "TimedWorkGuiThreadActivity waitfor sem from UI thread");
                    sem.acquire();
                } catch (InterruptedException e) {
                    Log.w("Manycore::log", "Interrupted sem");
                }
                long totalTimeMs = System.currentTimeMillis() - msStart;

                Log.i("Manycore::log", "TimedWorkGuiThreadActivity time ms: " + totalTimeMs);
            }
        });
        thread.start(); // thread.run() blocks until done
        // ... and if I .run() main thread is looked up so no crash.

        Log.i("Manycore::log", "Thread started. isaAlive=" + thread.isAlive() + " nbrJobs:" + nbrJobs.get() + " nbrRun:" + nbrRun.get());


    }


}
