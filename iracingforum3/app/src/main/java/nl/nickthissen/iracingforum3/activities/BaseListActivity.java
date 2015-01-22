package nl.nickthissen.iracingforum3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import nl.nickthissen.iracingforum3.Log;
import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.adapters.ListAdapter;
import nl.nickthissen.iracingforum3.fragments.NetworkFragment;
import nl.nickthissen.iracingforum3.network.ForumNetwork;

/**
 * Created by Nick on 1/19/2015.
 */
@EActivity
public abstract class BaseListActivity<TList, TItem> extends ActionBarActivity implements ListAdapter.ListItemClickListener<TItem>
{
    @ViewById(R.id.list)
    ListView _list;

    @ViewById(R.id.progress)
    ProgressBar _progress;

    private boolean _isLoading;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Log.info("BaseListActivity.onCreate");

        //_list = (ListView) this.findViewById(R.id.list);
        //_progress = (ProgressBar) this.findViewById(R.id.progress);

        this.createLoader();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.info("BaseListActivity.onStart");
        this.startLoader();
    }

    @AfterViews
    void onAfterViews()
    {
        Log.info("BaseListActivity.onAfterViews");
        //this.startLoader();
    }

    private void createLoader()
    {
        if (_loader == null)
        {
            Log.info("BaseListActivity -- creating loader");
            _loader = new NetworkFragment<>();
            this.getSupportFragmentManager().beginTransaction().add(_loader, "LOADER").commit();
        }
        else
        {
            Log.info("BaseListActivity -- loader already exists");
        }
    }

    private void startLoader()
    {
        Log.info("BaseListActivity -- starting loader");

        if (_loader == null)
        {
            Log.info("BaseListActivity -- loader is NULL");
            return;
        }

        if (_loader.isLoading())
        {
            Log.info("BaseListActivity -- loader is already loading, skipped");
            return;
        }
        _loader.setIsLoading(true);

        this.showProgress(true);
        this.startLoading();
    }

    private void showProgress(boolean visible)
    {
        if (_progress == null) return;
        if (visible)
            _progress.setVisibility(View.VISIBLE);
        else
            _progress.setVisibility(View.GONE);
    }

    private TList _listObject;
    public TList getListObject()
    {
        return _listObject;
    }

    public void finishedLoading(TList object)
    {
        Log.info("BaseListActivity -- finished loading");
        _loader.setIsLoading(false);
        this.showProgress(false);
        _listObject = object;
        this.listReady();
    }

    public void setListObject(TList object)
    {
        this.finishedLoading(object);
    }

    protected NetworkFragment<TList, TItem> _loader;
    public NetworkFragment<TList, TItem> getLoader()
    {
        return _loader;
    }

    private void listReady()
    {
        ListAdapter<TItem> adapter = this.getListAdapter();
        this.setListAdapter(adapter);
        this.onListReady(_listObject);
    }

    protected void setListAdapter(ListAdapter<TItem> adapter)
    {
        _list.setAdapter(adapter);
    }

    protected <T> T getExtra(Bundle bundle, String key)
    {
        if (bundle != null && bundle.containsKey(key))
        {
            Object obj = bundle.getSerializable(key);
            try
            {
                return (T)obj;
            }
            catch (ClassCastException ex)
            {
            }
        }
        return null;
    }

    protected <T> T getIntentExtra(String key)
    {
        Intent intent = getIntent();
        if (intent != null)
        {
            return getExtra(intent.getExtras(), key);
        }
        return null;
    }

    public abstract void onListReady(TList list);
    public abstract TList getNewListObject();
    public abstract ListAdapter<TItem> getListAdapter();
    public abstract void startLoading();
}
