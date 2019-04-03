package com.mrt.mravi.mrt_blog1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.mrt.mravi.mrt_blog1.Activities.MainActivityMD7;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent=new Intent(this,MainActivityMD7.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBulder=new NotificationCompat.Builder(this);

notificationBulder.setContentTitle("Fcn Notificatoin");
        //this will print the notification
        notificationBulder.setContentText(remoteMessage.getNotification().getBody());
        notificationBulder.setAutoCancel(true);
        notificationBulder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBulder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //this will display the notification
        notificationManager.notify(0,notificationBulder.build());

    }
}
