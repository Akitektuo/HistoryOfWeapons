package com.akitektuo.historyofweapons.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.akitektuo.historyofweapons.R;
import com.akitektuo.historyofweapons.database.DatabaseHelper;
import com.akitektuo.historyofweapons.util.HistoryAdapter;
import com.akitektuo.historyofweapons.util.HistoryItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

public class HistoryActivity extends Activity implements View.OnClickListener {

    private List<Integer> selectedItems;
    private HistoryItem[] historyItems;
    private ListView listHistory;
    private DatabaseHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histrory);
        findViewById(R.id.button_back_history).setOnClickListener(this);
        findViewById(R.id.button_filter).setOnClickListener(this);
        listHistory = findViewById(R.id.list_history_item);
        database = new DatabaseHelper(this);
        buildList();
        listHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplication(), HistoryDetailActivity.class);
                intent.putExtra(KEY_TITLE, historyItems[i].getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back_history:
                finish();
                break;
            case R.id.button_filter:
                selectedItems = new ArrayList<>();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.title_filter).setMultiChoiceItems(getResources().getStringArray(R.array.filter), null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            selectedItems.add(i);
                        } else if (selectedItems.contains(i)) {
                            selectedItems.remove(Integer.valueOf(i));
                        }
                    }
                });
                builder.setPositiveButton(R.string.filter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        List<Integer> id = new ArrayList<>();
                        int items = 0;
                        for (int type : selectedItems) {
                            Cursor cursor = database.getWeaponForType(database.getReadableDatabase(), type);
                            if (cursor.moveToFirst()) {
                                do {
                                    id.add(cursor.getInt(0));
                                    items++;
                                } while (cursor.moveToNext());
                            }
                        }
                        historyItems = new HistoryItem[items];
                        for (int j = 0; j < items; j++) {
                            Cursor cursor = database.getWeaponForId(database.getReadableDatabase(), id.get(j));
                            if (cursor.moveToFirst()) {
                                Drawable drawable = getResources().getDrawable(getResources().getIdentifier(cursor.getString(1), "drawable", getPackageName()));
                                historyItems[j] = new HistoryItem(drawable, cursor.getString(4), cursor.getInt(5));
                            }
                        }
                        ArrayAdapter<HistoryItem> historyArrayAdapter = new HistoryAdapter(getBaseContext(), historyItems);
                        listHistory.setAdapter(historyArrayAdapter);
                    }
                }).setNegativeButton(R.string.cancel, null).create().show();
                break;
        }
    }

    private void buildList() {
        historyItems = new HistoryItem[database.getWeaponId() + 1];
        Cursor cursor = database.getWeaponAlphabetically(database.getReadableDatabase());
        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                Drawable drawable = getResources().getDrawable(getResources().getIdentifier(cursor.getString(1), "drawable", getPackageName()));
                historyItems[i] = new HistoryItem(drawable, cursor.getString(4), cursor.getInt(5));
                i++;
            } while (cursor.moveToNext());
        }
        ArrayAdapter<HistoryItem> historyArrayAdapter = new HistoryAdapter(this, historyItems);
        listHistory.setAdapter(historyArrayAdapter);
    }
}
