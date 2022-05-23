package com.example.afinal.model;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import javax.inject.Inject;

import dagger.hilt.android.scopes.ServiceScoped;

@ServiceScoped
public class NotificationMangerService extends NotificationListenerService {
    private final DataModel dataModel;

    @Inject
    NotificationMangerService(DataModel dataModel){
        this.dataModel = dataModel;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){
        Bundle extras = sbn.getNotification().extras;
        String  packageName= sbn.getPackageName();
        String title = extras.getString(Notification.EXTRA_TITLE);
        String text = extras.getString(Notification.EXTRA_TEXT);
        long timestamp = sbn.getPostTime();
        int a = 0;
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){

    }
}
