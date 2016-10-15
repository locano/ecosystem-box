package com.ingsoft.url.gudbox;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ludwi on 10/14/2016.
 */

public class AndroidNotification extends Notification {

    /***
     * Local Variables
     */
    String SeedName,InformationText;
    Integer Seed_icon;
    Context context;
    /***
     *
     * @param SeedName
     * @param InformationText
     * @param Seed_icon
     */
    AndroidNotification(String SeedName, String InformationText,Integer Seed_icon,Context context){

        this.SeedName=SeedName;
        this.InformationText=InformationText;
        this.Seed_icon=Seed_icon;
        this.context=context;
    }

    public Notification CreateNotification() {
        Notification notification;

        PendingIntent pendingIntentIntent =
                PendingIntent.getActivity(context,0,
                        new Intent(context, MainActivity.class),0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context);
        builder
                .setSmallIcon(Seed_icon)
                .setContentTitle(SeedName)
                .setContentText(InformationText)
                .setTicker("Gudbox notification")
                .setContentIntent(pendingIntentIntent)
                .setDefaults(NotificationCompat.DEFAULT_SOUND);

        builder.setAutoCancel(true);

        notification =  builder.build();
        return  notification;
    }

}
