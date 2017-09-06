package ajilantang.wificonnect.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ajilantang on 02/09/17.
 */

/**
 * quotes model from api formastic
 */
public class Quotes {
    @SerializedName("quoteText")
    @Expose
    private String quoteText;
    @SerializedName("quoteAuthor")
    @Expose
    private String quoteAuthor;
    @SerializedName("senderName")
    @Expose
    private String senderName;
    @SerializedName("quoteLink")
    @Expose
    private String quoteLink;

    public String getQuoteText() {
        return quoteText;
    }
    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String asdasd) {
        this.quoteAuthor = asdasd;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getQuoteLink() {
        return quoteLink;
    }

    public void setQuoteLink(String quoteLink) {
        this.quoteLink = quoteLink;
    }
}
