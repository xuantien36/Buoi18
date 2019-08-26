
package com.t3h.buoi18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.t3h.buoi18.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainActivityListener {
    private ActivityMainBinding binding;
    private MyService iBinder;
    private MyReceiver receiver=new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setListener(this);
        IntentFilter filter=new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(receiver,filter);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            MainActivity.this.iBinder = binder.getService();
            binding.tvValue.setText("Service conected");

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


    @Override
    public void onBindClicked() {
        Intent intent = new Intent(this, MyService.class);
//        bindService(intent,connection,BIND_AUTO_CREATE);
        startService(intent);

    }

    @Override
    public void onUnBindClicked() {
//        unbindService(connection);
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(connection);
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}





























