package ajilantang.wificonnect;

import android.Manifest;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends TabActivity {
    public static final int ACCESS_COARSE_LOCATION = 100;
    public static final int CAMERA = 101;
    public static final int ACCESS_FINE_LOCATION = 102;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources ressources = getResources();
        TabHost tabHost = getTabHost();

        // Wifi List tab
        Intent intentWifi = new Intent().setClass(this, WifiListActivity.class);
        TabSpec tabWifiList = tabHost
                .newTabSpec("Wifi List")
                .setIndicator("", ressources.getDrawable(R.drawable.wipi_view))
                .setContent(intentWifi);

        // Qr code  tab
        Intent intentQrCode = new Intent().setClass(this, QrCodeActivity.class);
        TabSpec tabQrCode = tabHost
                .newTabSpec("Qr.code")
                .setIndicator("", ressources.getDrawable(R.drawable.qr_code))
                .setContent(intentQrCode);

        // Api tab
        Intent intetApi = new Intent().setClass(this, ApiActivity.class);
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
        // check permissions
        doCheckPermissions();

    }

    private void doCheckPermissions() {
        if (isAccessesGranted()) {
            gettingPermissions();
        }
    }

    /**
     * Check if our app has all required access
     * @return boolean
     */
    private boolean isAccessesGranted() {
        int granted = PackageManager.PERMISSION_GRANTED;
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != granted
                || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != granted
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != granted;
    }

    /**
     * Get required permission
     */
    private void gettingPermissions() {
        Map<String, Integer> permissions = new HashMap<>();
        permissions.put(Manifest.permission.ACCESS_COARSE_LOCATION, ACCESS_COARSE_LOCATION);
        permissions.put(Manifest.permission.CAMERA, CAMERA);
        permissions.put(Manifest.permission.ACCESS_FINE_LOCATION, ACCESS_FINE_LOCATION);

        for (String permission : permissions.keySet()) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    Toast.makeText(MainActivity.this,"getting permission",Toast.LENGTH_LONG).show();
                }
                requestAccess(permission, permissions.get(permission));
                break;
            }
        }
    }

    /**
     * Request permission to system
     * @param permission
     * @param requestCode
     */
    private void requestAccess(String permission, int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case ACCESS_COARSE_LOCATION:
            case CAMERA:
            case ACCESS_FINE_LOCATION:
            default:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"acces allowed",Toast.LENGTH_LONG).show();
                } else {
                    // TODO: disable functionality that needs this permissions
                    Toast.makeText(MainActivity.this,"Access Denied",Toast.LENGTH_LONG).show();
                }
                doCheckPermissions();
                break;
        }
    }


}