package nl.nickthissen.iracingforum3.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
    private Drawable _selectionBackground;

    public DrawerAdapter(Context context, ArrayList<DrawerItem> items, OnItemClickListener listener)
    {
        super(context, 0, items);
        _items = items;
        _listener = listener;

        _selectionBackground = context.getResources().getDrawable(android.R.drawable.list_selector_background);
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

        // Get views
        LinearLayout background = (LinearLayout) view.findViewById(R.id.background);
        TextView text = (TextView)view.findViewById(R.id.text);

        // Bind view properties
        text.setText(item.title);

        if (item.isSelected)
        {
            background.setBackground(_selectionBackground);
        }

        // Set click handlers
        if (item.canClick())
        {
            text.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    _listener.onClick(view, position);
                }
            });
        }

        View btnView = view.findViewById(R.id.closeButton);
        if (item.canClose())
        {
            Button btn = (Button) btnView;
            btn.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View view) {
                    _listener.onCloseClick(view, position);
                }
            });
        }
        else
        {
            if (btnView != null) btnView.setVisibility(View.INVISIBLE);
        }

        return v;
    }

    public interface OnItemClickListener {
        public void onClick(View view, int position);
        public void onCloseClick(View view, int position);
    }
}
