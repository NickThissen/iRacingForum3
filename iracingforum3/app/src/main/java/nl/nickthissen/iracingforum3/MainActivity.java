package nl.nickthissen.iracingforum3;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Trace;
import org.androidannotations.annotations.ViewById;

import nl.nickthissen.iracingforum3.network.Forum;
import nl.nickthissen.iracingforum3.network.NetworkCallback;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements NetworkCallback
{
    @ViewById
    TextView text;

    @AfterViews
    @Trace(level = Log.INFO, tag = "iRacingForum3")
    void afterViews()
    {
        Forum.getForumList(this);
    }

    @Override
    public void onResponse(String response)
    {
        text.setText(response);
    }

    @Override
    public void onError(VolleyError error)
    {
        text.setText("ERROR:" + error.getMessage());
    }
}
