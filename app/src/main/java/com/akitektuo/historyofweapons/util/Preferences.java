package com.akitektuo.historyofweapons.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.akitektuo.historyofweapons.util.Constants.APP_VERSION;
import static com.akitektuo.historyofweapons.util.Constants.KEY_APP_VERSION;
import static com.akitektuo.historyofweapons.util.Constants.KEY_BALANCE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_DATABASE_CREATED;
import static com.akitektuo.historyofweapons.util.Constants.KEY_EXCLUDE_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_HINT_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_INITIALIZE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_LANGUAGE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_QUESTION;
import static com.akitektuo.historyofweapons.util.Constants.KEY_SHOW_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_SKIP_NR;
import static com.akitektuo.historyofweapons.util.Constants.LANGUAGE_EN;
import static com.akitektuo.historyofweapons.util.Constants.preferences;

/**
 * Created by Akitektuo on 21.12.2016.
 */

public class Preferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public Preferences(Context context) {
        this.context = context;
        initialization();
    }

    private void initialization() {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KEY_INITIALIZE, MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    private void savedPreferences(){
        editor.commit();
    }

    public void setPreference(String key, boolean bool) {
        // TODO: 21.12.2016 initialize if crash
        editor.putBoolean(key, bool);
        savedPreferences();
    }

    public void setPreference(String key, int num) {
        // TODO: 21.12.2016 initialize if crash
        editor.putInt(key, num);
        savedPreferences();
    }

    public void setPreference(String key, String str) {
        // TODO: 21.12.2016 initialize if crash
        editor.putString(key, str);
        savedPreferences();
    }

    public void setDefaultPreferences(String language) {
        setPreference(KEY_DATABASE_CREATED, true);
        setPreference(KEY_LANGUAGE, language);
        setPreference(KEY_BALANCE, 0);
        setPreference(KEY_SKIP_NR, 0);
        setPreference(KEY_HINT_NR, 0);
        setPreference(KEY_EXCLUDE_NR, 0);
        setPreference(KEY_SHOW_NR, 0);
        setPreference(KEY_QUESTION, 0);
        setPreference(KEY_APP_VERSION, APP_VERSION);
    }

    public boolean getPreferenceBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public int getPreferenceInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public String getPreferenceString(String key) {
        return sharedPreferences.getString(key, null);
    }
}
