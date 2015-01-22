package nl.nickthissen.iracingforum3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.R;

/**
 * Created by Nick on 1/11/2015.
 */
public abstract class ListAdapter<T> extends ArrayAdapter<T>
{
    protected ArrayList<T> items;
    protected int resourceId;
    protected final ListItemClickListener<T> clickListener;

    public ListAdapter(Context context, int resourceId, ArrayList<T> items, ListItemClickListener<T> listener)
    {
        super(context, resourceId, items);
        this.items = items;
        this.resourceId = resourceId;
        this.clickListener = listener;
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(resourceId, null);
        }

        final T item = items.get(position);
        if (item != null)
        {
            v.setClickable(true);
            v.setOnCreateContextMenuListener(null);
            v.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View v)
                {
                    clickListener.onItemClicked(item, position);
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override public boolean onLongClick(View v)
                {
                    clickListener.onItemLongClicked(item, position);
                    return true;
                }
            });

            this.processRow(v, item, position);
        }
        return v;
    }

    public abstract void processRow(View view, T item, int position);

    public interface ListItemClickListener<T>
    {
        public void onItemClicked(T item, int position);
        public void onItemLongClicked(T item, int position);
    }
}
