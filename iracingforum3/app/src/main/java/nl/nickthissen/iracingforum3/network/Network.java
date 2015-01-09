package nl.nickthissen.iracingforum3.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by nthissen on 09/01/2015.
 */
public class Network
{
    private Context _context;

    private Network(Context context)
    {
        _context = context;
        _queue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static void init(Context context)
    {
        _instance = new Network(context);
    }

    private static Network _instance;
    public static synchronized Network getInstance()
    {
        if (_instance == null) throw new RuntimeException("Networking is not initialized.");
        return _instance;
    }

    private RequestQueue _queue;

    public void addRequest(Request req)
    {
        _queue.add(req);
    }
}
