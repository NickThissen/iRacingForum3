package nl.nickthissen.iracingforum3.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.models.forum.Post;
import nl.nickthissen.iracingforum3.models.forum.Thread;

/**
 * Created by Nick on 1/11/2015.
 */
public class PostListAdapter extends ListAdapter<Post>
{
    private ArrayList<Post> _items;

    public PostListAdapter(Context context, ArrayList<Post> items, ListItemClickListener listener)
    {
        super(context, R.layout.post_row, items, listener);
        _items = items;
    }

    @Override public void processRow(View view, Post item, int position)
    {
        TextView text = (TextView)view.findViewById(R.id.title);
        text.setText(item.contents);
    }
}
