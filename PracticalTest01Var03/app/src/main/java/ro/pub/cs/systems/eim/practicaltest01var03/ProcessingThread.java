package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private String text1;
    private String text2;

    private int i = 0;

    public ProcessingThread(Context context, String text1, String text2) {
        this.context = context;

        this.text1 = text1;
        this.text2 = text2;
    }

    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(String.valueOf(i));
        intent.putExtra("message", text1 + " - " + text2);
        Log.d("[ProcessingThread]",text1 + " - " + text2);
        context.sendBroadcast(intent);
        i++;
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}


