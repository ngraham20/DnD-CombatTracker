package com.example.dndcombattracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;


import java.util.ArrayList;

public class CharacterListFragment extends Fragment {

    //private final Context context = this;
    View view;
    private RecyclerView mRecyclerView;
    private ArrayList<Character> mCharacters = new ArrayList<>();

    private Character dialogCharacter;
    private CharacterAdapter mAdapter;

    private static final String TAG = "CharacterListFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_character_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new CharacterAdapter(getContext(), mCharacters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fab = view.findViewById(R.id.add_character_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: FAB Clicked");
                Context context = v.getContext();
                beginCharacterCreationDialog();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mCharacters = initDefaultCharacters();
    }

    private ArrayList<Character> initDefaultCharacters()
    {
        ArrayList<Character> characters = new ArrayList<>();
        characters.add(new PC("Player1", 19, 125, 5));
        characters.add(new PC("Player2", 19, 125, 5));
        characters.add(new PC("Player3", 19, 125, 5));
        characters.add(new PC("Player4", 19, 125, 5));
        characters.add(new PC("Player5", 19, 125, 5));
        characters.add(new PC("Player6", 19, 125, 5));
        characters.add(new PC("Player7", 19, 125, 5));
        characters.add(new PC("Player8", 19, 125, 5));
        characters.add(new PC("Player9", 19, 125, 5));
        characters.add(new PC("Player10", 19, 125, 5));
        characters.add(new PC("Player11", 19, 125, 5));
        characters.add(new PC("Player12", 19, 125, 5));
        characters.add(new PC("Player13", 19, 125, 5));

        return characters;
    }

    private void beginCharacterCreationDialog()
    {
        dialogCharacter = null;
        characterTypeDialog();
    }

    private void characterNameDialog()
    {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(input);
        builder.setTitle("Character Name");
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: Setting Character Name");

                        if(dialogCharacter!=null) {
                            dialogCharacter.setCharacterName(input.getText().toString());
                            characterACDialog();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: Cancel");
                        cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void characterTypeDialog()
    {
        final CharSequence[] items = {"Monster","NPC","PC"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Character Type");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogCharacter = Character.characterFactory(items[i].toString(), null, -1,-1,-1);
                characterNameDialog();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void characterACDialog()
    {
        final NumberPicker input = new NumberPicker(getContext());
        input.setMaxValue(60);
        input.setMinValue(1);
        input.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(input);
        builder.setTitle("Character AC");
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: Setting Character AC");

                if(dialogCharacter!=null) {
                    dialogCharacter.setArmorClass(input.getValue());
                    characterHPDialog();
                }
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: Cancel");
                        cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void characterHPDialog()
    {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(input);
        builder.setTitle("Character HP");
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: Setting Character HP");

                if(dialogCharacter!=null) {
                    int health = Integer.parseInt(input.getText().toString());
                    dialogCharacter.setMaxHealth(health);
                    dialogCharacter.setCurrentHealth(health);
                    characterInitiativeModDialog();
                }
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: Cancel");
                        cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void characterInitiativeModDialog()
    {
        final NumberPicker input = new NumberPicker(getContext());
        input.setMaxValue(12);
        input.setMinValue(0);
        input.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(input);
        builder.setTitle("Character Initiative Mod");
        builder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick: Setting Character Initiative Modifier");

                if(dialogCharacter!=null) {
                    dialogCharacter.setInitiativeModifier(input.getValue());
                    // add character to list
                    addCharacter(dialogCharacter);
                    clearDialogCharacter();
                }
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick: Cancel");
                        cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void addCharacter(Character character)
    {
        // copy the character and add it to list
        Character newGuy = Character.copy(character);
        mCharacters.add(newGuy);

        // update the ui
        mAdapter.notifyItemInserted(mCharacters.indexOf(newGuy));
    }

    private void cancel()
    {
        // set all dialog variables to null
        clearDialogCharacter();

    }

    private void clearDialogCharacter()
    {
        dialogCharacter = null;
    }

}
