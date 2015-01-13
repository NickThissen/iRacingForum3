package nl.nickthissen.iracingforum3.fragments;

import android.os.Bundle;
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

    @Override
    public void onCreate(Bundle bundle)
    {
        // Retain this instance across config changes
        this.setRetainInstance(true);
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

    public abstract String tag();
    public abstract void close();
}
