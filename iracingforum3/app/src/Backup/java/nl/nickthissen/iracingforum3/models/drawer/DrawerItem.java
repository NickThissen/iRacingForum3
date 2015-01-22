package nl.nickthissen.iracingforum3.models.drawer;

import android.support.v4.app.ListFragment;

import java.io.Serializable;
import java.util.Stack;

import nl.nickthissen.iracingforum3.fragments.DrawerListFragment;

/**
 * Created by Nick on 1/11/2015.
 */
public abstract class DrawerItem implements Serializable
{
    private Stack<DrawerItem> _backStack;

    public DrawerItem(String title, DrawerItemTypes type)
    {
        this.title = title;
        this._type = type;

        _backStack = new Stack<>();
    }

    public String title;

    public boolean isHeader()
    {
        return _type == DrawerItemTypes.Header;
    }

    public void replaceFragment(DrawerListFragment fragment)
    {

    }

    public boolean canClick()
    {
        return this.type() == DrawerItemTypes.ForumList
                || this.type() == DrawerItemTypes.Forum
                || this.type() == DrawerItemTypes.Thread;
    }


    public boolean canClose()
    {
        return this.type() != DrawerItemTypes.Header;
    }

    public boolean isSelected;

    protected DrawerItemTypes _type;
    public DrawerItemTypes type()
    {
        return _type;
    }

    protected DrawerListFragment _fragment;
    public DrawerListFragment fragment()
    {
        return _fragment;
    }

    public enum DrawerItemTypes
    {
        Header,
        ForumList,
        Forum,
        Thread
    }
}
