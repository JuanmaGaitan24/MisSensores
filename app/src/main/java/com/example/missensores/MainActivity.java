package com.example.missensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView txtView, txtProximidad, txtSensor, txtSensor2;
    SensorManager sensorManager;
    Vibrator vibrator;
    ConstraintLayout fondoaplicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.textView);
        txtProximidad = findViewById(R.id.textViewSensor);
        fondoaplicacion = findViewById(R.id.fondo);
        txtSensor = findViewById(R.id.textViewSensor2);
        txtSensor2 = findViewById(R.id.textViewSensor3);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        String cadena = "";
        int contador = 1;
        for (Sensor s: deviceSensors){

            cadena += contador + " Sensor: " + s.getName() + " Tipo: " + s.getStringType() + " Fabricante: " + s.getVendor() + "\n";
            contador++;

        }

        txtView.setText(cadena);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED), SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        /*if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY){

            txtProximidad.setText(""+sensorEvent.values[0]);
            if (sensorEvent.values[0] == 0){
                vibrator.vibrate(500);
            }

        }

        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){

            txtProximidad.setText("Lumninosida: " + sensorEvent.values[0]);

        }*/

        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE_UNCALIBRATED){

            txtProximidad.setText("Rotacion x: " + sensorEvent.values[0]);
            txtSensor.setText("Rotacion y: " + sensorEvent.values[1]);
            txtSensor2.setText("Rotacion z: " + sensorEvent.values[2]);

            if (sensorEvent.values[0] < 0 && sensorEvent.values[1] < 0 && sensorEvent.values[3] > 0){

                fondoaplicacion.setBackgroundColor(getResources().getColor(R.color.purple_200));

            }
            else {
                fondoaplicacion.setBackgroundColor(getResources().getColor(R.color.black));
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}