package evan.anderson.easybrowse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Vector;

public class BrowseActivity extends AppCompatActivity
{
    private WebView browserView;
    private WebSettings browserSettings;
    private EditText textFieldURL;
    private Vector<String> pageHistory;
    private Vector<String> bookmarks;
    private int currentPage;
    private String homeAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        pageHistory = new Vector<String>();
        bookmarks = new Vector<String>();
        currentPage = 0;

        Context currentContext = getApplicationContext();

        //Inflates the App bar.
        Toolbar browserToolbar = (Toolbar) findViewById(R.id.browser_toolbar);
        setSupportActionBar(browserToolbar);

        textFieldURL = findViewById(R.id.url_field);

        //Links browserView to the browser_view WebView object and sets a custom client that
        //prevents WebView from covering the whole screen.
        browserView = (WebView) findViewById(R.id.browser_view);
        browserView.setWebViewClient(new BrowserWebClient());

        browserSettings = browserView.getSettings();
        browserSettings.setJavaScriptEnabled(true);

        //Handler for go button.
        final ImageButton go_btn = (ImageButton) findViewById(R.id.go_btn);
        go_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                //If loadURL successfully completes, add the URL string to history.
                if(goTo(textFieldURL.getText().toString()))
                {
                    addToHistory(textFieldURL.getText().toString());
                }
            }
        });

        homeAddress = "http://www.google.com";

        //To do: Add home page, load here.
        goTo(homeAddress);
        addToHistory(homeAddress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Loads items to the app bar from menu.xml.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int ID = item.getItemId();

        //Selects appropriate action.
        switch (ID)
        {
            //The reload menu item was pressed.
            case R.id.action_reload:
                browserView.reload();
                break;
            //Forward button was pressed.
            case R.id.action_forward:
                if(currentPage < (pageHistory.size() - 1))
                {
                    currentPage++;
                    goTo(pageHistory.elementAt(currentPage));
                }
                break;
            //Back button was pressed.
            case R.id.action_backward:
                if(currentPage > 0)
                {
                    currentPage--;
                    goTo(pageHistory.elementAt(currentPage));
                }
                break;
            //Settings button was pressed.
            case R.id.action_settings:
                break;
            //Clear history button was pressed.
            case R.id.action_clear_history:
                clearHistory();
                break;
            case R.id.action_add_to_bookmarks:
                addToBookmarks(browserView.getUrl());
                break;
            case R.id.action_open_bookmarks:
                openBookmarksActivity();
                break;
            case R.id.action_home:
                goTo(homeAddress);
                break;
            case R.id.action_set_home:
                homeAddress = pageHistory.elementAt(currentPage);
                break;
        }

        return true;
    }

    //Purpose: Take a string, check that is a valid URL, and load it to BrowserView.
    //Returns: True if URL is valid and successfully loads, false otherwise.
    private boolean goTo(String inputURL)
    {

        if(Patterns.WEB_URL.matcher(inputURL).matches())
        {
            //Fixes URLs lacking http://
            if(!inputURL.contains("http"))
            {
                String fixedURL = "http://" + inputURL;
                browserView.loadUrl(fixedURL);
                return true;
            }

            browserView.loadUrl(inputURL);
            return true;
        }
        else
        {
            Context currentContext = getApplicationContext();
            String failedToLoad = "Invalid URL, failed to load.";

            Toast failMessage = Toast.makeText(currentContext,failedToLoad, Toast.LENGTH_SHORT);
            failMessage.show();

            return false;
        }

    }

    //Purpose: Take a valid URL string and add it to pageHistory.
    //Returns: True if item is successfully added, false otherwise.
    private boolean addToHistory(String inputURL)
    {
        //Clears forward history when a new page is loaded.
        while((pageHistory.size() - 1) > currentPage)
        {
            pageHistory.remove(pageHistory.size() - 1);
        }

        //Adds the URL to the end of pageHistory and sets current page to the index of URL.
        pageHistory.add(inputURL);
        currentPage = pageHistory.size() - 1;

        return true;
    }

    //Purpose: Take a valid URL string and add it to bookmarks.
    //Returns: True if item is added, false otherwise.
    private boolean addToBookmarks(String inputUrl)
    {
        //Checks that the inputUrl isn't already in the bookmarks vector.
        for(int i = 0; i < bookmarks.size(); i++)
        {
            if(inputUrl.equals(bookmarks.get(i)))
            {
                return false;
            }
        }

        bookmarks.add(inputUrl);

        return true;
    }

    //Purpose: Deletes all entries in the history log.
    //Returns: Nothing.
    private void clearHistory()
    {
        while(pageHistory.size() > 0)
        {
            pageHistory.clear();
            currentPage = 0;
        }
    }

    private void openBookmarksActivity()
    {
        Intent intent = new Intent(this, OpenBookmarksActivity.class);
        startActivity(intent);
    }

}
