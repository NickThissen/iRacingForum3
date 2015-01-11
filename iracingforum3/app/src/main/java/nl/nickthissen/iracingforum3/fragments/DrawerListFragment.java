package nl.nickthissen.iracingforum3.fragments;

import android.support.v4.app.ListFragment;

import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;

/**
 * Created by Nick on 1/11/2015.
 */
public abstract class DrawerListFragment extends ListFragment
{
    public DrawerListFragment()
    {
    }

    protected DrawerItem _drawerItem;

    public DrawerItem getDrawerItem()
    {
        return _drawerItem;
    }

    public void setDrawerItem(DrawerItem item)
    {
        _drawerItem = item;
    }
}
