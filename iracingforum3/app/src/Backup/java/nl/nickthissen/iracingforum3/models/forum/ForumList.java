package nl.nickthissen.iracingforum3.models.forum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nick on 1/11/2015.
 */
public class ForumList extends PageItemList  implements Serializable
{
    public ForumList()
    {
        this.forums = new ArrayList<>();
    }

    public ArrayList<Forum> forums;
    public int pmCount;
}
