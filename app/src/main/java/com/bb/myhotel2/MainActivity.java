package com.bb.myhotel2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView Iv;
    int[] icon = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
    String[] guestFName = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    String[] guestLName = {"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"};

    ArrayList<String> al = new ArrayList<String>();
    private EditText guestFNameEditText = findViewById(R.id.fname2);
    private EditText guestLNameEditText = findViewById(R.id.lname2);
    Button addGuestButton = findViewById(R.id.button2);
    int guestCount = 0;

    private int count = 0;
    private SharedPreferences sharedPreferences;
    private String mainKey = "my.count.key";

    private EditText guestNameEditText;
    private ListView guestListView;
    private Button addGuestButton;
    private int guestCount = 0;
    private String guestKeyPrefix = "GUEST_";
    private List guestList = new ArrayList<String>();

    private final String GUEST_COUNT_KEY = "guest.count.key";
    private Object ArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Iv = (ListView) findViewById(R.id.guest_listview);
        Iv.setAdapter(new CustomAdapter(MainActivity.this, icon, guestFName, guestLName));

        sharedPreferences = getSharedPreferences("com.bb.datapersistencefigchanges", Context.MODE_PRIVATE);
        guestNameEditText = findViewById(R.id.fname2);
        addGuestButton = findViewById(R.id.button2);
        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);

        addGuestButton.setOnClickListener((new View.OnClickListener() {

            public onClick(View v) {
                String guestName = guestNameEditText.getText().toString().trim();
                guestCount++;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(guestKeyPrefix + guestCount, guestName);

                editor.putInt(GUEST_COUNT_KEY, guestCount);
                editor.apply();
                readGuests();
                guestNameEditText.setText("");
            }
        }));

        guestFNameEditText.setText("Count: " + count);

        guestFNameEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCount();
            }
        });
    }

    private void addCount() {
        count++;
        guestFNameEditText.setText("Count: " + count);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(mainKey, count);
        editor.apply();
    }

    private void readGuests() {
        guestCount = sharedPreferences.getInt(GUEST_COUNT_KEY, 0);
        guestList.clear();

        for (int i = 0; i < guestCount; i++) {
            String name = sharedPreferences.getString(guestKeyPrefix + (i + 1), "unknown");
            guestList.add(name);
        }
        updateGuestList(parent);
    }

    private void updateGuestList(Object parent) {
        android.widget.ArrayAdapter arrayAdapter = new ArrayAdapter<List<String>>(
                this,
                R.layout.list_of_rows,
                R.id.fname2,
                guestList

        );
        guestListView.setAdapter(android.widget.ListAdapter.ArrayAdapter);
        guestListView.setOnItemClickListener(new AdapterView.OnItemClickListener);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(MainActivity.this, guestList.get(position).toString(), Toast.LENGTH_LONG).show();

    }
}


