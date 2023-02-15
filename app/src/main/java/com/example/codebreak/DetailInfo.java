package com.example.codebreak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class DetailInfo extends AppCompatActivity {
    TextView textView;
    WifiManager wifiManager;
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        textView=findViewById(R.id.t5);



        getInfo();
    }




    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void getInfo() {
        String ssid="";
        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getSupplicantState() == SupplicantState.COMPLETED) {
            ssid = wifiInfo.getSSID();

        }
        int ip = wifiInfo.getIpAddress();
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
                        "frequency: "+frequency;

        String ssid1 = ""+ssid;
        textView.setText(ssid1);

    }
}