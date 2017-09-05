package ajilantang.wificonnect;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ajilantang.wificonnect.Model.Connection;
import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;

/**
 * Created by ajilantang on 30/08/17.
 */

public class QrCodeActivity extends Activity {

    WifiManager mainWifiObj;

    EditText pass;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_code);
        Button btScan = (Button) findViewById(R.id.bt_scan);
        mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        mainWifiObj.startScan();
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Intent for capture from  ZXing
                Intent captureIntent = new Intent(QrCodeActivity.this, CaptureActivity.class);

                // Scanning load
                CaptureActivityIntents.setPromptMessage(captureIntent, "Barcode scanning...");

                // get result from  startActivityForResult, to get  QR Code scanning
                startActivityForResult(captureIntent, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                String value = data.getStringExtra("SCAN_RESULT");
                connectToWifi(value, false);
                Toast.makeText(QrCodeActivity.this, "Wifi SSID  : " + value, Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(QrCodeActivity.this, "Cancel to scan", Toast.LENGTH_SHORT).show();
            }
        } else {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void connectToWifi(final String wifiSSID, Boolean isWPA) {
        if (isWPA) {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.connect);
            TextView tv = dialog.findViewById(R.id.ssid);
            tv.setText("Connect to " + wifiSSID);
            View dialogButton = dialog.findViewById(R.id.okButton);
            pass = dialog.findViewById(R.id.textPassword);

            // if button is clicked, connect to the network;
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String checkPassword = pass.getText().toString();
                    finallyConnect(wifiSSID, checkPassword);
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            finallyConnect(wifiSSID, "");
        }
    }

    private void finallyConnect(String wifiSSID, String wifiPass) {

        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", wifiSSID);
        if (wifiPass.equals("")) {
           wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        } else {
            wifiConfig.preSharedKey = "\"" + wifiPass + "\"";
        }

        int netId = mainWifiObj.addNetwork(wifiConfig);
        mainWifiObj.disconnect();
        mainWifiObj.enableNetwork(netId, true);
        mainWifiObj.reconnect();

        String result = mainWifiObj.getConnectionInfo().getSSID().toString();
        String ssid = "\"" + wifiSSID + "\"";
        if (!result.equals(ssid)) {
          connectToWifi(wifiSSID, true);
        }
    }

}