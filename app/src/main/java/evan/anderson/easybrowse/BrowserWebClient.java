package evan.anderson.easybrowse;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebViewClient;
import android.webkit.WebView;

/**
 * Created by Evan on 3/3/2018.
 */

public class BrowserWebClient extends WebViewClient
{
    //Prevents the WebView from taking the entire screen.
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }

    //Proceeds with page loading if SSL error is detected.
    //Add notification in the future.
    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
    {
        handler.proceed();
    }
}
