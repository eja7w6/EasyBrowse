package evan.anderson.easybrowse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OpenBookmarksActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_bookmarks);

        Toolbar menuToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(menuToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Loads items to the app bar from menu.xml.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bookmarks_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int ItemID = item.getItemId();

        switch(ItemID)
        {
            case R.id.bookmarks_back:
                returnToBrowse();
                break;
        }

        return true;
    }

    //Resumes the browser activity.
    private void returnToBrowse()
    {
        Intent intent = new Intent(this, BrowseActivity.class);
        startActivity(intent);
    }
}
