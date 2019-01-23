package com.example.dndcombattracker;

import java.util.ArrayList;

public class CharacterMasterList {
    private ArrayList<Character> mCharacters;
    private static final CharacterMasterList holder = new CharacterMasterList();

    private CharacterMasterList()
    {
        mCharacters = initDefaultCharacters();
    }

    public ArrayList<Character> getmCharacters() {
        return mCharacters;
    }

    public void setmCharacters(ArrayList<Character> mCharacters) {
        this.mCharacters = mCharacters;
    }

    public static CharacterMasterList getInstance()
    {
        return holder;
    }

    private ArrayList<Character> initDefaultCharacters()
    {
        ArrayList<Character> characters = new ArrayList<>();
        characters.add(new PC("Oberon", 19, 125, 5));
        characters.add(new PC("Aeon", 15, 125, 5));
        characters.add(new Monster("Medusa", 25, 1025, 9));
        characters.add(new Monster("Dwarfish Cult Leader", 19, 337, 5));

        return characters;
    }
}
