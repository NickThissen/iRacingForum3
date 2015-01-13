package nl.nickthissen.iracingforum3.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.MainActivity;
import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;
import nl.nickthissen.iracingforum3.models.drawer.ForumListDrawerItem;

/**
 * Created by Nick on 1/13/2015.
 */
public class DrawerControlFragment extends Fragment
{
    private MainActivity _activity;

    public DrawerControlFragment()
    {
        // Retain this fragment across config changes (the only reason this class is a Fragment)
        this.setRetainInstance(true);

        _drawerItems = new ArrayList<>();
    }

    public static DrawerControlFragment create()
    {
        DrawerControlFragment fragment = new DrawerControlFragment();
        return fragment;
    }

    @Override public void onAttach(Activity activity)
    {
        _activity = (MainActivity) activity;
    }

    private ArrayList<DrawerItem> _drawerItems;
    public ArrayList<DrawerItem> getDrawerItems() {return _drawerItems;}

    private ForumListDrawerItem _forumListDrawerItem;
    public ForumListDrawerItem getForumListDrawerItem(){ return _forumListDrawerItem;}
    public void initForumListDrawerItem(ForumListDrawerItem item)
    {
        _forumListDrawerItem = item;
        addItem(item);
        selectItem(item);
    }

    private DrawerItem _selectedDrawerItem;
    public DrawerItem getSelectedDrawerItem() { return _selectedDrawerItem;}

    public void selectItem(DrawerItem item)
    {
        // Add item
        _drawerItems.add(item);

        // Select item
        for (DrawerItem d : _drawerItems)
        {
            d.isSelected = false;
        }
        item.isSelected = true;

        _selectedDrawerItem = item;

        // Show the fragment
        this.swapFragment(item.fragment());
    }

    public void addItem(DrawerItem item)
    {

    }

    public void removeItem(DrawerItem item)
    {

    }

    private void swapFragment(DrawerListFragment fragment)
    {
        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment, fragment.tag()).addToBackStack(fragment.tag()).commit();
    }

    private void removeFragment(DrawerListFragment fragment)
    {
        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment).commit();
    }
}
