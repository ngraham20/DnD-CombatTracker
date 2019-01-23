package com.example.dndcombattracker;

import java.io.Serializable;
import java.util.ArrayList;


public class Combat implements Serializable
{
    private ArrayList<Character> characters;
    private String name;

    public Combat (String name)
    {
        this.name = name;
        characters = new ArrayList<Character>();
    }

    public Combat(Combat other) {
        this.characters = other.characters;
        this.name = other.name;
    }

    public void addCharacter(Character character)
    {
        characters.add(character);
    }

    public boolean deleteCharacter(Character doomedCharacter)
    {
        return characters.remove(doomedCharacter);
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
