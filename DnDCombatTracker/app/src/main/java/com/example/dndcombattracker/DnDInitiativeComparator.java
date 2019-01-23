package com.example.dndcombattracker;

import java.util.Comparator;

public class DnDInitiativeComparator implements Comparator<Character> {
    @Override
    public int compare(Character character, Character t1) {
        return character.getCurrentInitiative();
    }
}
