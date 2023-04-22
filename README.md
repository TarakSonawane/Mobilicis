# Mobilicis


About:
In this Application I have used Java to retrieve the following Device Information : 
● Manufacturer
● Model Name, Model Number 
● Ram 	
● Storage
● Battery current charging level 
● Android Version 
● Camera MegaPixel 
● Camera Aperture 
● Processor Information 
● GPU Information 
● Live Sensor reading ( Gyroscope, Barometer, Accelerometer, Rotation Vector, Proximity, Ambient light sensor) 

Classes/Methods Used:

1)SensorEventListener :
 SensorEventListener is an interface in the Android SDK that can be used to receive updates from the device's sensors. 
 It contains two methods that must be implemented:

   i) onSensorChanged: 
    This method is called when a sensor value has changed. 
    The method takes a SensorEvent object as a parameter, which contains information about the new  sensor value.   
    The SensorEvent object contains several fields, including the sensor type, timestamp, and the new sensor values.
  
   ii) onAccuracyChanged: 
    This method is called when the accuracy of a sensor has changed. 
    The method takes a Sensor object and an accuracy value as parameters. 
    The accuracy value is an integer that indicates the new accuracy of the sensor.

2)SensorManager:
 The SensorManager class in Android is used to access device sensors such as the accelerometer, gyroscope, and other sensors that are available on the device. 
 It provides a set of methods to access and use these sensors in an Android application.

