package com.akitektuo.historyofweapons.util;

import android.graphics.drawable.Drawable;

/**
 * Created by AoD Akitektuo on 11-Dec-16.
 */

public class HistoryItem {

    private Drawable imageDrawable;
    private String title;
    private int type;

    public HistoryItem(Drawable drawable, String text, int type) {
        setImageDrawable(drawable);
        setTitle(text);
        setType(type);
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(Drawable imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
