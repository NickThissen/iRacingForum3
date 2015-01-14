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

        ForumListDrawerItem item = this.createForumListDrawerItem(_activity.getForums());
        this.initForumListDrawerItem(item);
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

    private ForumListDrawerItem _forumListDrawerItem;
    public ForumListDrawerItem getForumListDrawerItem(){ return _forumListDrawerItem;}

    private ForumListDrawerItem createForumListDrawerItem(ForumList forumList)
    {
        ForumListFragment fragment = ForumListFragment.create(forumList);
        ForumListDrawerItem item = new ForumListDrawerItem(forumList, fragment);
        return item;
    }

    private void initForumListDrawerItem(ForumListDrawerItem item)
    {
        _forumListDrawerItem = item;
        addItem(item);
        selectItem(item);
    }

    private DrawerItem _selectedDrawerItem;
    public DrawerItem getSelectedDrawerItem() { return _selectedDrawerItem;}

    public DrawerItem getItem(int position)
    {
        if (position < 0 || position >= _drawerItems.size()) return null;
        return _drawerItems.get(position);
    }

    /**
     * Is the ForumList drawer item currently selected?
     */
    public boolean isForumListSelected()
    {
        return _selectedDrawerItem != null
                && _selectedDrawerItem.fragment() != null
                && _forumListDrawerItem != null
                && _forumListDrawerItem.fragment() != null
                && _selectedDrawerItem.fragment().tag() == _forumListDrawerItem.fragment().tag();
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

        // Show the fragment
        this.swapFragment(item.fragment());

        // Refresh the drawer
        _activity.refreshDrawer();
    }

    /**
     * Removes the currently selected item from the drawer and closes the Fragment
     */
    public void removeCurrentItem()
    {
        if (!isAttached()) return;
        DrawerItem item = this.getSelectedDrawerItem();
        this.closeItem(item);
    }

    /**
     * Add a drawer item to the drawer but do not show it (yet).
     */
    public void addItem(DrawerItem item)
    {
        if (!isAttached()) return;
        if (item != null)
        {
            _drawerItems.add(item);
            _activity.refreshDrawer();
        }
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

        this.swapFragment(item.fragment());
        this.selectItem(selected);
    }

    private void removeDrawerItem(DrawerItem item)
    {
        if (item != null)
        {
            _drawerItems.remove(item);
            _activity.refreshDrawer();
        }
    }

    private void swapFragment(DrawerListFragment fragment)
    {
        if (!isAttached()) return;
        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment, fragment.tag()).addToBackStack(fragment.tag()).commit();

        _activity.setTitle(fragment.title());
    }

    private void removeFragment(DrawerListFragment fragment)
    {
        if (!isAttached()) return;
        FragmentManager manager = _activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment).commit();
    }

}
