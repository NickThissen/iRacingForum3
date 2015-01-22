package nl.nickthissen.iracingforum3.network;

import com.android.volley.VolleyError;

/**
 * Created by nthissen on 09/01/2015.
 */
public interface ForumCallback<T>
{
    void onResponse(T response);
    void onError(VolleyError error);
}
