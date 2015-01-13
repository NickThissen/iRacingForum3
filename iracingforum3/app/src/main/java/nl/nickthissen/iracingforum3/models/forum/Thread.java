package nl.nickthissen.iracingforum3.models.forum;

import java.io.Serializable;

/**
 * Created by Nick on 1/11/2015.
 */
public class Thread implements Serializable
{
    public Thread(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public int id;
    public int forumId;

    public String url;
    public String lastPageUrl;
    public int pageCount;

    public String author;
    public String title;
    public String authorUrl;
    public int replies;
    public int views;
    public long lastMessageTime;
    public String lastMessageAuthor;
    public String lastMessageAuthorUrl;
    public String latestReplyUrl;
    public String iconName;
    public boolean isPoll;
    public boolean hasAttachments;
    public boolean isWatching;

}
