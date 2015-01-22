package nl.nickthissen.iracingforum3.models.forum;

/**
 * Created by Nick on 1/11/2015.
 */
public abstract class PageItemList
{
    public boolean hasPagination;
    public int page;
    public int lastPage;
    public String prevPageUrl;
    public String nextPageUrl;
    public String threadUrl;
    public String threadLastPageUrl;
}
