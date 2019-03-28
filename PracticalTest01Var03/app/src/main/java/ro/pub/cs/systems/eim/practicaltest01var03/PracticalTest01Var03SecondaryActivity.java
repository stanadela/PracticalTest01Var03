package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01Var03SecondaryActivity extends AppCompatActivity {

    TextView text1;
    TextView text2;
    Button ok;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_secondary);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        ok = (Button) findViewById(R.id.ok);
        cancel = (Button) findViewById(R.id.cancel);

        Intent intent = getIntent();
        String message1 = intent.getStringExtra("edit1");
        String message2 = intent.getStringExtra("edit2");

        text1.setText(message1);
        text2.setText(message2);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, resultIntent);
                finish();
            }
        });
    }
}
