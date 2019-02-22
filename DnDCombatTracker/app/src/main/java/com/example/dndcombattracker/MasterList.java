package com.example.dndcombattracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CombatMasterList {
    private static final CombatMasterList holder = new CombatMasterList();
    public static String NULL_COMBAT = "NULL";
    private static final String FILE_NAME = "combats.json";

    private ArrayList<Combat> mCombats = new ArrayList<>();
    private ArrayList<Character> mCharacters = new ArrayList<>();
    private JSONObject rootObject = new JSONObject();
    private JSONArray jCombats = new JSONArray();
    private JSONArray jOutsideCombat = new JSONArray();

    /**
     * Private Constructor to prevent creation of new Master Lists
     */
    private CombatMasterList()
    {
    }

    private void addCombat(Combat combat) throws JSONException {
        mCombats.add(combat);
        jCombats.put(new JSONObject()
                .put("name",combat.getName())
                .put("characters", new JSONArray(combat.getCharacters())));

        try {
            DnDFileHandler.getInstance().writeToFile(FILE_NAME, rootObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public ArrayList<Character> getmCharacters()
    {
        return mCharacters;
    }

    public static void CreateFileIfNotExist()
    {
        try
        {
            DnDFileHandler.getInstance().createFileIfNotExist(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile() throws IOException, JSONException {
        String jsonString = DnDFileHandler.getInstance().readFile(FILE_NAME);
        parseJsonString(jsonString);
    }

    public void saveToFile()
    {
        String jsonString = rootObject.toString();
    }

    public void parseJsonString(String json) throws JSONException {
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
}
