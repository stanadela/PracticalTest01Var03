package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {

    EditText edit1;
    EditText edit2;
    CheckBox check1;
    CheckBox check2;
    TextView textView;
    Button btn;
    Button next;

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private IntentFilter intentFilter = new IntentFilter();

    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);

        edit1 = (EditText)findViewById(R.id.edit1);
        edit2 = (EditText)findViewById(R.id.edit2);
        check1 = (CheckBox) findViewById(R.id.check1);
        check2 = (CheckBox) findViewById(R.id.check2);
        textView = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.btn);
        next = (Button) findViewById(R.id.next);

        for (int i = 0; i < 1000; i++) {
            intentFilter.addAction(String.valueOf(i));
        }

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("edit1")) {
                edit1.setText(savedInstanceState.getString("edit1"));
            }
            if (savedInstanceState.containsKey("edit2")) {
                edit2.setText(savedInstanceState.getString("edit2"));
            }
            if (savedInstanceState.containsKey("text")) {
                textView.setText(savedInstanceState.getString("text"));
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder s = new StringBuilder(100);
                if (check1.isChecked()) {
                    String name = edit1.getText().toString();
                    if (name.equals("")) {
                        Toast.makeText(getApplicationContext(), "Text vid", Toast.LENGTH_SHORT).show();
                    } else {
                        s.append(name);
                    }
                }
                s.append(" ");
                if (check2.isChecked()) {
                    String group = edit2.getText().toString();
                    if (group.equals("")) {
                        Toast.makeText(getApplicationContext(), "Text vid", Toast.LENGTH_SHORT).show();
                    } else {
                        s.append(group);
                    }
                }
                textView.setText(s.toString());

                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intent.putExtra("edit1", edit1.getText().toString());
                intent.putExtra("edit2", edit2.getText().toString());
                getApplicationContext().startService(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03SecondaryActivity.class);
                String message1 = edit1.getText().toString();
                String message2 = edit2.getText().toString();
                intent.putExtra("edit1", message1);
                intent.putExtra("edit2", message2);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Result ok", Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "Result cancel", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var03Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("edit1", edit1.getText().toString());
        savedInstanceState.putString("edit2", edit2.getText().toString());
        savedInstanceState.putString("text", textView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("edit1")) {
            edit1.setText(savedInstanceState.getString("edit1"));
        }
        if (savedInstanceState.containsKey("edit2")) {
            edit2.setText(savedInstanceState.getString("edit2"));
        }
        if (savedInstanceState.containsKey("text")) {
            textView.setText(savedInstanceState.getString("text"));
        }
    }
}
