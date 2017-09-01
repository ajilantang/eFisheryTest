package ajilantang.wificonnect;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.BreakIterator;

import ajilantang.wificonnect.Interface.ApiService;
import ajilantang.wificonnect.Model.Quotes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ajilantang on 30/08/17.
 */

public class Api extends Activity {
    private static final String ROOT_URL = "https://api.forismatic.com/api/";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.quotes_forismatic);
        if(isOnline()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService service = retrofit.create(ApiService.class);
            Call<Quotes> call = service.getQuotesDetail();
            call.enqueue(new Callback<Quotes>() {
                @Override
                public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                    TextView quoteText = (TextView) findViewById(R.id.quoteText);
                    TextView quoteAuthor = (TextView) findViewById(R.id.quoteAuthor);
                    quoteAuthor.setText("Author  :  " + response.body().getQuoteAuthor());
                    quoteText.setText("Quotes  :  " + response.body().getQuoteText());
                }

                @Override
                public void onFailure(Call<Quotes> call, Throwable t) {
                    TextView error = (TextView) findViewById(R.id.error);
                    error.setText("Cannot access api");
                }
            });
        }
        else if(!isOnline()){
            TextView error = (TextView) findViewById(R.id.error);
            error.setText("there is no internet access , please connect to wifi or mobile data");
        }

    }
    //check connection on resume

    @Override
    protected void onResume() {
        if (isOnline()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService service = retrofit.create(ApiService.class);
            Call<Quotes> call = service.getQuotesDetail();
            call.enqueue(new Callback<Quotes>() {
                @Override
                public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                    TextView quoteText = (TextView) findViewById(R.id.quoteText);
                    TextView quoteAuthor = (TextView) findViewById(R.id.quoteAuthor);
                    quoteAuthor.setText("Author  :  " + response.body().getQuoteAuthor());
                    quoteText.setText("Quotes  :  " + response.body().getQuoteText());
                }

                @Override
                public void onFailure(Call<Quotes> call, Throwable t) {
                    TextView error = (TextView) findViewById(R.id.error);
                    error.setText("Cannot access api");
                }
            });
        }
        else if(!isOnline()){
            TextView error = (TextView) findViewById(R.id.error);
            error.setText("there is no internet access , please connect to wifi or mobile data");
        }
        super.onResume();
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}