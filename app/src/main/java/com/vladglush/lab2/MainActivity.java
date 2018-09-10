package com.vladglush.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSayHelloButtonClick(View v) {
        TextView tv = findViewById(R.id.textViewHello);
        String name = ((EditText)findViewById(R.id.editTextName)).getText().toString().trim();

        if (!name.isEmpty())
            tv.setText("Hello, " + name);
    }
}
