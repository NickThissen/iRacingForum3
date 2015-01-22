package nl.nickthissen.iracingforum3.models.forum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nick on 1/11/2015.
 */
public class PostList extends PageItemList implements Serializable
{
    public PostList()
    {
        this.posts = new ArrayList<>();
    }

    public ArrayList<Post> posts;

    public int threadId;
    public String threadTitle;

    public int forumId;
    public String forumTitle;
    public String forumUrl;

    public String pollHtml;
    public boolean canReply;

    public boolean isWatching;
}
