package ajilantang.wificonnect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ajilantang on 30/08/17.
 */

public class Api extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is Api");
        setContentView(textview);
    }
}