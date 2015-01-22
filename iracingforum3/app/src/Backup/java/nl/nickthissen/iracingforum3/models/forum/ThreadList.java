package nl.nickthissen.iracingforum3.models.forum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nick on 1/11/2015.
 */
public class ThreadList extends PageItemList implements Serializable
{
    public ThreadList()
    {
        this.threads = new ArrayList<>();
    }

    public ArrayList<Thread> threads;
    public boolean isWatching;
    public boolean canPost;
}
