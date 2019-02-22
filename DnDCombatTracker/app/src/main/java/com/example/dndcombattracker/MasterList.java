package com.example.dndcombattracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MasterList {
    private static final MasterList holder = new MasterList();
    public static String NULL_COMBAT = "NULL";
    private static final String FILE_NAME = "combats.json";

    private ArrayList<Combat> mCombats = new ArrayList<>();
    private ArrayList<Character> mCharacters = new ArrayList<>();
    private ArrayList<Character> mOutsideCombat = new ArrayList<>();
    private JSONObject rootObject = new JSONObject();
    private JSONArray jCombats = new JSONArray();
    private JSONArray jOutsideCombat = new JSONArray();

    /**
     * Private Constructor to prevent creation of new Master Lists
     */
    private MasterList()
    {
    }

    public void addCombat(Combat combat) throws JSONException {
        mCombats.add(combat);
        jCombats.put(new JSONObject()
                .put("name",combat.getName())
                .put("characters", new JSONArray(combat.getCharacters())));

        // TODO move this to async at some point
        save();
    }

    public void addCharacter(Character character) throws JSONException
    {
        mCharacters.add(character);
        mOutsideCombat.add(character);
        jOutsideCombat.put(new JSONObject()
                .put("name", character.getCharacterName())
                .put("type", character.getCharacterType())
                .put("ac",character.getArmorClass())
                .put("init_mod",character.getInitiativeModifier())
                .put("init_base",character.getBaseInitiative())
                .put("current_hp",character.getCurrentHealth())
                .put("temp_hp", character.getTempHP()));

        // TODO move this to async at some point
        save();
    }

    public void removeCharacter(Character character)
    {
        int index = mOutsideCombat.indexOf(character);
        jOutsideCombat.remove(index);
        mOutsideCombat.remove(index);
        mCharacters.remove(character);
        save();
    }

    public void removeCombat(Combat combat)
    {
        int index = mCombats.indexOf(combat);
        jCombats.remove(index);
        mCombats.remove(index);
        save();
    }



    /**
     * Returns the only instance
     * @return this instance
     */
    public static MasterList getInstance()
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

    public ArrayList<Character> getmCharacters()
    {
        return mCharacters;
    }

    public static void CreateFileIfNotExist()
    {
        try
        {
            if(DnDFileHandler.getInstance().createFileIfNotExist(FILE_NAME))
            {
                JSONObject root = new JSONObject()
                        .put("combats", new JSONArray())
                        .put("no_combats", new JSONArray());

                DnDFileHandler.getInstance().writeToFile(FILE_NAME, root.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void load() throws IOException, JSONException {
        String jsonString = DnDFileHandler.getInstance().readFile(FILE_NAME);
        parseJsonString(jsonString);
    }

    public void save()
    {
        try {
            DnDFileHandler.getInstance().writeToFile(FILE_NAME, rootObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonString(String json) throws JSONException {
        rootObject = new JSONObject(json);

        jCombats = rootObject.getJSONArray("combats");
        jOutsideCombat = rootObject.getJSONArray("no_combats");

        // add characters not in combat

        for(int i = 0; i < jOutsideCombat.length(); i++)
        {
            JSONObject jCharacter = jOutsideCombat.getJSONObject(i);

            Character character = Character.characterFactory(
                    jCharacter.getString("name"),
                    jCharacter.getString("type"),
                    jCharacter.getInt("ac"),
                    jCharacter.getInt("current_hp"),
                    jCharacter.getInt("init_mod")
            );

            character.setInCombat(false, NULL_COMBAT);
            mOutsideCombat.add(character);
            mCharacters.add(character);
        }

        // grab combats and all characters in that combat
        for(int i = 0; i < jCombats.length(); i++)
        {
            JSONObject jCombat = jCombats.getJSONObject(i);
            JSONArray characters = jCombat.getJSONArray("characters");

            Combat combat = new Combat(jCombat.getString("name"));

            for(int j = 0; j < characters.length(); j++)
            {
                JSONObject jCharacter = characters.getJSONObject(j);
                Character character = Character.characterFactory(
                        jCharacter.getString("name"),
                        jCharacter.getString("type"),
                        jCharacter.getInt("ac"),
                        jCharacter.getInt("current_hp"),
                        jCharacter.getInt("init_mod"));

                combat.addCharacter(character);
                character.setInCombat(true, combat.getName());
                mCharacters.add(character);
            }

            mCombats.add(combat);

        }
    }

    public void addCharacterToCombat(Character character, Combat combat)
    {
        character.setInCombat(true, combat.getName());
        combat.addCharacter(character);
        JSONObject jCombat = null;
        try {
            jCombat = jCombats.getJSONObject(mCombats.indexOf(combat));
            jCombat.getJSONArray("characters")
                    .put(new JSONObject()
                    .put("name", character.getCharacterName())
                    .put("type", character.getCharacterType())
                    .put("ac",character.getArmorClass())
                    .put("init_mod",character.getInitiativeModifier())
                    .put("init_base",character.getBaseInitiative())
                    .put("current_hp",character.getCurrentHealth())
                    .put("temp_hp", character.getTempHP()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        int index = mOutsideCombat.indexOf(character);
        mOutsideCombat.remove(index);
        jOutsideCombat.remove(index);

        // TODO async
        save();
    }

    public void removeCharacterFromCombat(Character character, Combat combat)
    {
        int index = combat.getCharacters().indexOf(character);
        character.setInCombat(false, NULL_COMBAT);
        combat.deleteCharacter(character);
        JSONObject jCombat = null;

        try {
            jCombat = jCombats.getJSONObject(mCombats.indexOf(combat));
            jCombat.getJSONArray("characters").remove(index);

            jOutsideCombat
                    .put(new JSONObject()
                            .put("name", character.getCharacterName())
                            .put("type", character.getCharacterType())
                            .put("ac",character.getArmorClass())
                            .put("init_mod",character.getInitiativeModifier())
                            .put("init_base",character.getBaseInitiative())
                            .put("current_hp",character.getCurrentHealth())
                            .put("temp_hp", character.getTempHP()));

            mOutsideCombat.add(character);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // TODO make async
        save();
    }
}
