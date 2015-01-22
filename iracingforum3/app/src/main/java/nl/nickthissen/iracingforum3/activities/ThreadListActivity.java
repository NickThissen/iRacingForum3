package nl.nickthissen.iracingforum3.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

import nl.nickthissen.iracingforum3.Log;
import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.adapters.ListAdapter;
import nl.nickthissen.iracingforum3.adapters.ThreadListAdapter;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.Thread;
import nl.nickthissen.iracingforum3.models.forum.ThreadList;
import nl.nickthissen.iracingforum3.network.ForumNetwork;

/**
 * Created by Nick on 1/16/2015.
 */
@EActivity(R.layout.threadlist)
public class ThreadListActivity extends BaseListActivity<ThreadList, Thread> implements ListAdapter.ListItemClickListener<Thread>
{
    private static final String KEY_FORUM = "KEY_FORUM";
    private static final String KEY_THREADLIST = "KEY_THREADLIST";

    private Forum _forum;
    private ThreadList _threadList;

    public static Intent getIntent(Context context, Forum forum)
    {
        Intent intent = ThreadListActivity_.intent(context).get();
        intent.putExtra(KEY_FORUM, forum);
        return intent;
    }

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Log.info("ThreadListActivity.onCreate");

        // Get forum from Intent
        _forum = this.getIntentExtra(KEY_FORUM);

        // No forum passed, get from bundle instead
        if (_forum == null)
        {
            _forum = getExtra(bundle, KEY_FORUM);
        }

        // Get previous threadlist from bundle
        _threadList = this.getExtra(bundle, KEY_THREADLIST);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle)
    {
        // Save forum and threadlist
        bundle.putSerializable(KEY_FORUM, _forum);
        bundle.putSerializable(KEY_THREADLIST, _threadList);
    }

    @Override
    public void startLoading()
    {
        Log.info("ThreadListActivity -- start loading forum " + _forum.title);

        if (_threadList == null)
        {
            // No threadlist cached, load
            ForumNetwork.getInstance().getThreadList(_forum, getLoader());
        }
        else
        {
            // Threadlist cached, no need to load
            this.setListObject(_threadList);
        }
    }

    @Override
    public void onListReady(ThreadList list)
    {

    }

    @Override
    public void onItemClicked(Thread item, int position)
    {

    }

    @Override
    public void onItemLongClicked(Thread item, int position)
    {

    }

    @Override
    public ThreadListAdapter getListAdapter()
    {
        return new ThreadListAdapter(this, getListObject().threads, this);
    }

    @Override
    public ThreadList getNewListObject()
    {
        return new ThreadList();
    }

}
