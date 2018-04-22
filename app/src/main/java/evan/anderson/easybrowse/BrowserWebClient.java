package evan.anderson.easybrowse;

import android.content.Context;
import android.net.http.SslError;
import android.util.Patterns;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

/**
 * Created by Evan on 3/3/2018.
 */

public class BrowserWebClient extends WebViewClient
{

    private WebView browserView;
    private Vector<String> pageHistory;
    private Vector<String> bookmarks;
    private int currentPage;
    private Context currentContext;

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
