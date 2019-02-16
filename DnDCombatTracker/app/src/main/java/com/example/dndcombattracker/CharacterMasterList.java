package com.example.dndcombattracker;

import android.content.Context;

import java.util.ArrayList;

public class CharacterMasterList {
    private Context context;
    private ArrayList<Character> mCharacters;
    private static final CharacterMasterList holder = new CharacterMasterList();

    /**
     * The only constructor is private to prevent creation of new Master List
     */
    private CharacterMasterList()
    {
        mCharacters = initDefaultCharacters();
    }

    /**
     * returns the character list
     * @return the character list
     */
    public ArrayList<Character> getmCharacters() {
        return mCharacters;
    }

    /**
     * sets the character list
     * @param mCharacters the character list to set to
     */
    public void setmCharacters(ArrayList<Character> mCharacters) {
        this.mCharacters = mCharacters;
    }

    /**
     * Returns the static instance of this object
     * @return this Instance
     */
    public static CharacterMasterList getInstance()
    {
        return holder;
    }

    /**
     * Generates a short list of default characters
     * @return the default list of characters
     */
    private ArrayList<Character> initDefaultCharacters()
    {
        ArrayList<Character> characters = new ArrayList<>();
//        characters.add(new PC("Oberon", 19, 125, 5));
//        characters.add(new PC("Aeon", 15, 125, 5));
//        characters.add(new Monster("Medusa", 25, 1025, 9));
//        characters.add(new Monster("Dwarfish Cult Leader", 19, 337, 5));
//        characters.add(new PC("Grahnath", 19, 135, 5));
//        characters.add(new PC("Kelly", 12, 100, 6));
//        characters.add(new Monster("Gnome King", 20, 637, 7));

        DnDFileHandler handler = new DnDFileHandler(context);

        return characters;
    }
}
