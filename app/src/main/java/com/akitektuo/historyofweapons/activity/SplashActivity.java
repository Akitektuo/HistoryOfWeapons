package com.akitektuo.historyofweapons.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akitektuo.historyofweapons.R;
import com.akitektuo.historyofweapons.database.DatabaseHelper;
import com.akitektuo.historyofweapons.util.Methods;
import com.akitektuo.historyofweapons.util.Preferences;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.akitektuo.historyofweapons.util.Constants.APP_VERSION;
import static com.akitektuo.historyofweapons.util.Constants.FILE_QUESTION;
import static com.akitektuo.historyofweapons.util.Constants.FILE_WEAPON;
import static com.akitektuo.historyofweapons.util.Constants.KEY_APP_VERSION;
import static com.akitektuo.historyofweapons.util.Constants.KEY_DATABASE_CREATED;
import static com.akitektuo.historyofweapons.util.Constants.KEY_LANGUAGE;
import static com.akitektuo.historyofweapons.util.Constants.LANGUAGE_EN;
import static com.akitektuo.historyofweapons.util.Constants.preferences;

public class SplashActivity extends Activity {

    private int progressStatus = 0;
    private Handler handler;
    private ProgressBar progressBarDatabase;
    private TextView textStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, MainActivity.class));
//        preferences = new Preferences(this);
//        if (preferences.getPreferenceBoolean(KEY_DATABASE_CREATED)) {
//            Methods.changeLanguage(getResources(), preferences.getPreferenceString(KEY_LANGUAGE));
//            if (preferences.getPreferenceInt(KEY_APP_VERSION) == APP_VERSION) {
//                int splashLength = 1500;
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        startActivity(new Intent(SplashActivity.this, MenuActivity.class));
//                        finish();
//                    }
//                }, splashLength);
//            } else {
//                Toast.makeText(this, R.string.updating_database, Toast.LENGTH_LONG).show();
//                progressBarDatabase = (ProgressBar) findViewById(R.id.progress_database);
//                textStatus = (TextView) findViewById(R.id.text_status);
//                handler = new Handler();
//                progressBarDatabase.setVisibility(View.VISIBLE);
//                textStatus.setVisibility(View.VISIBLE);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (progressStatus < 200) {
//                            progressStatus++;
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    progressBarDatabase.setProgress(progressStatus);
//                                    textStatus.setText(progressStatus / 2 + "%");
//                                }
//                            });
//                            try {
//                                Thread.sleep(50);
//                            } catch (InterruptedException ex) {
//                                ex.printStackTrace();
//                            }
//                            if (progressStatus == 200) {
//                                startActivity(new Intent(SplashActivity.this, MenuActivity.class));
//                                finish();
//                            }
//                        }
//                    }
//                }).start();
//                DatabaseHelper database = new DatabaseHelper(this);
//                for (int i = 0; i <= database.getQuestionId(); i++) {
//                    database.deleteQuestion(database.getWritableDatabase(), i);
//                }
//                for (int i = 0; i <= database.getWeaponId(); i++) {
//                    database.deleteWeapon(database.getWritableDatabase(), i);
//                }
//                List<String> questionList = Arrays.asList(Methods.readFromFile(this, FILE_QUESTION).split("\r\n___;___\r\n"));
//                for (String question : questionList) {
//                    String[] questionInfo = question.split("\r\n__;__\r\n");
//                    database.addQuestion(database.getWritableDatabase(), questionInfo[5], questionInfo[0],
//                            questionInfo[1], questionInfo[2], questionInfo[3], Integer.parseInt(questionInfo[4]));
//                }
//                List<String> weaponList = Arrays.asList(Methods.readFromFile(this, FILE_WEAPON).split("\r\n___;___\r\n"));
//                for (String weapon : weaponList) {
//                    String[] weaponInfo = weapon.split("\r\n__;__\r\n");
//                    database.addWeapon(database.getWritableDatabase(), weaponInfo[4], weaponInfo[0], weaponInfo[1],
//                            weaponInfo[2], Integer.parseInt(weaponInfo[3]));
//                }
//                preferences.setPreference(KEY_APP_VERSION, APP_VERSION);
//            }
//        } else {
//            Toast.makeText(this, "Creating database...", Toast.LENGTH_LONG).show();
//            progressBarDatabase = (ProgressBar) findViewById(R.id.progress_database);
//            textStatus = (TextView) findViewById(R.id.text_status);
//            handler = new Handler();
//            progressBarDatabase.setVisibility(View.VISIBLE);
//            textStatus.setVisibility(View.VISIBLE);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (progressStatus < 200) {
//                        progressStatus++;
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                progressBarDatabase.setProgress(progressStatus);
//                                textStatus.setText(progressStatus / 2 + "%");
//                            }
//                        });
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException ex) {
//                            ex.printStackTrace();
//                        }
//                        if (progressStatus == 200) {
//                            startActivity(new Intent(SplashActivity.this, MenuActivity.class));
//                            finish();
//                        }
//                    }
//                }
//            }).start();
//            DatabaseHelper database = new DatabaseHelper(this);
//            preferences.setDefaultPreferences(LANGUAGE_EN);
//            Methods.changeLanguage(getResources(), preferences.getPreferenceString(KEY_LANGUAGE));
//            List<String> questionList = Arrays.asList(Methods.readFromFile(this, FILE_QUESTION).split("\r\n___;___\r\n"));
//            for (String question : questionList) {
//                String[] questionInfo = question.split("\r\n__;__\r\n");
//                database.addQuestion(database.getWritableDatabase(), questionInfo[5], questionInfo[0],
//                        questionInfo[1], questionInfo[2], questionInfo[3], Integer.parseInt(questionInfo[4]));
//            }
//            List<String> weaponList = Arrays.asList(Methods.readFromFile(this, FILE_WEAPON).split("\r\n___;___\r\n"));
//            for (String weapon : weaponList) {
//                String[] weaponInfo = weapon.split("\r\n__;__\r\n");
//                database.addWeapon(database.getWritableDatabase(), weaponInfo[4], weaponInfo[0], weaponInfo[1],
//                        weaponInfo[2], Integer.parseInt(weaponInfo[3]));
//            }
//            preferences.setPreference(KEY_DATABASE_CREATED, true);
//        }
    }

}
