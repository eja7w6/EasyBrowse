package evan.anderson.easybrowse;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.os.Bundle;
import android.webkit.WebView;
import evan.anderson.easybrowse.BrowserWebClient;

public class BrowseActivity extends AppCompatActivity
{
    private WebView browserView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        Toolbar browserToolbar = (Toolbar) findViewById(R.id.browser_toolbar);
        setSupportActionBar(browserToolbar);

        browserView = findViewById(R.id.browser_view);
        browserView.setWebViewClient(new BrowserWebClient());

        browserView.loadUrl("http://www.google.com");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}
