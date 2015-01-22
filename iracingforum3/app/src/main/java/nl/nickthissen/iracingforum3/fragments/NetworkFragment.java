package nl.nickthissen.iracingforum3.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.volley.VolleyError;

import nl.nickthissen.iracingforum3.Log;
import nl.nickthissen.iracingforum3.activities.BaseListActivity;
import nl.nickthissen.iracingforum3.network.ForumCallback;
import nl.nickthissen.iracingforum3.parsing.ParseResult;

/**
 * Created by Nick on 1/19/2015.
 */
public class NetworkFragment<TList, TItem> extends Fragment implements ForumCallback<ParseResult<TList>>
{
    private BaseListActivity<TList, TItem> _activity;
    private boolean _isLoading;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        this.setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        Log.info("NetworkFragment -- attaching activity.");
        _activity = (BaseListActivity<TList, TItem>) activity;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.info("NetworkFragment -- detaching activity.");
        _activity = null;
    }

    @Override
    public void onResponse(ParseResult<TList> response)
    {
        Log.info("NetworkFragment -- response success: " + response.success());
        if (_activity == null)
        {
            Log.info("NetworkFragment -- response ignored, no activity set.");
            return;
        }

        TList list;
        if (response.success())
        {
            list = response.result();
        }
        else
        {
            list = _activity.getNewListObject();
        }
        _activity.finishedLoading(list);
    }

    @Override
    public void onError(VolleyError error)
    {
        if (_activity == null) return;
    }

    public boolean isLoading()
    {
        return _isLoading;
    }

    public void setIsLoading(boolean isLoading)
    {
        this._isLoading = isLoading;
    }
}
