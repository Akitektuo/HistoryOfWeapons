package com.akitektuo.historyofweapons.database;

import android.provider.BaseColumns;

/**
 * Created by AoD Akitektuo on 26-Jul-16.
 */
public class SettingsContract {

    public abstract class SettingsContractEntry implements BaseColumns {
        public static final String TABLE_NAME = "settings";
        public static final String COLUMN_NAME_START = "first_start";
        public static final String COLUMN_NAME_LANGUAGE = "language";
    }

}
