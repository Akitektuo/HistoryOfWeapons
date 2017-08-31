package com.akitektuo.historyofweapons.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.akitektuo.historyofweapons.R;
import static com.akitektuo.historyofweapons.util.Constants.KEY_BALANCE;
import static com.akitektuo.historyofweapons.util.Constants.KEY_EXCLUDE_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_HINT_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_SHOW_NR;
import static com.akitektuo.historyofweapons.util.Constants.KEY_SKIP_NR;
import static com.akitektuo.historyofweapons.util.Constants.PRICE_EXCLUDE;
import static com.akitektuo.historyofweapons.util.Constants.PRICE_HINT;
import static com.akitektuo.historyofweapons.util.Constants.PRICE_SHOW;
import static com.akitektuo.historyofweapons.util.Constants.PRICE_SKIP;
import static com.akitektuo.historyofweapons.util.Constants.preferences;

public class ShopActivity extends Activity implements View.OnClickListener {

    private TextView textChip;
    private TextView textCountSkip;
    private TextView textCountHint;
    private TextView textCountExclude;
    private TextView textCountShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        findViewById(R.id.button_back_shop).setOnClickListener(this);
        findViewById(R.id.button_chip).setOnClickListener(this);
        findViewById(R.id.layout_shop_skip).setOnClickListener(this);
        findViewById(R.id.layout_shop_hint).setOnClickListener(this);
        findViewById(R.id.layout_shop_exclude).setOnClickListener(this);
        findViewById(R.id.layout_shop_show).setOnClickListener(this);
        findViewById(R.id.button_chip).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                preferences.setPreference(KEY_BALANCE, 0);
                refreshBalance();
                return true;
            }
        });
        textChip = (TextView) findViewById(R.id.text_balance);
        textCountSkip = (TextView) findViewById(R.id.text_shop_count_skip);
        textCountHint = (TextView) findViewById(R.id.text_shop_count_hint);
        textCountExclude = (TextView) findViewById(R.id.text_shop_count_exclude);
        textCountShow = (TextView) findViewById(R.id.text_shop_count_show);
        refreshBalance();
        textCountSkip.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SKIP_NR)));
        textCountHint.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_HINT_NR)));
        textCountExclude.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_EXCLUDE_NR)));
        textCountShow.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SHOW_NR)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back_shop:
                finish();
                break;
            case R.id.button_chip:
                addToBalance(10);
                refreshBalance();
                // TODO: 11-Dec-16 dev tools to add chips with dialog
                break;
            case R.id.layout_shop_skip:
                if (preferences.getPreferenceInt(KEY_BALANCE) < PRICE_SKIP) {
                    Toast.makeText(this, R.string.not_enough_chips, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.buy_skip);
                    builder.setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferences.setPreference(KEY_SKIP_NR, preferences.getPreferenceInt(KEY_SKIP_NR) + 1);
                            textCountSkip.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SKIP_NR)));
                            takeFromBalance(PRICE_SKIP);
                            refreshBalance();
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
            case R.id.layout_shop_hint:
                if (preferences.getPreferenceInt(KEY_BALANCE) < PRICE_HINT) {
                    Toast.makeText(this, R.string.not_enough_chips, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.buy_hint);
                    builder.setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferences.setPreference(KEY_HINT_NR, preferences.getPreferenceInt(KEY_HINT_NR) + 1);
                            textCountHint.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_HINT_NR)));
                            takeFromBalance(PRICE_HINT);
                            refreshBalance();
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                break;
            case R.id.layout_shop_exclude:
                if (preferences.getPreferenceInt(KEY_BALANCE) < PRICE_EXCLUDE) {
                    Toast.makeText(this, R.string.not_enough_chips, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.buy_exclude);
                    builder.setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferences.setPreference(KEY_EXCLUDE_NR, preferences.getPreferenceInt(KEY_EXCLUDE_NR) + 1);
                            textCountExclude.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_EXCLUDE_NR)));
                            takeFromBalance(PRICE_EXCLUDE);
                            refreshBalance();
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
            case R.id.layout_shop_show:
                if (preferences.getPreferenceInt(KEY_BALANCE) < PRICE_SHOW) {
                    Toast.makeText(this, R.string.not_enough_chips, Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.buy_show);
                    builder.setPositiveButton(R.string.buy, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferences.setPreference(KEY_SHOW_NR, preferences.getPreferenceInt(KEY_SHOW_NR) + 1);
                            textCountShow.setText(getString(R.string.count_tools, preferences.getPreferenceInt(KEY_SHOW_NR)));
                            takeFromBalance(PRICE_SHOW);
                            refreshBalance();
                        }
                    });
                    builder.setNegativeButton(R.string.cancel, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                break;
        }
    }

    private void refreshBalance() {
        textChip.setText(getString(R.string.balance, preferences.getPreferenceInt(KEY_BALANCE)));
    }

    private void addToBalance(int num) {
        preferences.setPreference(KEY_BALANCE, preferences.getPreferenceInt(KEY_BALANCE) + num);
    }

    private void takeFromBalance(int num) {
        preferences.setPreference(KEY_BALANCE, preferences.getPreferenceInt(KEY_BALANCE) - num);
    }

}
