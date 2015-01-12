package nl.nickthissen.iracingforum3.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.MainActivity;
import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.adapters.ForumListAdapter;
import nl.nickthissen.iracingforum3.adapters.ListAdapter;
import nl.nickthissen.iracingforum3.adapters.ThreadListAdapter;
import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;
import nl.nickthissen.iracingforum3.models.drawer.ForumDrawerItem;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.Thread;
import nl.nickthissen.iracingforum3.models.forum.ForumList;
import nl.nickthissen.iracingforum3.models.forum.ThreadList;

/**
 * Created by Nick on 1/11/2015.
 */
@EFragment(R.layout.forumlist)
public class ForumListFragment extends DrawerListFragment implements ForumListAdapter.ListItemClickListener<Forum>
{
    private static final String KEY_FORUMLIST = "KEY_FORUMLIST";

    private MainActivity _activity;
    private ForumList _forumList;
    private ForumListAdapter _adapter;

    public ForumListFragment()
    {
    }

    public static ForumListFragment create(ForumList forums)
    {
        ForumListFragment fragment = new ForumListFragment_();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FORUMLIST, forums);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);

        _activity = (MainActivity) this.getActivity();

        Bundle args = this.getArguments();
        _forumList = (ForumList)args.getSerializable(KEY_FORUMLIST);
    }

    @AfterViews
    void afterViews()
    {
        this.setItems();
    }

    private void setItems()
    {
        _adapter = new ForumListAdapter(_activity, _forumList.forums, this);
        this.setListAdapter(_adapter);
    }

    @Override
    public void onItemClicked(Forum forum, int position)
    {
        // Create new drawer item and fragment
        ForumDrawerItem item = createDrawerItem(forum);

        // Replace current
        _activity.showDrawerItem(item);
    }

    @Override
    public void onItemLongClicked(Forum forum, int position)
    {
        // Create new drawer item and fragment
        ForumDrawerItem item = createDrawerItem(forum);

        // Add it to the navigation drawer in the background
        _activity.addDrawerItem(item);
        _activity.showDrawerItemInBackground(item);
    }

    private ForumDrawerItem createDrawerItem(Forum forum)
    {
        ThreadListFragment fragment = ThreadListFragment.create(forum);
        ForumDrawerItem item = new ForumDrawerItem(forum, fragment);
        return item;
    }

    @Override public void close()
    {
        // TODO: cancel web tasks
    }

    @Override public String tag()
    {
        return "FORUMLISTFRAGMENT";
    }
}
