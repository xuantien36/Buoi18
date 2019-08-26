package com.t3h.buoi18;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        Toast.makeText(context,action,Toast.LENGTH_LONG).show();

    }
}
