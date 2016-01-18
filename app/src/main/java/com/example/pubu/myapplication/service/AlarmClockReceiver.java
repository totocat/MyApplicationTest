package com.example.pubu.myapplication.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.pubu.myapplication.AlarmClockActivity;
import com.example.pubu.myapplication.receiver.AlarmClockIntentService;

public class AlarmClockReceiver extends WakefulBroadcastReceiver {
    public AlarmClockReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //  Update UI
        AlarmClockActivity inst = AlarmClockActivity.instance();
        inst.setAlarmText("Alarm! Wake up!!!");

        // sound the alram tone
        // sound the alarm oce, if you wish
        // raise  alarm in loop continuesly then use MediaPlayer and setLooping(true)
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            Log.d("Edward", "RingtoneManager.TYPE_NOTIFICATION");
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        } else {
            Log.d("Edward", "RingtoneManager.TYPE_ALARM");
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        // this will send anotification message
        ComponentName comp = new ComponentName(context.getPackageName(), AlarmClockIntentService.class.getName());
        startWakefulService(context, intent.setComponent(comp));
        setResultCode(Activity.RESULT_OK);
    }
}
