package com.akitektuo.historyofweapons.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.akitektuo.historyofweapons.R;
import com.akitektuo.historyofweapons.fragment.MenuFragment;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity {

    private MenuFragment fragmentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMenu = new MenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragmentMenu).commit();
    }
}
