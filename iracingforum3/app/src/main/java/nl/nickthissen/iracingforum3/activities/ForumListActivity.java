package nl.nickthissen.iracingforum3.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.androidannotations.annotations.EActivity;

import nl.nickthissen.iracingforum3.Log;
import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.adapters.ForumListAdapter;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.ForumList;

/**
 * Created by Nick on 1/16/2015.
 */
@EActivity(R.layout.forumlist)
public class ForumListActivity extends BaseListActivity<ForumList, Forum>
{
    private static final String KEY_FORUMLIST = "KEY_FORUMLIST";

    private ForumList _forumList;

    public static Intent getIntent(Context context, ForumList forumList)
    {
        Intent intent = ForumListActivity_.intent(context).get();
        intent.putExtra(KEY_FORUMLIST, forumList);
        return intent;
    }

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Log.info("ForumListActivity.onCreate");

        _forumList = this.getIntentExtra(KEY_FORUMLIST);
    }

    @Override
    public void startLoading()
    {
        Log.info("ForumListActivity.startLoading");
        this.setListObject(_forumList);
    }

    @Override
    public void onListReady(ForumList list)
    {
        Log.info("ForumListActivity.onListReady");
    }

    @Override
    public void onItemClicked(Forum item, int position)
    {
        this.startActivity(ThreadListActivity.getIntent(this, item));
    }

    @Override
    public void onItemLongClicked(Forum item, int position)
    {

    }

    @Override
    public ForumListAdapter getListAdapter()
    {
        return new ForumListAdapter(this, getListObject().forums, this);
    }

    @Override
    public ForumList getNewListObject()
    {
        return new ForumList();
    }
}
