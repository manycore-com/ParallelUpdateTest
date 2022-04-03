package io.manycore.instrumentation;

import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;

// looked at this for reference:
// https://android.googlesource.com/platform/frameworks/testing/+/7a552ffc0bce492a7b87755490f3df7490dc357c/support/src/android/support/test/runner/AndroidJUnitRunner.java
// https://android.googlesource.com/platform/frameworks/testing/+/master/support/src/android/support/test/runner/MonitoringInstrumentation.java
public class Test extends Instrumentation {

    @Override
    public void onCreate(Bundle arguments) {
        Log.i("menglog", "instrumentation onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("menglog", "instrumentation onStart");
    }

}

