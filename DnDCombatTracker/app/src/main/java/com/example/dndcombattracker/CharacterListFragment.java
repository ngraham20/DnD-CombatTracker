package com.example.dndcombattracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CharacterListFragment extends Fragment {

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
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        DnDAdapter adapter = new DnDAdapter(getContext(), mCharacters);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
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
