package com.mrt.mravi.mrt_blog1;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by mravi on 07-01-2018.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
private static  final String REG_TOKEN="REG_TOKEN";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        //Log.d(TAG, "Refreshed token: " + refreshedToken);

        Log.d(REG_TOKEN,recent_token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //sendRegistrationToServer(refreshedToken);
    }
}
