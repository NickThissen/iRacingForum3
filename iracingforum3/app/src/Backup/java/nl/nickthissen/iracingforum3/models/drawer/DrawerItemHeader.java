package nl.nickthissen.iracingforum3.models.drawer;

import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;

/**
 * Created by Nick on 1/11/2015.
 */
public class DrawerItemHeader extends DrawerItem
{
    public DrawerItemHeader(String title)
    {
        super(title, DrawerItemTypes.Header);
    }
}
