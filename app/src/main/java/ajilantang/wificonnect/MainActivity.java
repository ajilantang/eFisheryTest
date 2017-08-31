package ajilantang.wificonnect;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources ressources = getResources();
        TabHost tabHost = getTabHost();

        // Wifi List tab
        Intent intentWifi = new Intent().setClass(this, WifiList.class);
        TabSpec tabWifiList = tabHost
                .newTabSpec("WifiList")
                .setIndicator("", ressources.getDrawable(R.drawable.wipi_view))
                .setContent(intentWifi);

        // Qr code  tab
        Intent intentQrCode = new Intent().setClass(this, QrCode.class);
        TabSpec tabQrCode = tabHost
                .newTabSpec("Qr.code")
                .setIndicator("", ressources.getDrawable(R.drawable.qr_code))
                .setContent(intentQrCode);

        // Api tab
        Intent intetApi = new Intent().setClass(this, Api.class);
        TabSpec tabApi = tabHost
                .newTabSpec("Api")
                .setIndicator("", ressources.getDrawable(R.drawable.api_view))
                .setContent(intetApi);

        // add all tabs
        tabHost.addTab(tabWifiList);
        tabHost.addTab(tabQrCode);
        tabHost.addTab(tabApi);

        //set Windows tab as default (zero based)
        tabHost.setCurrentTab(2);
    }

}