package com.indooratlas.android.sdk.examples.foregroundservice;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.indooratlas.android.sdk.examples.R;
import com.indooratlas.android.sdk.examples.SdkExample;

@SdkExample(description = R.string.example_foregroundservice_description)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int CODE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        String[] neededPermissions = {
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN
        };
        ActivityCompat.requestPermissions( this, neededPermissions, CODE_PERMISSIONS );

        Button startButton = findViewById(R.id.button1);
        Button stopButton = findViewById(R.id.button2);

        // Start foreground service will create persistent notification with the "Start" and "Stop"
        // buttons for positioning
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                // After starting the foreground service, you can close the example app and continue
                // using the foreground service notification
                Intent startIntent = new Intent(MainActivity.this, ForegroundService.class);
                startIntent.setAction(ForegroundService.STARTFOREGROUND_ACTION);
                startService(startIntent);
                break;
            case R.id.button2:
                // To close the foreground service, "Stop foreground service" button must be pressed
                Intent stopIntent = new Intent(MainActivity.this, ForegroundService.class);
                stopIntent.setAction(ForegroundService.STOPFOREGROUND_ACTION);
                startService(stopIntent);
                break;

            default:
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
}
