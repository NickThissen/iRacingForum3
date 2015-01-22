package nl.nickthissen.iracingforum3.network;

import com.android.volley.VolleyError;

/**
 * Created by nthissen on 09/01/2015.
 */
public interface NetworkCallback
{
    void onResponse(String response);
    void onError(VolleyError error);
}
