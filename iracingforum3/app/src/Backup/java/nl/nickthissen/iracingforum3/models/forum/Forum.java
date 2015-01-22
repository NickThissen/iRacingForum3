package nl.nickthissen.iracingforum3.models.forum;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Nick on 1/11/2015.
 */
public class Forum implements Serializable
{
    public Forum(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public int id;
    public boolean isCategoryHeader;
    public String url;
    public String title;
    public String category;

    public int topics;
    public int posts;

    public String lastMessageTime;
    public String lastMessageUrl;
    public String lastMessageAuthor;
    public String lastMessageAuthorUrl;
    public String iconName;

    public boolean isWatching;
}
