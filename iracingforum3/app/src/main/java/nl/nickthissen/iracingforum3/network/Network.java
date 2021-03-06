package nl.nickthissen.iracingforum3.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by nthissen on 09/01/2015.
 */
public abstract class Network
{
    private RequestQueue _queue;

    protected Network(Context context)
    {
        _context = context;
        _queue = Volley.newRequestQueue(context.getApplicationContext());
    }

    private Context _context;
    public Context getContext() {return _context;}


    public void addRequest(Request req)
    {
        _queue.add(req);
    }
}
