package com.example.dndcombattracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CombatActivity extends AppCompatActivity {

    private static final String TAG = "CombatActivity";

    RecyclerView mRecyclerView;
    CharacterAdapter mAdapter;
    Combat mCombat;
    ArrayList<Character> mCharacters;

    Character mDialogCharacter;
    ArrayList<String> mDialogNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        TextView title = (TextView) findViewById(R.id.combat_title);

        int index = (int) getIntent().getSerializableExtra(CombatAdapter.COMBAT_EXTRA);
        mCombat = CombatMasterList.getInstance().getmCombats().get(index);
        mCharacters = mCombat.getCharacters();

        String name = mCombat.getName();
        title.setText(name);

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
               addCharacterDialog();

            }
        });
    }

    private void addCharacterDialog()
    {

        mDialogNames = new ArrayList<>();
        for(Character character : CharacterMasterList.getInstance().getmCharacters())
        {
            String name = character.getCharacterName();
            mDialogNames.add(name);
        }

        CharSequence[] input = mDialogNames.toArray(new CharSequence[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(input, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Character character = CharacterMasterList.getInstance().getmCharacters().get(i);
                mCombat.addCharacter(character);
                mCharacters = mCombat.getCharacters();
                mAdapter.notifyItemInserted(mCharacters.indexOf(character));
            }
        });
        builder.setTitle("Add Character");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void cancel()
    {
        mDialogCharacter = null;
    }

}
