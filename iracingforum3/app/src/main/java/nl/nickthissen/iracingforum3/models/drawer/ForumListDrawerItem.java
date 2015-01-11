package nl.nickthissen.iracingforum3.models.drawer;

import nl.nickthissen.iracingforum3.fragments.ForumListFragment;
import nl.nickthissen.iracingforum3.models.forum.ForumList;

/**
 * Represents the single item in the navigation drawer that links to the Forum List.
 */
public class ForumListDrawerItem extends DrawerItem
{
    public ForumListDrawerItem(ForumList forumList, ForumListFragment fragment)
    {
        super("Forum list", DrawerItemTypes.ForumList);
        this.forumList = forumList;

        _fragment = fragment;
        _fragment.setDrawerItem(this);
    }

    public ForumList forumList;
}
