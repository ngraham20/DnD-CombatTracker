package com.example.dndcombattracker;

import java.util.ArrayList;

public class CombatMasterList {
    private static final CombatMasterList holder = new CombatMasterList();
    private ArrayList<Combat> mCombats;

    private CombatMasterList()
    {
        mCombats = initDefaultCombat();
    }

    public static CombatMasterList getInstance()
    {
        return holder;
    }

    public ArrayList<Combat> getmCombats() {
        return this.mCombats;
    }

    public void setmCombats(ArrayList<Combat> mCombats) {
        this.mCombats = mCombats;
    }

    private ArrayList<Combat> initDefaultCombat()
    {
        ArrayList<Combat> combats = new ArrayList<>();
        Combat combat = new Combat("Mountain Crypt");
        combat.addCharacter(new PC("Grahnath", 19, 135, 5));
        combat.addCharacter(new PC("Kelly", 12, 100, 6));
        combat.addCharacter(new Monster("Gnome King", 20, 637, 7));
        combats.add(combat);
        return combats;
    }
}
