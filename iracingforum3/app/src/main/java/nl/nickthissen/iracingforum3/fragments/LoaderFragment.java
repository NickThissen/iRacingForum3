package nl.nickthissen.iracingforum3.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Nick on 1/12/2015.
 */
public class LoaderFragment extends Fragment
{
    static interface LoadingCallbacks
    {

    }

    @Override
    public void onAttach(Activity activity)
    {

    }

    @Override
    public void onCreate(Bundle bundle)
    {
        // Retain this instance across config changes
        this.setRetainInstance(true);
    }
}
