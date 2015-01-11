package nl.nickthissen.iracingforum3.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.models.forum.Thread;

/**
 * Created by Nick on 1/11/2015.
 */
public class ThreadListAdapter extends ListAdapter<Thread>
{
    private ArrayList<Thread> _items;

    public ThreadListAdapter(Context context, ArrayList<Thread> items, ListAdapter.ListItemClickListener listener)
    {
        super(context, R.layout.thread_row, items, listener);
        _items = items;
    }

    @Override public void processRow(View view, Thread item, int position)
    {
        TextView text = (TextView)view.findViewById(R.id.title);
        text.setText(item.title);
    }
}
