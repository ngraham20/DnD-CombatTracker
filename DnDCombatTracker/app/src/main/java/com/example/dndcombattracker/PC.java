package com.example.dndcombattracker;

public class PC extends Character
{
    public PC(String name, int newAC, int newMaxHP, int newInitMod)
    {
        super(name, newAC, newMaxHP, newInitMod, type.PLAYER);
    }

    PC(PC pc)
    {
        super(pc);
    }
}
