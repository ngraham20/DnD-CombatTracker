package com.example.dndcombattracker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class CharacterListFragment extends Fragment {

    //private final Context context = this;
    View view;
    private RecyclerView mRecyclerView;
    private ArrayList<Character> mCharacters = new ArrayList<>();

    private static final String TAG = "CharacterListFragment";

    public CharacterListFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_character_list, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        CharacterAdapter adapter = new CharacterAdapter(getContext(), mCharacters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                layoutManager.getOrientation());

        mRecyclerView.addItemDecoration(dividerItemDecoration);

        FloatingActionButton fab = view.findViewById(R.id.add_character_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");


                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCharacters = initDefaultCharacters();
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
}
