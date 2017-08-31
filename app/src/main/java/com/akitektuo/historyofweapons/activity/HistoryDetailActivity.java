package com.akitektuo.historyofweapons.activity;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.akitektuo.historyofweapons.R;
import com.akitektuo.historyofweapons.database.DatabaseHelper;
import com.akitektuo.historyofweapons.util.Methods;

import java.io.IOException;
import java.io.InputStream;

import static com.akitektuo.historyofweapons.util.Constants.FILTER_BOMBS;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_GUNS;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_NAVY;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_PLANES;
import static com.akitektuo.historyofweapons.util.Constants.FILTER_TANKS;
import static com.akitektuo.historyofweapons.util.Constants.KEY_LANGUAGE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_TITLE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_TYPE;
import static com.akitektuo.historyofweapons.util.Constants.LANGUAGE_EN;
import static com.akitektuo.historyofweapons.util.Constants.preferences;

public class HistoryDetailActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);
        findViewById(R.id.button_back_history_detail).setOnClickListener(this);
        TextView textTitle = (TextView) findViewById(R.id.text_detail_title);
        ImageView imageType = (ImageView) findViewById(R.id.image_detail_type);
        ImageView imageWeapon = (ImageView) findViewById(R.id.image_detail_weapon);
        TextView textLecture = (TextView) findViewById(R.id.text_detail_lecture);
        DatabaseHelper database = new DatabaseHelper(this);
        if (getIntent() != null && getIntent().getExtras() != null) {
            Cursor cursor = database.getWeaponForName(database.getReadableDatabase(), getIntent().getStringExtra(KEY_TITLE));
            if (cursor.moveToFirst()) {
                imageWeapon.setImageDrawable(getDrawable(getResources().getIdentifier(cursor.getString(1), "drawable", getPackageName())));
                if (preferences.getPreferenceString(KEY_LANGUAGE).equals(LANGUAGE_EN)) {
                    textLecture.setText(cursor.getString(2));
                } else {
                    textLecture.setText(cursor.getString(3));
                }
                textTitle.setText(cursor.getString(4));
                Methods.setType(this, imageType, cursor.getInt(5));
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_back_history_detail) {
            finish();
        }
    }
}
