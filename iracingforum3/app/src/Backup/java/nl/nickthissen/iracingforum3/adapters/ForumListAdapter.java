package nl.nickthissen.iracingforum3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.models.forum.Forum;

/**
 * Created by Nick on 1/11/2015.
 */
public class ForumListAdapter extends ListAdapter<Forum>
{
    private ArrayList<Forum> _items;

    public ForumListAdapter(Context context, ArrayList<Forum> items, ListItemClickListener listener)
    {
        super(context, R.layout.forum_row, items, listener);
        _items = items;
    }

    @Override public void processRow(View view, Forum item, int position)
    {
        TextView text = (TextView)view.findViewById(R.id.title);
        text.setText(item.title);
    }
}
