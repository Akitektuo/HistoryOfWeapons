package com.akitektuo.historyofweapons.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.akitektuo.historyofweapons.R;
import com.akitektuo.historyofweapons.database.DatabaseHelper;
import static com.akitektuo.historyofweapons.util.Constants.KEY_BALANCE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_EXCLUDE_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_HINT_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_LANGUAGE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_QUESTION;
import static com.akitektuo.historyofweapons.util.Constants.KEY_SHOW_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_SKIP_NR;
import static com.akitektuo.historyofweapons.util.Constants.LANGUAGE_EN;
import static com.akitektuo.historyofweapons.util.Constants.TOOL_EXCLUDE;
import static com.akitektuo.historyofweapons.util.Constants.TOOL_HINT;
import static com.akitektuo.historyofweapons.util.Constants.TOOL_SHOW;
import static com.akitektuo.historyofweapons.util.Constants.TOOL_SKIP;
import static com.akitektuo.historyofweapons.util.Constants.preferences;

public class QuizActivity extends Activity implements View.OnClickListener {

    private ImageView imageWeapon;
    private TextView textQuestion;
    private Button buttonVariantA;
    private Button buttonVariantB;
    private Button buttonVariantC;
    private Button buttonVariantD;
    private TextView textDialogSkip;
    private TextView textDialogHint;
    private TextView textDialogExclude;
    private TextView textDialogShow;
    private TextView textSelectedTool;
    private int selectedTool;
    private DatabaseHelper database;
    private int correctVariant;
    private boolean clickable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        Button buttonBack = (Button) findViewById(R.id.button_back_quiz);
        Button buttonTools = (Button) findViewById(R.id.button_tools_quiz);
        imageWeapon = (ImageView) findViewById(R.id.image_weapon_quiz);
        textQuestion = (TextView) findViewById(R.id.text_question_quiz);
        buttonVariantA = (Button) findViewById(R.id.button_variant_a);
        buttonVariantB = (Button) findViewById(R.id.button_variant_b);
        buttonVariantC = (Button) findViewById(R.id.button_variant_c);
        buttonVariantD = (Button) findViewById(R.id.button_variant_d);
        buttonBack.setOnClickListener(this);
        buttonTools.setOnClickListener(this);
        buttonVariantA.setOnClickListener(this);
        buttonVariantB.setOnClickListener(this);
        buttonVariantC.setOnClickListener(this);
        buttonVariantD.setOnClickListener(this);
        database = new DatabaseHelper(this);
        getQuestionFromDatabase();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back_quiz:
                finish();
                break;
            case R.id.button_tools_quiz:
                selectedTool = 4;
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_choose_tool, null);
                builder.setView(viewDialog);
                builder.setPositiveButton(R.string.use, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (selectedTool) {
                            case TOOL_SKIP:
                                preferences.setPreference(KEY_SKIP_NR, preferences.getPreferenceInt(KEY_SKIP_NR) - 1);
                                textDialogSkip.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SKIP_NR)));
                                preferences.setPreference(KEY_QUESTION, preferences.getPreferenceInt(KEY_QUESTION) + 1);
                                getQuestionFromDatabase();
                                break;
                            case TOOL_HINT:
                                preferences.setPreference(KEY_HINT_NR, preferences.getPreferenceInt(KEY_HINT_NR) - 1);
                                textDialogHint.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_HINT_NR)));
                                switch (correctVariant) {
                                    case 0:
                                        buttonVariantA.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                    case 1:
                                        buttonVariantB.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                    case 2:
                                        buttonVariantC.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                    case 3:
                                        buttonVariantD.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                }
                                break;
                            case TOOL_EXCLUDE:
                                preferences.setPreference(KEY_EXCLUDE_NR, preferences.getPreferenceInt(KEY_EXCLUDE_NR) - 1);
                                textDialogExclude.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_EXCLUDE_NR)));
                                if (correctVariant % 2 == 0) {
                                    buttonVariantB.setBackgroundResource(R.drawable.wrong);
                                    buttonVariantB.setTextColor(getResources().getColor(R.color.answer_text));
                                    buttonVariantD.setBackgroundResource(R.drawable.wrong);
                                    buttonVariantD.setTextColor(getResources().getColor(R.color.answer_text));
                                } else {
                                    buttonVariantA.setBackgroundResource(R.drawable.wrong);
                                    buttonVariantA.setTextColor(getResources().getColor(R.color.answer_text));
                                    buttonVariantC.setBackgroundResource(R.drawable.wrong);
                                    buttonVariantC.setTextColor(getResources().getColor(R.color.answer_text));
                                }
                                break;
                            case TOOL_SHOW:
                                preferences.setPreference(KEY_SHOW_NR, preferences.getPreferenceInt(KEY_SHOW_NR) - 1);
                                textDialogShow.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SHOW_NR)));
                                switch (correctVariant) {
                                    case 0:
                                        buttonVariantA.setBackgroundResource(R.drawable.correct);
                                        buttonVariantA.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                    case 1:
                                        buttonVariantB.setBackgroundResource(R.drawable.correct);
                                        buttonVariantB.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                    case 2:
                                        buttonVariantC.setBackgroundResource(R.drawable.correct);
                                        buttonVariantC.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                    case 3:
                                        buttonVariantD.setBackgroundResource(R.drawable.correct);
                                        buttonVariantD.setTextColor(getResources().getColor(R.color.answer_text));
                                        break;
                                }
                                clickable = false;
                                preferences.setPreference(KEY_BALANCE, preferences.getPreferenceInt(KEY_BALANCE) + 10);
                                preferences.setPreference(KEY_QUESTION, preferences.getPreferenceInt(KEY_QUESTION) + 1);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        getQuestionFromDatabase();
                                        buttonVariantA.setBackgroundResource(R.drawable.variant);
                                        buttonVariantB.setBackgroundResource(R.drawable.variant);
                                        buttonVariantC.setBackgroundResource(R.drawable.variant);
                                        buttonVariantD.setBackgroundResource(R.drawable.variant);
                                        buttonVariantA.setTextColor(getResources().getColor(R.color.variant_text));
                                        buttonVariantB.setTextColor(getResources().getColor(R.color.variant_text));
                                        buttonVariantC.setTextColor(getResources().getColor(R.color.variant_text));
                                        buttonVariantD.setTextColor(getResources().getColor(R.color.variant_text));
                                        clickable = true;
                                    }
                                }, 1000);
                                break;
                            default:
                                Toast.makeText(getBaseContext(), R.string.tool_not_selected, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setTitle(R.string.select_tool).setNeutralButton(R.string.cancel, null);
                builder.show();
                LinearLayout layoutSkip = (LinearLayout) viewDialog.findViewById(R.id.layout_dialog_skip);
                LinearLayout layoutHint = (LinearLayout) viewDialog.findViewById(R.id.layout_dialog_hint);
                LinearLayout layoutExclude = (LinearLayout) viewDialog.findViewById(R.id.layout_dialog_exclude);
                LinearLayout layoutShow = (LinearLayout) viewDialog.findViewById(R.id.layout_dialog_show);
                textDialogSkip = (TextView) viewDialog.findViewById(R.id.text_dialog_count_skip);
                textDialogHint = (TextView) viewDialog.findViewById(R.id.text_dialog_count_hint);
                textDialogExclude = (TextView) viewDialog.findViewById(R.id.text_dialog_count_exclude);
                textDialogShow = (TextView) viewDialog.findViewById(R.id.text_dialog_count_show);
                textSelectedTool = (TextView) viewDialog.findViewById(R.id.text_dialog_selected_tool);
                textSelectedTool.setText(R.string.select_tool);
                layoutSkip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preferences.getPreferenceInt(KEY_SKIP_NR) == 0) {
                            textSelectedTool.setText(R.string.not_tools);
                        } else {
                            selectedTool = 0;
                            textSelectedTool.setText(R.string.skip_selected);
                        }
                    }
                });
                layoutHint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preferences.getPreferenceInt(KEY_HINT_NR) == 0) {
                            textSelectedTool.setText(R.string.not_tools);
                        } else {
                            selectedTool = 1;
                            textSelectedTool.setText(R.string.hint_selected);
                        }
                    }
                });
                layoutExclude.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preferences.getPreferenceInt(KEY_EXCLUDE_NR) == 0) {
                            textSelectedTool.setText(R.string.not_tools);
                        } else {
                            selectedTool = 2;
                            textSelectedTool.setText(R.string.exclude_selected);
                        }
                    }
                });
                layoutShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (preferences.getPreferenceInt(KEY_SHOW_NR) == 0) {
                            textSelectedTool.setText(R.string.not_tools);
                        } else {
                            selectedTool = 3;
                            textSelectedTool.setText(R.string.show_selected);
                        }
                    }
                });
                textDialogSkip.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SKIP_NR)));
                textDialogHint.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_HINT_NR)));
                textDialogExclude.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_EXCLUDE_NR)));
                textDialogShow.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SHOW_NR)));
                break;
            case R.id.button_variant_a:
                onVariantClick(0, buttonVariantA);
                break;
            case R.id.button_variant_b:
                onVariantClick(1, buttonVariantB);
                break;
            case R.id.button_variant_c:
                onVariantClick(2, buttonVariantC);
                break;
            case R.id.button_variant_d:
                onVariantClick(3, buttonVariantD);
                break;
        }
    }

    private void getQuestionFromDatabase() {
        if (preferences.getPreferenceInt(KEY_QUESTION) > database.getQuestionId()) {
            preferences.setPreference(KEY_QUESTION, 0);
        }
        Cursor cursor = database.getQuestionForId(database.getReadableDatabase(), preferences.getPreferenceInt(KEY_QUESTION));
        if (cursor.moveToFirst()) {
            imageWeapon.setImageDrawable(getDrawable(getResources().getIdentifier(cursor.getString(1), "drawable", getPackageName())));
            String[] variant;
            if (preferences.getPreferenceString(KEY_LANGUAGE).equals(LANGUAGE_EN)) {
                textQuestion.setText(cursor.getString(2));
                variant = cursor.getString(4).split("_;_");
            } else {
                textQuestion.setText(cursor.getString(3));
                variant = cursor.getString(5).split("_;_");
            }
            buttonVariantA.setText(variant[0]);
            buttonVariantB.setText(variant[1]);
            buttonVariantC.setText(variant[2]);
            buttonVariantD.setText(variant[3]);
            correctVariant = cursor.getInt(6);
        } else {
            Toast.makeText(this, "Error occurred while trying to get the question.", Toast.LENGTH_LONG).show();
        }

    }

    private void onVariantClick(int id, Button button) {
        if (clickable) {
            if (correctVariant == id) {
                button.setBackgroundResource(R.drawable.correct);
                button.setTextColor(getResources().getColor(R.color.answer_text));
                preferences.setPreference(KEY_BALANCE, preferences.getPreferenceInt(KEY_BALANCE) + 10);
                preferences.setPreference(KEY_QUESTION, preferences.getPreferenceInt(KEY_QUESTION) + 1);
                clickable = false;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getQuestionFromDatabase();
                        buttonVariantA.setBackgroundResource(R.drawable.variant);
                        buttonVariantB.setBackgroundResource(R.drawable.variant);
                        buttonVariantC.setBackgroundResource(R.drawable.variant);
                        buttonVariantD.setBackgroundResource(R.drawable.variant);
                        buttonVariantA.setTextColor(getResources().getColor(R.color.variant_text));
                        buttonVariantB.setTextColor(getResources().getColor(R.color.variant_text));
                        buttonVariantC.setTextColor(getResources().getColor(R.color.variant_text));
                        buttonVariantD.setTextColor(getResources().getColor(R.color.variant_text));
                        clickable = true;
                    }
                }, 1000);
            } else {
                button.setBackgroundResource(R.drawable.wrong);
                button.setTextColor(getResources().getColor(R.color.answer_text));
                preferences.setPreference(KEY_BALANCE, preferences.getPreferenceInt(KEY_BALANCE) - 10);
            }
        }
    }

}
