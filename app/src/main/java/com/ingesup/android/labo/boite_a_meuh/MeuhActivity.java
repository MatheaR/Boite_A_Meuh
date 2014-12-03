package com.ingesup.android.labo.boite_a_meuh;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;



public class MeuhActivity extends Activity {
    public int recup;
    String txt;
    public TextView sensorg;

    private boolean mSoundCanLaunch;

    private MediaPlayer mPlayer = null; // Initialisation d'un media

    @Override  // Pause + Libération de mémoire pour le son
    public void onPause() {
        super.onPause();
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meuh);
        sensorg = (TextView) findViewById(R.id.sensorg);

        mSoundCanLaunch = true;

        SensorManager gestionnaireGyro = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        if (gestionnaireGyro.getDefaultSensor(Sensor.TYPE_GYROSCOPE)!=null) {
            gyroscope(gestionnaireGyro);
            sensorg.setText(txt);

        }



        final Button btn_playsound = (Button)findViewById(R.id.btn_play_sound);
        btn_playsound.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(R.raw.son);
            }
        });
    }


    private void playSound(int resId) {
        if(mPlayer != null) { // S'il y a déjà le son en route, on le coupe
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(this, resId); // On créé le son
        mPlayer.start(); // On joue le son
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.meuh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void gyroscope(SensorManager sensorManager) {

        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float roll = event.values[2];        // roll
                float pitch = event.values[1];     // pitch

                if (pitch < -45 && pitch > -135) {
                    mSoundCanLaunch = true;
                    // top side up
                } else if (isTilted(roll) && mSoundCanLaunch) {
                    // right side up
                    playSound(R.raw.son);
                    mSoundCanLaunch = false;
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sensorManager.registerListener(sel, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);


    }

    private boolean isTilted(float degree){
        return (degree > 45 || degree <-45);
    }
}
