package com.example.sensesensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager senseMan;
    private Sensor lightSensor;
    private TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Map textview to TextView resources
        textview = findViewById(R.id.textview);

        // Map sensor manager to system service
        senseMan = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Map sensor to light sensor
        lightSensor = senseMan.getDefaultSensor(Sensor.TYPE_LIGHT);

        // Register listener for lightSensor
        if (lightSensor != null) {
            senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            Toast.makeText(this, "Light sensor found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Light sensor not found", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Change the value in textview when light level changed
        textview.setText(Float.toString(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Handle accuracy changes if needed
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Register listener for lightSensor
        senseMan.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister listener for lightSensor
        senseMan.unregisterListener(this);
    }

}

