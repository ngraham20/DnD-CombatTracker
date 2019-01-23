package com.example.dndcombattracker;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CombatActivity extends AppCompatActivity {

    private static final String TAG = "CombatActivity";

    RecyclerView mRecyclerView;
    CharacterAdapter mAdapter;
    Combat mCombat;
    ArrayList<Character> mCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        mCombat = (Combat) getIntent().getSerializableExtra(CombatAdapter.COMBAT_EXTRA);
        mCharacters = mCombat.getCharacters();

        mRecyclerView = findViewById(R.id.recycler_view);
        mAdapter = new CharacterAdapter(this, mCharacters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fab = findViewById(R.id.add_character_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: FAB Clicked");
                Context context = v.getContext();
            }
        });
    }
}
