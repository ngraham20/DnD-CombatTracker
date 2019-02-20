package com.example.dndcombattracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private ArrayList<Character> mCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started.");

        // set file handler context now
        DnDFileHandler.getInstance().setContext(this);
        CharacterMasterList.CreateMasterFileIfNotExist();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new CharacterListFragment());
    }

    /**
     * Loads the fragment to the content view
     * @param fragment the fragment to load
     * @return boolean success
     */
    private boolean loadFragment(Fragment fragment)
    {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }

        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.navigation_character:
                fragment = new CharacterListFragment();
                break;
            case R.id.navigation_combat:
                fragment = new CombatListFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
