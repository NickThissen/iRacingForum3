package nl.nickthissen.iracingforum3.models.drawer;

import nl.nickthissen.iracingforum3.fragments.ThreadListFragment;
import nl.nickthissen.iracingforum3.models.forum.Forum;

/**
 * Represents an item in the navigation drawer that links to a forum (thread list).
 */
public class ForumDrawerItem extends DrawerItem
{
    public ForumDrawerItem(Forum forum, ThreadListFragment fragment)
    {
        super(forum.title, DrawerItemTypes.Forum);
        this.forum = forum;

        _fragment = fragment;
        _fragment.setDrawerItem(this);
    }

    public Forum forum;
}
