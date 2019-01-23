package com.example.dndcombattracker;

import java.util.ArrayList;


public class Combat
{
    private ArrayList<Character> characters;
    private String name;

    public Combat (String name)
    {
        this.name = name;
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

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public String getName() {
        return name;
    }
}
