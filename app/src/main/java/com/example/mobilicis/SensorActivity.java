package com.example.mobilicis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mobilicis.databinding.ActivityMainBinding;
import com.example.mobilicis.databinding.ActivitySensorBinding;

import java.util.Vector;

public class SensorActivity extends AppCompatActivity {

    private SensorEventListener SensorReaderr;
    ActivitySensorBinding binding;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private Sensor accelerometer;
    private Sensor barometer;
    private Sensor rotationVectorSensor;
    private Sensor proximitySensor;
    private Sensor ambientLightSensor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySensorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        barometer = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        ambientLightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);



        SensorReaderr = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor == gyroscopeSensor) {
                    binding.Gyroscope.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]+" ");



                }

                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                    binding.Accelerometer.setText(+event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]+" ");

                }
                if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {

                    binding.Barometer.setText(event.values[0]+" ");



                }

                if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

                    binding.rotationVectorSensor.setText(event.values[0]+"\n"+event.values[1]+"\n"+event.values[2]+" ");

                }

                if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

                    binding.proximitySensor.setText(event.values[0]+" ");



                }

                if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {

                    binding.AmbientLightSensor.setText(event.values[0]+" ");



                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (gyroscopeSensor != null) {
            sensorManager.registerListener(SensorReaderr,gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("SensorReader", "Gyroscope not supported on this device");
        }

        if (accelerometer != null) {
            sensorManager.registerListener( SensorReaderr, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("SensorReader", "accelerometer not supported on this device");
        }

        if (barometer != null) {
            sensorManager.registerListener(SensorReaderr, barometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("SensorReader", "barometer not supported on this device");
        }

        if (rotationVectorSensor != null) {
            sensorManager.registerListener(SensorReaderr, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("SensorReader", "rotationVectorSensor not supported on this device");
        }


        if (proximitySensor != null) {
            sensorManager.registerListener(SensorReaderr, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("SensorReader", "proximitySensor not supported on this device");
        }


        if (ambientLightSensor != null) {
            sensorManager.registerListener(SensorReaderr, ambientLightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Log.d("SensorReader", "ambientLightSensor not supported on this device");
        }




    }



}