package nl.nickthissen.iracingforum3.models.forum;

import java.io.Serializable;

/**
 * Created by Nick on 1/11/2015.
 */
public class Post implements Serializable
{
    public int id;
    public String url;

    public String author;
    public String authorUrl;
    public boolean authorOnline;
    public boolean authorDonated;
    public boolean authorIsStaff;

    public String contents;
    public String plainContents;

    public long createdTime;

    public String quoteUrl;
    public String editUrl;
    public boolean canEdit;

    public boolean isIgnored;
    public String ignoredText;
}
