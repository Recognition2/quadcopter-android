package nl.kevinhill.gregory.quadrupelcontroller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int[] quadControls = new int[4];
    private int[] quadControlViews = new int[] {R.id.lift_view, R.id.pitch_view, R.id.roll_view, R.id.yaw_view};

    private int aardbei = 0;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TriggerEventListener mSensorListener;

    private void updateValues(boolean up, int idx) {
        quadControls[idx] += up ? 100 : -100;
        int c = quadControls[idx];
        if (c < 0)
            quadControls[idx] = 0;
        else if (c > 30000)
            quadControls[idx] = 30000;
        updateViews();
    }

    private void updateViews() {
        for (int i = 0; i < quadControls.length; i++) {
            ((TextView) findViewById(quadControlViews[i])).setText(String.format(Locale.ENGLISH,"%d", quadControls[i]));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent triggerEvent) {
                Log.d(TAG, "onTrigger is aangeroepen\n");
                Toast.makeText(MainActivity.this, "Aardappelen", Toast.LENGTH_SHORT).show();
                ((TextView) findViewById(R.id.helloworld)).setText(String.format(Locale.ENGLISH, "Getrekkerd! %d", aardbei++));
            }
        };
        mSensorManager.registerListener(mSensorListener, mSensor, 1000*1000);
        mSensorManager.requestTriggerSensor(mSensorListener, mSensor);

        // Set button handlers
        {
            Button b = findViewById(R.id.lift_dn);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(false, 0);
                }
            });
        }
        {
            Button b = findViewById(R.id.lift_up);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(true, 0);
                }
            });
        }
        {
            Button b = findViewById(R.id.pitch_dn);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(false, 1);
                }
            });
        }
        {
            Button b = findViewById(R.id.pitch_up);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(true, 1);
                }
            });
        }
        {
            Button b = findViewById(R.id.roll_dn);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(false, 2);
                }
            });
        }
        {
            Button b = findViewById(R.id.roll_up);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(true, 2);
                }
            });
        }
        {
            Button b = findViewById(R.id.yaw_dn);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(false, 3);
                }
            });
        }
        {
            Button b = findViewById(R.id.yaw_up);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateValues(true, 3);
                }
            });
        }
        final int banana = 0;

        // Start the message sender
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Get sensor data


//                sendMessageToDrone();
            }
        }, 100);

    }

}
