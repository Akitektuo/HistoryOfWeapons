package com.akitektuo.historyofweapons.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.akitektuo.historyofweapons.R;

import static com.akitektuo.historyofweapons.util.Constants.FILTER_BOMBS;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_GUNS;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_NAVY;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_PLANES;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_TANKS;

/**
 * Created by AoD Akitektuo on 11-Dec-16.
 */

public class HistoryAdapter extends ArrayAdapter<HistoryItem> {

    private Context context;

    private  HistoryItem[] items;

    public HistoryAdapter(Context context, HistoryItem[] objects) {
        super(context, R.layout.item_history, objects);
        this.context = context;
        items = objects;
    }

    private class MyViewHolder {
        ImageView imageTitle;
        TextView textTitle;
        ImageView imageType;
        MyViewHolder(View view) {
            imageTitle = (ImageView) view.findViewById(R.id.image_weapon);
            textTitle = (TextView) view.findViewById(R.id.text_weapon_title);
            imageType = (ImageView) view.findViewById(R.id.image_item_type);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        MyViewHolder holder;
        if (view == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_history, parent, false);
            holder = new MyViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        HistoryItem item = items[position];
        // TODO: 11-Dec-16 set image to imageTitle
        holder.imageTitle.setImageDrawable(item.getImageDrawable());
        holder.textTitle.setText(item.getTitle());
        Methods.setType(context, holder.imageType, item.getType());
        return view;
    }
}
