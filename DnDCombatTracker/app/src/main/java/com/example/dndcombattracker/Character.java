package com.example.dndcombattracker;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Character implements Serializable
{
    private int temporaryHP;
    private String characterName;
    private int armorClass;
    private int maxHealth;
    private int currentHealth;
    private int initiativeModifier;
    private int currentInitiative;
    private type characterType;
    private boolean[] successSaves = new boolean[3];
    private boolean[] failSaves = new boolean[3];
    public enum type {MONSTER, NONPLAYER, PLAYER}


    public Character(String newName, int newAC, int newMaxHP, int newInitMod, type newType)
    {
        characterName = newName;
        armorClass = newAC;
        maxHealth = newMaxHP;
        currentHealth = maxHealth;
        initiativeModifier = newInitMod;
        characterType = newType;
    }

    public void resetSavingThrows()
    {
        Arrays.fill(successSaves, Boolean.FALSE);
        Arrays.fill(failSaves, Boolean.FALSE);
    }

    public int getTempHP() {
        if (temporaryHP > 0) {
            return temporaryHP;
        }
        else {
            return -1;
        }
    }

    public void addFailedSave()
    {
        for(int i = 0; i < failSaves.length; i++)
        {
            if(failSaves[i] == false)
            {
                failSaves[i] = true;
                break;
            }
        }
    }

    public void addSuccessfulSave()
    {
        for(int i = 0; i < successSaves.length; i++)
        {
            if(successSaves[i] == false)
            {
                successSaves[i] = true;
                break;
            }
        }
    }

    public void takeDamage(int damage)
    {

        //don't want to damage for negatives cause thats weird reverse healing
        if(damage < 0)
        {
            damage = 0;
        }
        //if all damage goes into taking down the temp hp then just subtract from tempHp and done
        else if(temporaryHP > 0 && damage <= temporaryHP)
        {
            temporaryHP -= damage;

        }
        //damage will overflow temporaryHP, so must subtract from main health
        else
        {
            //temporary hp isn't 0, so subtract it from damage, so later calculation will use leftover damage amt instead of passed in total
            if (temporaryHP > 0)
            {
                temporaryHP = 0;
                damage -= temporaryHP;
            }

            int damagedHealth = currentHealth - damage;

            if(damagedHealth <  0)
            {
                currentHealth = 0;
            }
            else
            {
                currentHealth = damagedHealth;
            }

            currentHealth -= damage;
        }



    }

    public void heal(int healAmount)
    {
        int healedHealth = currentHealth + healAmount;
        if(healedHealth > maxHealth)
        {
            currentHealth = maxHealth;
        }
        else
        {
            currentHealth = healedHealth;
        }
    }

    public void setTemporaryHP(int newTempHP)
    {
        temporaryHP = newTempHP;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    public void setCurrentHealth(int newHealth)
    {
        if(newHealth > maxHealth)
        {
            currentHealth = maxHealth;
        }
        else
        {
            currentHealth = newHealth;
        }
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentInitiative() {
        return currentInitiative;
    }

    public int getInitiativeModifier() {
        return initiativeModifier;
    }

    public String getCharacterType() {

        switch(characterType) {
            case MONSTER:
            {
                return "Monster";
            }
            case PLAYER:
            {
                return "Player";
            }
            case NONPLAYER:
            {
                return "NPC";
            }
            default:
            {
                return "";
            }

        }
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public void setCharacterType(type characterType) {
        this.characterType = characterType;
    }

    public void setCurrentInitiative(int currentInitiative) {
        this.currentInitiative = currentInitiative;
    }

    public void setInitiativeModifier(int initiativeModifier) {
        this.initiativeModifier = initiativeModifier;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }


}
