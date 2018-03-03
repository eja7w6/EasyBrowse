package evan.anderson.easybrowse;

import android.webkit.WebViewClient;
import android.webkit.WebView;

/**
 * Created by Evan on 3/3/2018.
 */

public class BrowserWebClient extends WebViewClient
{
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;
    }
}
