package com.example.pubu.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import com.example.pubu.myapplication.service.AlarmClockReceiver;

import java.util.Calendar;

public class AlarmClockActivity extends AppCompatActivity {

    public static final String TAG = "AlarmClockActivity";

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static AlarmClockActivity insta;
    private TextView alarmTextView;
    private ToggleButton alarmToggleBtn;

    public static AlarmClockActivity instance() {
        return insta;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        alarmToggleBtn = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    /**
     * allow other components such as a service to invoke this activity's method vis AlarmClockActivity.instance().xxxxx()
     */
    @Override
    protected void onStart() {
        super.onStart();
        insta = this;
    }

    public void onToggleClicked(View view) {
        if (alarmToggleBtn.isChecked()) {
            Log.d(TAG, "Alarm On : " + alarmTimePicker.getCurrentHour() + ":" + alarmTimePicker.getCurrentMinute());
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

            Intent myIntent = new Intent(AlarmClockActivity.this, AlarmClockReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmClockActivity.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        } else {
            Log.d(TAG, "Alarm Off");
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
        }

    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }


}
