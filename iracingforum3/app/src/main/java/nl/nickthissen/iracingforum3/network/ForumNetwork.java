package nl.nickthissen.iracingforum3.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

import nl.nickthissen.iracingforum3.models.forum.ThreadList;
import nl.nickthissen.iracingforum3.models.forum.Thread;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.parsing.ParseResult;
import nl.nickthissen.iracingforum3.parsing.Parser;
import nl.nickthissen.iracingforum3.parsing.ThreadListParser;

/**
 * Created by nthissen on 09/01/2015.
 */
@EBean
public class ForumNetwork extends Network
{
    private static final String LOGIN_URL = "https://members.iracing.com/jforum/Login";
    private static final String FORUM_LIST = "http://members.iracing.com/jforum/forums/list.page";

    public ForumNetwork(Context context)
    {
        super(context);
    }

    public static void init(Context context)
    {
        _instance = ForumNetwork_.getInstance_(context);
    }

    private static ForumNetwork_ _instance;
    public static synchronized ForumNetwork_ getInstance()
    {
        if (_instance == null) throw new RuntimeException("Networking is not initialized.");
        return _instance;
    }

    public void login(final ForumCallback callback)
    {

    }

    public void getForumList(final ForumCallback callback)
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
        this.addRequest(req);
    }

    public void getThreadList(Forum forum, final ForumCallback<ParseResult<ThreadList>> callback)
    {
        getThreadListBackground(forum, callback);
    }

    @Background(delay=4000)
    void getThreadListBackground(Forum forum, final ForumCallback<ParseResult<ThreadList>> callback)
    {
        Parser parser = new ThreadListParser();
        ParseResult result = parser.parse("");
        getThreadListFinished(forum, result, callback);
    }

    @UiThread
    void getThreadListFinished(Forum forum, ParseResult<ThreadList> result, final ForumCallback<ParseResult<ThreadList>> callback)
    {
        callback.onResponse(result);
    }
}
