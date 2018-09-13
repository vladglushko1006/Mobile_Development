package com.vladglush.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    EditText nameEdit;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEdit = findViewById(R.id.editTextName);
        tv = findViewById(R.id.textViewHello);
    }

    public void onClickClear(View view) {
        nameEdit.setText("");
    }

    public void onSayHelloButtonClick(View v) {
        String name = nameEdit.getText().toString().trim();

        if (!name.isEmpty())
            tv.setText("Hello, " + name);
    }

}
