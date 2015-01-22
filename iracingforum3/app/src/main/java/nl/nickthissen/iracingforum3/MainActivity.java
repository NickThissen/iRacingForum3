package nl.nickthissen.iracingforum3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.android.volley.VolleyError;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import nl.nickthissen.iracingforum3.activities.ForumListActivity;
import nl.nickthissen.iracingforum3.adapters.ListAdapter;
import nl.nickthissen.iracingforum3.models.forum.Forum;
import nl.nickthissen.iracingforum3.models.forum.ForumList;
import nl.nickthissen.iracingforum3.models.forum.ThreadList;
import nl.nickthissen.iracingforum3.network.ForumCallback;
import nl.nickthissen.iracingforum3.parsing.ParseResult;

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity implements ForumCallback<ParseResult<ForumList>>
{
    @Override protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
    }

    @AfterViews
    void afterViews()
    {
    }

    @Click(R.id.forumListButton)
    void onClickForumListButton()
    {
        this.startActivity(ForumListActivity.getIntent(this, getForums()));
    }

    private ForumList getForums()
    {
        // TODO: load before
        ForumList forumList = new ForumList();
        forumList.forums.add(new Forum(0, "Forum A"));
        forumList.forums.add(new Forum(1, "Forum B"));
        forumList.forums.add(new Forum(2, "Forum C"));
        forumList.forums.add(new Forum(3, "Forum D"));
        forumList.forums.add(new Forum(4, "Forum E"));
        forumList.forums.add(new Forum(5, "Forum F"));

        return forumList;
    }

    @Override
    public void onResponse(ParseResult<ForumList> response)
    {

    }

    @Override
    public void onError(VolleyError error)
    {

    }
}
