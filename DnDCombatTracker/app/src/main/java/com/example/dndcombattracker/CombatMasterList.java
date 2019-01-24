package com.example.dndcombattracker;

import java.util.ArrayList;

public class CombatMasterList {
    private static final CombatMasterList holder = new CombatMasterList();
    private ArrayList<Combat> mCombats;

    /**
     * Private Constructor to prevent creation of new Master Lists
     */
    private CombatMasterList()
    {
        mCombats = initDefaultCombat();
    }

    /**
     * Returns the only instance
     * @return this instance
     */
    public static CombatMasterList getInstance()
    {
        return holder;
    }

    /**
     * Returns the combats list
     * @return the combats list
     */
    public ArrayList<Combat> getmCombats() {
        return this.mCombats;
    }

    /**
     * Initializes the combats list with a default combat
     * @return the default combats list
     */
    private ArrayList<Combat> initDefaultCombat()
    {
        ArrayList<Combat> combats = new ArrayList<>();
        Combat combat = new Combat("Mountain Crypt");
        combats.add(combat);
        return combats;
    }
}
