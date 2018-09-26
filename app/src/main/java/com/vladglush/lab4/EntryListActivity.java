package com.vladglush.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EntryListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);

        SharedPreferences sp  = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        String entryListString = sp.getString("entry_list", "");

        if (entryListString.isEmpty())
            return;

        String[] entryList = entryListString.split("&");
        for (int i = 0; i < entryList.length; i++)
        {
            String[] data = entryList[i].split("\\|");
            entryList[i] = data[0] + " " + data[1] + ", " + data[2];
        }

        ListView lv = findViewById(R.id.entryListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, entryList);
        lv.setAdapter(adapter);
    }
}
