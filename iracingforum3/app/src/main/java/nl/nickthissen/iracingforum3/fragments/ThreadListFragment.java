package nl.nickthissen.iracingforum3.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterViews;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.MainActivity;
import nl.nickthissen.iracingforum3.adapters.ThreadListAdapter;
import nl.nickthissen.iracingforum3.models.drawer.ThreadDrawerItem;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.Thread;
import nl.nickthissen.iracingforum3.models.forum.ThreadList;

/**
 * Created by Nick on 1/11/2015.
 */
public class ThreadListFragment extends DrawerListFragment implements ThreadListAdapter.ListItemClickListener<Thread>
{
    private static final String KEY_FORUM = "KEY_FORUM";

    private MainActivity _activity;
    private ThreadList _threadList;
    private ThreadListAdapter _adapter;
    private Forum _forum;

    public ThreadListFragment()
    {
    }

    public static ThreadListFragment create(Forum forum)
    {
        // Create new fragment for Forum
        ThreadListFragment fragment = new ThreadListFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_FORUM, forum);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(Bundle bundle)
    {
        _activity = (MainActivity) this.getActivity();
        _forum = (Forum)bundle.getSerializable(KEY_FORUM);

        _threadList = new ThreadList();
    }

    @AfterViews
    void afterViews()
    {
        this.loadThreads();
    }

    private void loadThreads()
    {
        // TODO: Load from website async
        _threadList.threads.add(new Thread(_forum.title + ", Thread A"));
        _threadList.threads.add(new Thread(_forum.title + ", Thread B"));
        _threadList.threads.add(new Thread(_forum.title + ", Thread C"));
        _threadList.threads.add(new Thread(_forum.title + ", Thread D"));
        _threadList.threads.add(new Thread(_forum.title + ", Thread E"));
        _threadList.threads.add(new Thread(_forum.title + ", Thread F"));

        this.loadThreadsFinished();
    }

    private void loadThreadsFinished()
    {
        this.setItems();
    }

    private void setItems()
    {
        _adapter = new ThreadListAdapter(_activity, _threadList.threads, this);
        this.setListAdapter(_adapter);
    }

    @Override
    public void onItemClicked(Thread thread, int position)
    {
        // Create new drawer item and fragment
        ThreadDrawerItem item = createDrawerItem(thread);

        // Replace current
        _activity.replaceDrawerItem(item);
    }

    @Override
    public void onItemLongClicked(Thread thread, int position)
    {
        // Create new drawer item and fragment
        ThreadDrawerItem item = createDrawerItem(thread);

        // Add to navigation drawer
        _activity.addDrawerItem(item);
    }

    private ThreadDrawerItem createDrawerItem(Thread thread)
    {
        PostListFragment fragment = PostListFragment.create(thread);
        ThreadDrawerItem item = new ThreadDrawerItem(thread, fragment);
        return item;
    }
}
