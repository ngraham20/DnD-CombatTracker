package com.example.dndcombattracker;

public class NPC extends Character
{
    public NPC(String name, int newAC, int newMaxHP, int newInitMod)
    {
        super(name, newAC, newMaxHP, newInitMod, type.NONPLAYER);

    }

    NPC(NPC npc)
    {
        super(npc);
    }

}
