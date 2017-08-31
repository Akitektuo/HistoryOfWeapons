package com.akitektuo.historyofweapons.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akitektuo.historyofweapons.R;
import com.akitektuo.historyofweapons.database.DatabaseHelper;
import com.akitektuo.historyofweapons.util.Methods;

import java.util.Locale;

import static com.akitektuo.historyofweapons.util.Constants.KEY_LANGUAGE;
import static com.akitektuo.historyofweapons.util.Constants.LANGUAGE_EN;
import static com.akitektuo.historyofweapons.util.Constants.LANGUAGE_RO;
import static com.akitektuo.historyofweapons.util.Constants.preferences;

public class MenuActivity extends Activity implements View.OnClickListener {
    private TextView textTitle;
    private TextView textQuiz;
    private TextView textHistory;
    private TextView textShop;
    private TextView textLanguage;
    private TextView textContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        textTitle = (TextView) findViewById(R.id.text_menu_title);
        textQuiz = (TextView) findViewById(R.id.text_menu_quiz);
        textHistory = (TextView) findViewById(R.id.text_menu_history);
        textShop = (TextView) findViewById(R.id.text_menu_shop);
        textLanguage = (TextView) findViewById(R.id.text_menu_language);
        textContact = (TextView) findViewById(R.id.text_menu_contact);
        LinearLayout layoutLanguage = (LinearLayout) findViewById(R.id.layout_language);
        layoutLanguage.setOnClickListener(this);
        LinearLayout layoutQuiz = (LinearLayout) findViewById(R.id.layout_quiz);
        layoutQuiz.setOnClickListener(this);
        LinearLayout layoutHistory = (LinearLayout) findViewById(R.id.layout_history);
        layoutHistory.setOnClickListener(this);
        LinearLayout layoutShop = (LinearLayout) findViewById(R.id.layout_shop);
        layoutShop.setOnClickListener(this);
    }

    private void refreshMenu() {
        textTitle.setText(R.string.history_of_weapons);
        textQuiz.setText(R.string.quiz);
        textHistory.setText(R.string.history);
        textShop.setText(R.string.shop);
        textLanguage.setText(R.string.language);
        textContact.setText(R.string.contact);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_language:
                AlertDialog.Builder builderLanguage = new AlertDialog.Builder(this);
                builderLanguage.setTitle(R.string.select_language);
                builderLanguage.setMessage(R.string.select_language_message);
                if (preferences.getPreferenceString(KEY_LANGUAGE).equals(LANGUAGE_EN)) {
                    builderLanguage.setPositiveButton(R.string.romanian, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Methods.changeLanguage(getResources(), LANGUAGE_RO);
                            refreshMenu();
                            preferences.setPreference(KEY_LANGUAGE, LANGUAGE_RO);
                        }
                    });
                } else {
                    builderLanguage.setPositiveButton(R.string.english, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Methods.changeLanguage(getResources(), LANGUAGE_EN);
                            refreshMenu();
                            preferences.setPreference(KEY_LANGUAGE, LANGUAGE_EN);
                        }
                    });
                }
                builderLanguage.setNegativeButton(R.string.cancel, null);
                AlertDialog dialogLanguage = builderLanguage.create();
                dialogLanguage.show();
                break;
            case R.id.layout_quiz:
                startActivity(new Intent(this, QuizActivity.class));
                break;
            case R.id.layout_history:
                startActivity(new Intent(this, HistoryActivity.class));
                break;
            case R.id.layout_shop:
                startActivity(new Intent(this, ShopActivity.class));
                break;
        }
    }
}
