package ajilantang.wificonnect;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;

/**
 * Created by ajilantang on 30/08/17.
 */

public class QrCode extends Activity {

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
                Intent captureIntent = new Intent(QrCode.this, CaptureActivity.class);

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
                Log.d(value, "onActivityResult: ");
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.connect);
                dialog.setTitle("Connect to Network");
                TextView text = (TextView) dialog.findViewById(R.id.ssid);
                final String wifiSSID = value;
                text.setText(wifiSSID);
                Button dialogButton = (Button) dialog.findViewById(R.id.okButton);
                pass = (EditText) dialog.findViewById(R.id.textPassword);

                // if button is clicked, connect to the network;
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String checkPassword = pass.getText().toString();
                        finallyConnect(checkPassword, wifiSSID);
                        dialog.dismiss();
                    }
                });
                dialog.show();
                Toast.makeText(QrCode.this,"Wifi SSID  : "+value,Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(QrCode.this,"failed to scan try again later",Toast.LENGTH_SHORT).show();
            }
        } else {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void finallyConnect(String networkPass, String networkSSID) {

        WifiConfiguration wifiConfig = new WifiConfiguration();
        wifiConfig.SSID = String.format("\"%s\"", networkSSID);
        wifiConfig.preSharedKey = String.format("\"%s\"", networkPass);

        // remember id
        int netId = mainWifiObj.addNetwork(wifiConfig);
        mainWifiObj.disconnect();
        mainWifiObj.enableNetwork(netId, true);
        mainWifiObj.reconnect();

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"\"" + networkSSID + "\"\"";
        conf.preSharedKey = "\"" + networkPass + "\"";
        mainWifiObj.addNetwork(conf);
    }
}
