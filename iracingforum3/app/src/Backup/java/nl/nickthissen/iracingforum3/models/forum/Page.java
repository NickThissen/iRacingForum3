package nl.nickthissen.iracingforum3.models.forum;

import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 1/11/2015.
 */
public class Page
{
    private static final int PagesPerThread = 25;
    private static final int PagesPerForum = 50;

    public Page(int nr, int total)
    {
        this.number = nr;
        this.total = total;
    }

    public int number;
    public int total;

    public String getThreadUrl(String currentUrl)
    {
        return getUrl(currentUrl, PagesPerThread);
    }

    public String getForumUrl(String currentUrl)
    {
        return getUrl(currentUrl, PagesPerForum);
    }

    public String getUrl(String currentUrl, int jump)
    {
        Uri uri = Uri.parse(currentUrl);
        List<String> parts = uri.getPathSegments();

        if (parts.size() == 4 || parts.size() == 5)
        {
            ArrayList<String> fixedParts = new ArrayList<>();
            fixedParts.add(parts.get(0)); //jforum
            fixedParts.add(parts.get(1)); //forums
            fixedParts.add(parts.get(2)); //show

            int desiredPosts = jump * (this.number - 1);
            if (desiredPosts > 0)
            {
                fixedParts.add(Integer.toString(desiredPosts));
            }
            fixedParts.add(parts.get(parts.size() - 1)); //forumid.page

            Uri.Builder builder = uri.buildUpon();
            builder.path(TextUtils.join("/", fixedParts));
            return "/" + builder.build().toString();
        }

        return currentUrl;
    }

    public static ArrayList<Page> fromList(PageItemList itemlist)
    {
        ArrayList<Page> items = new ArrayList<>();
        if (!itemlist.hasPagination)
        {
            Page page = new Page(1, 1);
            items.add(page);
        }
        else
        {
            for (int i = 1; i <= itemlist.lastPage; i++)
            {
                Page page = new Page(i, itemlist.lastPage);
                items.add(page);
            }
        }
        return items;
    }
}
