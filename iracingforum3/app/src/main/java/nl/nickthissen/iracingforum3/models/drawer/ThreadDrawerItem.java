package nl.nickthissen.iracingforum3.models.drawer;

import nl.nickthissen.iracingforum3.fragments.PostListFragment;
import nl.nickthissen.iracingforum3.models.forum.Thread;

/**
 * Represents an item in the navigation drawer that links to a thread (post list).
 */
public class ThreadDrawerItem extends DrawerItem
{
    public ThreadDrawerItem(Thread thread, PostListFragment fragment)
    {
        super(thread.title, DrawerItemTypes.Thread);
        this.thread = thread;

        _fragment = fragment;
        _fragment.setDrawerItem(this);
    }

    public Thread thread;
}
