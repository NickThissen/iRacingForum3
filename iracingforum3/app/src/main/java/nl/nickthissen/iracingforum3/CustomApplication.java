package nl.nickthissen.iracingforum3;

import android.app.Application;

import nl.nickthissen.iracingforum3.network.Network;

/**
 * Created by nthissen on 09/01/2015.
 */
public class CustomApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();

        Network.init(this.getApplicationContext());
    }
}
