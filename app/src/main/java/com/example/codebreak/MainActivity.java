package com.example.codebreak;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import soup.neumorphism.NeumorphImageButton;

public class MainActivity extends AppCompatActivity {
    NeumorphImageButton btn;
    TextView tv2,tv3;
    WifiManager wifiManager;



    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.b1);
        tv2 = findViewById(R.id.t3);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        getInfo();



        btn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),DetailInfo.class);
                startActivity(i);
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void getInfo() {
        String ssid="";
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
             ssid = wifiInfo.getSSID();

        }
       /* int ip = wifiInfo.getIpAddress();
        String bssid = wifiInfo.getBSSID();
        String mac = wifiInfo.getMacAddress();
        int rssi = wifiInfo.getRssi();
        int netid = wifiInfo.getNetworkId();
        int linkspeed = wifiInfo.getLinkSpeed();
        int frequency = wifiInfo.getFrequency();
        int txLinkSpeed = wifiInfo.getTxLinkSpeedMbps();
        int rxLinkSpeed = wifiInfo.getRxLinkSpeedMbps();
        String info = "ipAddress: " + ip + "\n" +
                        "bssid: " + bssid +  "\n"+
                        "mac: " + mac +  "\n"+
                        "netID: " + netid +  "\n"+
                        "txLinkSpeed: " + txLinkSpeed +  "\n"+
                        "rxLinkSpeed: " + rxLinkSpeed +  "\n"+
                        "rssi: "+rssi+"\n"+
                        "linkspeed: "+linkspeed+"\n"+
                        "ssid: "+ssid+"\n"+
                        "frequency: "+frequency;*/
        /*tv1.setText(info);*/
        String ssid1 = ""+ssid;
        tv2.setText(ssid1);

    }
}