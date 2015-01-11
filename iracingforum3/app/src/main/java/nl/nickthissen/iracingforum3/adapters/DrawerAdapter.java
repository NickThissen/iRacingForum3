package nl.nickthissen.iracingforum3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import nl.nickthissen.iracingforum3.R;
import nl.nickthissen.iracingforum3.models.drawer.DrawerItem;

/**
 * Created by Nick on 1/11/2015.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItem>
{
    private ArrayList<DrawerItem> _items;
    private OnItemClickListener _listener;

    public DrawerAdapter(Context context, ArrayList<DrawerItem> items, OnItemClickListener listener)
    {
        super(context, 0, items);
        _items = items;
        _listener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        // Get drawer item
        DrawerItem item = _items.get(position);
        int layout = item.isHeader() ? R.layout.drawer_item_header : R.layout.drawer_item;

        // Create view
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(layout, null);
        }
        final View view = v;

        // Get text/button
        TextView text = (TextView)view.findViewById(R.id.text);

        text.setText(item.title);

        if (!item.isHeader())
        {
            text.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View view) {
                    _listener.onClick(view, position);
                }
            });

            Button btn = (Button) view.findViewById(R.id.closeButton);

            btn.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View view) {
                    _listener.onCloseClick(view, position);
                }
            });
        }

        return v;
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
        public void onCloseClick(View view, int position);
    }
}
