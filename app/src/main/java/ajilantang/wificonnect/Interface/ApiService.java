package ajilantang.wificonnect.Interface;


import ajilantang.wificonnect.Model.Quotes;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ajilantang on 02/09/17.
 */

public interface ApiService {
    /*
  Retrofit get annotation with our URL
  And our method that will return us the List of ContactList
  */
    @GET("1.0/?method=getQuote&lang=en&format=json")
    Call<Quotes> getQuotesDetail();
}
