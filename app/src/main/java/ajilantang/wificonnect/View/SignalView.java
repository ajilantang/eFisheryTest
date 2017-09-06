package ajilantang.wificonnect.View;

/**
 * Created by ajilantang on 05/09/17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import ajilantang.wificonnect.R;


/**
 * Signal view branch to show image based on signal strengh (1-5)
 */
public class SignalView extends View {

    Bitmap ic_signal_0 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_signal_wifi_0_bar_white_24dp);
    Bitmap ic_signal_1 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_signal_wifi_1_bar_white_24dp);
    Bitmap ic_signal_2 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_signal_wifi_2_bar_white_24dp);
    Bitmap ic_signal_3 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_signal_wifi_3_bar_white_24dp);
    Bitmap ic_signal_4 = BitmapFactory.decodeResource(getResources(), R.drawable.ic_signal_wifi_4_bar_white_24dp);
    Bitmap ic_signal_off = BitmapFactory.decodeResource(getResources(), R.drawable.ic_signal_wifi_off_white_24dp);

    Bitmap ic_current = ic_signal_off;

    public void setLevel(int value){

        if (value >= 4) {
            ic_current = ic_signal_4;
        } else if (value >= 3) {
            ic_current = ic_signal_3;
        } else if (value >= 2) {
            ic_current = ic_signal_2;
        } else if (value >= 1) {
            ic_current = ic_signal_1;
        } else if (value >= 0) {
            ic_current = ic_signal_0;
        } else if (value < 0) {
            ic_current = ic_signal_off;
        }

        invalidate();
    }

    public SignalView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(ic_current, 0, 0, null);
    }
}
