package nl.nickthissen.iracingforum3;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.adapters.DrawerAdapter;
import nl.nickthissen.iracingforum3.fragments.ForumListFragment;
import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;
import nl.nickthissen.iracingforum3.models.drawer.DrawerItemHeader;
import nl.nickthissen.iracingforum3.models.drawer.ForumDrawerItem;
import nl.nickthissen.iracingforum3.models.drawer.ForumListDrawerItem;
import nl.nickthissen.iracingforum3.models.drawer.ThreadDrawerItem;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.ForumList;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements DrawerAdapter.OnItemClickListener
{
    @ViewById(R.id.drawer_layout) DrawerLayout _drawer;
    @ViewById(R.id.left_drawer) ListView _drawerList;

    private ArrayList<DrawerItem> _drawerItems;
    private ForumListDrawerItem _forumListDrawerItem;

    private ActionBarDrawerToggle _drawerToggle;
    private CharSequence _title;
    private CharSequence _drawerTitle;

    @Override protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        _drawerItems = new ArrayList<>();

    }

    @AfterViews
    void afterViews()
    {
        this.getForums();
        this.setupDrawer();
        this.refreshDrawer();
    }

    private void getForums()
    {
        // TODO: load before
        ForumList forumList = new ForumList();
        forumList.forums.add(new Forum("Forum A"));
        forumList.forums.add(new Forum("Forum B"));
        forumList.forums.add(new Forum("Forum C"));
        forumList.forums.add(new Forum("Forum D"));
        forumList.forums.add(new Forum("Forum E"));
        forumList.forums.add(new Forum("Forum F"));

        ForumListFragment fragment = ForumListFragment.create(forumList);
       _forumListDrawerItem = new ForumListDrawerItem(forumList, fragment);
    }

    //region Navigation Drawer

    private void setupDrawer()
    {
        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        _title = _drawerTitle = this.getTitle();

        _drawerToggle = new ActionBarDrawerToggle(this, _drawer,
                R.string.open_drawer, R.string.close_drawer)
        {
            @Override public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(_title);
                invalidateOptionsMenu();
            }

            @Override public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(_drawerTitle);
                invalidateOptionsMenu();
            }
        };

        _drawer.setDrawerListener(_drawerToggle);

//        if (savedInstanceState == null) {
//            selectItem(0);
//        }
    }

    private void refreshDrawer()
    {
        ArrayList<DrawerItem> items = new ArrayList<>();

        // Add forum list
        items.add(_forumListDrawerItem);

        // Add forums
        items.add(new DrawerItemHeader("FORUMS"));
        for (DrawerItem item : _drawerItems)
        {
            if (item.type() == DrawerItem.DrawerItemTypes.Forum)
            {
                items.add(item);
            }
        }

        // Add threads
        items.add(new DrawerItemHeader("THREADS"));
        for (DrawerItem item : _drawerItems)
        {
            if (item.type() == DrawerItem.DrawerItemTypes.Thread)
            {
                items.add(item);
            }
        }

        _drawerItems = items;
        DrawerAdapter adapter = new DrawerAdapter(this, items, this);
        _drawerList.setAdapter(adapter);
    }

    public void addDrawerItem(DrawerItem item)
    {
        _drawerItems.add(item);
        this.refreshDrawer();
    }

    public void replaceDrawerItem(DrawerItem item)
    {
        this.refreshDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        _drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        _drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (_drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    //endregion

    // region Fragment Transactions

    public void showItem(DrawerItem item)
    {
        this.swapFragment(item.fragment());
    }

    private void swapFragment(Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    // endregion


    @Override public boolean onPrepareOptionsMenu(Menu menu)
    {
        if (_drawer.isDrawerOpen(_drawerList))
        {

        }
        else
        {

        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View view, int position)
    {
        // Navigation drawer item selected
        DrawerItem item = _drawerItems.get(position);
        if (item != null && !item.isHeader())
        {
            this.showItem(item);
        }
    }

    @Override
    public void onCloseClick(View view, int position)
    {
        // Navigation drawer item close clicked
        DrawerItem item = _drawerItems.get(position);
        if (item != null && !item.isHeader())
        {
            _drawerItems.remove(position);
            this.refreshDrawer();
        }
    }
}
