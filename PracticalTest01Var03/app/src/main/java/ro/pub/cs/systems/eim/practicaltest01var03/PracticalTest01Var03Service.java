package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var03Service extends Service {
    public PracticalTest01Var03Service() {
    }

    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text1 = intent.getStringExtra("edit1");
        String text2 = intent.getStringExtra("edit2");
        processingThread = new ProcessingThread(this, text1, text2);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
