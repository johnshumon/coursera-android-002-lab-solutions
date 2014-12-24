package me.shumon.selfiemania;

/**
 * Created by kutimuti on 11/29/14.
 */

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.widget.Toast;

import java.util.Calendar;


public class SetAlarm {

    // local variables
    private Context mContext;
    private static Calendar cal;
    private static AlarmManager alarmManager = null;
    private static Intent intent = null;;
    private static PendingIntent pendingIntent = null;

    final static int RQS_1 = 1;

    /**
     * constructor.
     *
     * @param context
     */
    public SetAlarm(Context context) {
        mContext = context;
    }

    /**
     * calculate the alarm time based on user time.
     *
     * @param alarmOffset
     * @param alarmText
     */
    public void calculateAlarmTime() {
        try {

            // get current time.
            Long currTime = System.currentTimeMillis();

            // add two mins with it
            Long twoMin = (long) (2 * 60 * 1000); // alarm offset

            // set alarm for this new time.
            Long alarmTime = currTime + twoMin;

            // get the current time.
            cal = Calendar.getInstance();

            // set alarm time to the calender variable
            cal.setTimeInMillis(alarmTime);

            // set alarm and notification
            setAlarmAndNotify(cal);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    } // end method..

    /**
     * Call the alarm broad cast receiver and pass the alarm manager.
     *
     * @param targetCal
     */
    private void setAlarmAndNotify(Calendar targetCal) {

        Toast.makeText(mContext, "SetAlarm::: Setting alarm!",
                Toast.LENGTH_SHORT).show();

        intent = new Intent(mContext, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(mContext, RQS_1, intent, 0);
        alarmManager = (AlarmManager) mContext
                .getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);

    }

    /**
     * Deactivate any set alarm.
     */
    public void cancelAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(mContext, "Alarm Deactivated.",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(mContext, "No alarm to deactivate",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
