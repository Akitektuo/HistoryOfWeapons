package com.akitektuo.historyofweapons.util;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.widget.ImageView;

import com.akitektuo.historyofweapons.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import static com.akitektuo.historyofweapons.util.Constants.FILTER_BOMBS;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_GUNS;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_NAVY;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_PLANES;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_TANKS;
import static com.akitektuo.historyofweapons.util.Constants.KEY_LANGUAGE;
import static com.akitektuo.historyofweapons.util.Constants.LANGUAGE_EN;
import static com.akitektuo.historyofweapons.util.Constants.preferences;

/**
 * Created by Akitektuo on 28.12.2016.
 */

public class Methods {

    public static void setType(Context context, ImageView imageView, int type) {
        switch (type) {
            case FILTER_GUNS:
                imageView.setBackground(context.getDrawable(R.drawable.gun_00));
                break;
            case FILTER_BOMBS:
                imageView.setBackground(context.getDrawable(R.drawable.bomb_00));
                break;
            case FILTER_TANKS:
                imageView.setBackground(context.getDrawable(R.drawable.tank_00));
                break;
            case FILTER_NAVY:
                imageView.setBackground(context.getDrawable(R.drawable.navy_00));
                break;
            case FILTER_PLANES:
                imageView.setBackground(context.getDrawable(R.drawable.plane_00));
                break;
        }
    }

    public static void changeLanguage(Resources resources, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    public static String readFromFile(Context context, String file) {
        try {
            InputStream inputStream = context.getAssets().open(file);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
