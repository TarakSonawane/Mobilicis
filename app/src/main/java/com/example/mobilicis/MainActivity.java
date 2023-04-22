package com.example.mobilicis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.opengl.GLES20;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Size;
import android.util.SizeF;
import android.view.View;

import com.example.mobilicis.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String manufacturer = Build.MANUFACTURER;
        String androidVersion = Build.VERSION.RELEASE;
        String cpuInfo = Build.HARDWARE;
        String gpu = Build.getRadioVersion();
        String ram = String.valueOf(getTotalRAM());
        String storage = String.valueOf(getTotalInternalMemorySize());
        String battery = String.valueOf(getBatteryLevel());
        String megapixel = String.valueOf(getCameraMegapixel());
        String aperture = String.valueOf(getCameraAperture());
        String processor = String.valueOf(getCPUInfo());

//● Live Sensor reading (GPS, Gyroscope, Barometer, Accelerometer, Rotation Vector, Proximity, Ambient light sensor)
//● IMEI

        binding.sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(myIntent);

            }
        });


        binding.manufactur.setText("Manufacturer: "+manufacturer+"\n");

        binding.ModelNumber.setText("Model Number: "+Build.MODEL+"\n");

        binding.ModelName.setText("Model Name: "+Build.DEVICE+"\n");

        binding.Ram.setText("Ram: "+ram+" MB\n");

        binding.Battery.setText("Battery: "+battery+"%\n");

        binding.storage.setText("Storage: "+storage+" GB\n");

        binding.AndroidVersion.setText("Android Version: "+androidVersion+"\n");

        binding.CameraMegaPixel.setText("Camera MegaPixel: "+megapixel+" MP\n");

        binding.CameraAperture.setText("Camera Aperture: "+aperture+"\n");

        binding.cpuData.setText(cpuInfo+"\n"+processor+"\n");

        binding.gpuData.setText(gpu+"\n");
    }

    public long getTotalRAM() {
        ActivityManager actManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        long totalMemory = memInfo.totalMem;
        return (totalMemory / (1024 * 1024));
    }

    public float getTotalInternalMemorySize() {
        StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
        long bytesAvailable = stat.getBlockSizeLong() * stat.getBlockCountLong();
        return (float) bytesAvailable / (1024 * 1024 * 1024);
    }

    public int getBatteryLevel() {
        Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return (int) ((float) level / (float) scale * 100.0f);
    }

    public float getCameraMegapixel() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraIds = manager.getCameraIdList();
            for (String cameraId : cameraIds) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                Size[] outputSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                        .getOutputSizes(ImageFormat.JPEG);
                float totalPixels = 0;
                for (Size size : outputSizes) {
                    float pixels = size.getWidth() * size.getHeight();
                    totalPixels += pixels;
                }
                float megapixels = totalPixels / (1024 * 1024);
                return megapixels;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int getCameraAperture() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String[] cameraIds = manager.getCameraIdList();
            for (String cameraId : cameraIds) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                float[] focalLengths = characteristics.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
                float focalLength = focalLengths[0];
                float sensorSize = characteristics.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE).getHeight();
                float aperture = focalLength / sensorSize;
                return (int) aperture;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public String getCPUInfo() {

        StringBuffer sb = new StringBuffer();
        try {
            Process process = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}