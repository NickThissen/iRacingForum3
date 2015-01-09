package nl.nickthissen.iracingforum3.network;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by nthissen on 09/01/2015.
 */
public class Forum
{
    private static final String LOGIN_URL = "https://members.iracing.com/jforum/Login";
    private static final String FORUM_LIST = "http://members.iracing.com/jforum/forums/list.page";

    public static void login(final NetworkCallback callback)
    {

    }

    public static void getForumList(final NetworkCallback callback)
    {
        StringRequest req = new StringRequest(Request.Method.GET, FORUM_LIST,
                new Response.Listener()
                {
                    @Override
                    public void onResponse(Object response)
                    {
                        callback.onResponse(response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        callback.onError(error);
                    }
                });
        Network.getInstance().addRequest(req);
    }
}
