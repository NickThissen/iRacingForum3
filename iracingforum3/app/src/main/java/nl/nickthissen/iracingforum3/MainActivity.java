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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.adapters.DrawerAdapter;
import nl.nickthissen.iracingforum3.fragments.DrawerControlFragment;
import nl.nickthissen.iracingforum3.fragments.DrawerListFragment;
import nl.nickthissen.iracingforum3.fragments.ForumListFragment;
import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;
import nl.nickthissen.iracingforum3.models.drawer.ForumListDrawerItem;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.ForumList;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements DrawerAdapter.OnItemClickListener
{
    @ViewById(R.id.drawer_layout) DrawerLayout _drawer;
    @ViewById(R.id.left_drawer) ListView _drawerList;

    private DrawerControlFragment _drawerController;

    private ActionBarDrawerToggle _drawerToggle;
    private CharSequence _title;
    private CharSequence _drawerTitle;

    @Override protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    @AfterViews
    void afterViews()
    {
        this.setupDrawer();
    }

    public ForumList getForums()
    {
        // TODO: load before
        ForumList forumList = new ForumList();
        forumList.forums.add(new Forum(0, "Forum A"));
        forumList.forums.add(new Forum(1, "Forum B"));
        forumList.forums.add(new Forum(2, "Forum C"));
        forumList.forums.add(new Forum(3, "Forum D"));
        forumList.forums.add(new Forum(4, "Forum E"));
        forumList.forums.add(new Forum(5, "Forum F"));

        return forumList;
    }

    //region Navigation Drawer

    @Override public void setTitle(CharSequence title)
    {
        super.setTitle(title);
        _title = title;
    }

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

        // Create a DrawerControlFragment as controller for adding/removing drawer items
        _drawerController = DrawerControlFragment.create();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, _drawerController).commit();
    }

    public void refreshDrawer()
    {
        DrawerAdapter adapter = new DrawerAdapter(this, _drawerController.getDrawerItems(), this);
        _drawerList.setAdapter(adapter);
    }

    public DrawerControlFragment getDrawerController()
    {
        return _drawerController;
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

    //region Fragment transactions

    @Override
    public void onClick(View view, int position)
    {
        // Navigation drawer item clicked
        DrawerItem item = _drawerController.getItem(position);
        if (item != null && !item.isHeader())
        {
            // Select item
            _drawerController.selectItem(item);

            // Close the drawer
            _drawer.closeDrawers();
        }
    }

    @Override
    public void onCloseClick(View view, int position)
    {
        // Navigation drawer item close clicked
        DrawerItem item = _drawerController.getItem(position);
        _drawerController.closeItem(item);
    }

    //endregion

}
