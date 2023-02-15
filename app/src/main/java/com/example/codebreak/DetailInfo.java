package com.example.codebreak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.github.anastr.speedviewlib.AwesomeSpeedometer;

public class DetailInfo extends AppCompatActivity {
    TextView textView,text_ip,text_frequency,text_channel,text_rssi,text_mac,text_bssid,text_linkspeed,text_netid,distance,text_indicator;
    WifiManager wifiManager;
    AwesomeSpeedometer awesomeSpeedometer;
    Handler handler = new Handler();
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        textView=findViewById(R.id.t5);
        text_ip=findViewById(R.id.t6);
        text_frequency=findViewById(R.id.t7);
        text_channel=findViewById(R.id.t8);
        text_rssi=findViewById(R.id.t9);
        text_mac=findViewById(R.id.t10);
        text_bssid=findViewById(R.id.t11);
        text_linkspeed=findViewById(R.id.t12);
        text_netid=findViewById(R.id.t13);
        distance=findViewById(R.id.t14);
        text_indicator=findViewById(R.id.text_indicator);
        awesomeSpeedometer=findViewById(R.id.awesomeSpeedometer);
        awesomeSpeedometer.setSpeedAt(5);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        handler.post(runnableCode);
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
        text_ip.setText("IP Address: "+ip);
        text_frequency.setText("Frequency: "+frequency);

        int channel = 0;
        if (frequency >= 2412 && frequency <= 2484) {
            channel = (frequency - 2412) / 5 + 1;
        } else if (frequency >= 5170 && frequency <= 5825) {
            channel = (frequency - 5170) / 5 + 34;
        }
        text_channel.setText("Channel: "+channel);

        text_rssi.setText("RSSI: "+rssi);
        text_mac.setText("MAC: "+mac);
        text_bssid.setText("BSSID: "+bssid);
        text_linkspeed.setText("Link Speed: "+linkspeed);
        text_netid.setText("Network ID: "+netid);

        int referenceRssi = -40;
        float distance_val = (float) Math.pow(10, (referenceRssi - rssi) / (10 * 2.0));
        distance.setText("Distance : "+distance_val+"m");
        int signal_Strength = rssi+120;
        awesomeSpeedometer.speedTo(signal_Strength);
        if(signal_Strength > 60) text_indicator.setText("STRONG CONNECTION!");
        else if(signal_Strength > 40 && signal_Strength<60) text_indicator.setText("AVERAGE CONNECTION!");
        else text_indicator.setText("WEAK CONNECTION! (please move closer)");
    }

    private Runnable runnableCode = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public void run() {
            getInfo();
            handler.postDelayed(this, 2000);
        }
    };
}