package com.example.matteo.app1;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by matteo on 28/03/17.
 */

public class MyFirebaseInstaceService extends FirebaseInstanceIdService {

    public static final String REG_TOKEN = "REG_TOKEN";
    public  String recent_token;

    @Override
    public void onTokenRefresh() {
        recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recent_token);
        System.out.println(recent_token);
    }



}
