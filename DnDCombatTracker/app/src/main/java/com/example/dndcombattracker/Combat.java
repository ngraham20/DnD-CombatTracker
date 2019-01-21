package com.example.dndcombattracker;

import java.util.ArrayList;


public class Combat
{
    private ArrayList<Character> characters;

    public Combat ()
    {
        characters = new ArrayList<Character>();
    }

    public void addCharacter(Character character)
    {
        characters.add(character);
    }

    public boolean deleteCharacter(Character doomedCharacter)
    {
        return characters.remove(doomedCharacter);
    }

    public void addMultipleCopiesOfCharacter(Character character, int number)
    {
        for(int i = 0; i < number; i++)
        {
            characters.add(character);
        }
    }

    public ArrayList<Character> getCharactersInCombat()
    {
        return characters;
    }

    public Character getCharacter(int index)
    {
        return characters.get(index);
    }


}
