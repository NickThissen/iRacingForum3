package nl.nickthissen.iracingforum3.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Stack;

import nl.nickthissen.iracingforum3.MainActivity;
import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;
import nl.nickthissen.iracingforum3.models.drawer.ForumListDrawerItem;
import nl.nickthissen.iracingforum3.models.forum.ForumList;

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
        super.onAttach(activity);
        _activity = (MainActivity) activity;

        this.addNewTab();
    }

    @Override public void onDetach()
    {
        super.onDetach();
        _activity = null;
    }

    private boolean isAttached()
    {
        return _activity != null;
    }

    private ArrayList<DrawerItem> _drawerItems;
    public ArrayList<DrawerItem> getDrawerItems() {return _drawerItems;}

    private ForumListDrawerItem createForumListDrawerItem(ForumList forumList)
    {
        ForumListFragment fragment = ForumListFragment.create(forumList);
        ForumListDrawerItem item = new ForumListDrawerItem(forumList, fragment);
        return item;
    }

    private DrawerItem _selectedDrawerItem;
    public DrawerItem getSelectedDrawerItem() { return _selectedDrawerItem;}

    public DrawerItem getItem(int position)
    {
        if (position < 0 || position >= _drawerItems.size()) return null;
        return _drawerItems.get(position);
    }

    public void addNewTab()
    {
        if (!this.isAttached()) return;

        DrawerItem item = this.createForumListDrawerItem(_activity.getForums());
        _drawerItems.add(item);
        this.addFragment(item.fragment());
        this.selectItem(item);
    }

    public void replaceCurrentItem(DrawerItem item)
    {
        // Stay in the current 'tab' and replace Fragment

        // Get selected item and replace it
        DrawerItem selected = this.getSelectedDrawerItem();

        // Replace Fragments
        this.hideFragment(selected.fragment());
        this.addFragment(item.fragment());

        // Remove old item
        _drawerItems.remove(selected);

        // Add new item
        _drawerItems.add(item);

        // Select new item
        this.selectItem(item);
    }

    public void switchToItem(DrawerItem item)
    {
        // Get selected item and replace it
        DrawerItem selected = this.getSelectedDrawerItem();
        this.replaceFragment(selected.fragment(), item.fragment());

        // Select it
        this.selectItem(item);
    }

    /**
     * Select a drawer item and show its Fragment
     */
    public void selectItem(DrawerItem item)
    {
        if (!isAttached()) return;

        // Select item
        for (DrawerItem d : _drawerItems)
        {
            d.isSelected = false;
        }
        item.isSelected = true;

        _selectedDrawerItem = item;

        // Refresh the drawer
        _activity.refreshDrawer();
    }

    /**
     * Remove a drawer item from the drawer and remove the corresponding Fragment.
     */
    public void closeItem(DrawerItem item)
    {
        if (!isAttached()) return;
        if (item != null && item.canClose())
        {
            item.fragment().close();
            this.removeFragment(item.fragment());
            this.removeDrawerItem(item);
        }
    }

    public void openInBackground(DrawerItem item)
    {
        // Show and immediately show previous again to trigger loading
        DrawerItem selected = this.getSelectedDrawerItem();

        // Add fragment
        this.addFragment(item.fragment());

        // Hide it again by replacing it with previously selected
        this.replaceFragment(item.fragment(), selected.fragment());
    }

    private void removeDrawerItem(DrawerItem item)
    {
        if (item != null)
        {
            _drawerItems.remove(item);
            _activity.refreshDrawer();
        }
    }

    private void addFragment(DrawerListFragment fragment)
    {
        if (!isAttached()) return;
        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content_frame, fragment, fragment.tag())
                .addToBackStack("ADD_" + fragment.tag())
                .commit();

        _activity.getSupportActionBar().setTitle(fragment.title());
    }

    private void replaceFragment(DrawerListFragment current, DrawerListFragment replacement)
    {
        if (!isAttached()) return;

        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if (current == null)
        {
            transaction.show(replacement)
                    .addToBackStack("REPLACE_NULL_BY_" + replacement.tag())
                    .commit();
        }
        else
        {
            transaction.hide(current)
                    .show(replacement)
                    .addToBackStack("REPLACE_" + current.tag() + "_BY_" + replacement.tag())
                    .commit();
        }

        _activity.setTitle(replacement.title());
    }

    private void hideFragment(DrawerListFragment fragment)
    {
        if (!isAttached()) return;

        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(fragment).addToBackStack("HIDE_" + fragment.tag()).commit();
    }

    private void removeFragment(DrawerListFragment fragment)
    {
        if (!isAttached()) return;
        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment).commit();
    }

    public boolean onBackPressed()
    {
        DrawerItem selected = this.getSelectedDrawerItem();
        if (selected != null && selected.fragment() != null)
        {
            if (selected.fragment().onBackPressed()) return true;
        }
        return false;
    }

}
