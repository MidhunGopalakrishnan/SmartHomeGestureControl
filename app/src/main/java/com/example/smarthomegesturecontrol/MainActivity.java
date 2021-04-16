package com.example.smarthomegesturecontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "Main Activity";
    private String gestureFinal;
    public static final String DIR = "/Download/CapturedVideos/";
    private int totalVideoCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gesture_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        File dir = new File(Environment.getExternalStorageDirectory() + DIR);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        //find how many files starts with gesture name
        File[] fileList = dir.listFiles();

        for(File file: fileList) {
            totalVideoCount++;
        }
        //set the value for the text field
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setText("Total Recordings Available : "+totalVideoCount+"/51");

        Button proceedButton = (Button) findViewById(R.id.button);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GestureSampleActivity.class);
                intent.putExtra("Gesture",gestureFinal);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        gestureFinal = (String)parent.getItemAtPosition(position);
        Log.d(TAG, "onItemSelected: Selected Symptom = "+gestureFinal );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}