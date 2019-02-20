package com.example.dndcombattracker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;

public class CharacterMasterList {
    private ArrayList<Character> mCharacters;
    private JSONArray jSonArray;
    private static final CharacterMasterList holder = new CharacterMasterList();
    private final String FILE_NAME = "characters.json";

    /**
     * The only constructor is private to prevent creation of new Master List
     */
    private CharacterMasterList()
    {
        mCharacters = initDefaultCharacters();
    }

    /**
     * returns the character list
     * @return the character list
     */
    public ArrayList<Character> getmCharacters() {
        return mCharacters;
    }

    /**
     * sets the character list
     * @param mCharacters the character list to set to
     */
    public void setmCharacters(ArrayList<Character> mCharacters) {
        this.mCharacters = mCharacters;
    }

    /**
     * Returns the static instance of this object
     * @return this Instance
     */
    public static CharacterMasterList getInstance()
    {
        return holder;
    }

    /**
     * Generates a short list of default characters
     * @return the default list of characters
     */
    private ArrayList<Character> initDefaultCharacters()
    {
        ArrayList<Character> characters = new ArrayList<>();

        return characters;
    }

    public boolean loadCharactersFromFile(Context context) throws IOException, JSONException {
        InputStream inputStream = context.getAssets().open(FILE_NAME);

        int size = inputStream.available();

        byte[] buffer = new byte[size];

        inputStream.read(buffer);

        inputStream.close();

        parseJsonString(new String(buffer, "UTF-8"));

        return true;
    }

    public boolean saveCharactersToFile(Context context) throws IOException {
        return true;
    }

    private void parseJsonString(String json) throws JSONException {
        jSonArray = new JSONArray(json);

        for(int i = 0; i < jSonArray.length(); i++)
        {
            JSONObject jObject = (JSONObject) jSonArray.get(i);

            Character character = Character.characterFactory(
                    jObject.getString("name"),
                    jObject.getString("type"),
                    jObject.getInt("ac"),
                    jObject.getInt("current_hp"),
                    jObject.getInt("init_mod")
            );

            mCharacters.add(character);
        }
    }

    public void addCharacter(Context context, Character character) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject()
                .put("name", character.getCharacterName())
                .put("type", character.getCharacterType())
                .put("ac",character.getArmorClass())
                .put("init_mod",character.getInitiativeModifier())
                .put("init_base",character.getBaseInitiative())
                .put("current_hp",character.getCurrentHealth())
                .put("temp_hp", character.getTempHP());

        jSonArray.put(jsonObject);

        // TODO make this next bit a threaded task to prevent lag

        String jsonString = jSonArray.toString();

        Writer output = null;
        File file = new File(FILE_NAME);
        output = new BufferedWriter(new FileWriter(file));
        output.write(jsonString);
        output.close();
        Toast.makeText(context, "Characters saved", Toast.LENGTH_LONG).show();

    }

    public void removeCharacter(Context context, int index) throws IOException {
        jSonArray.remove(index);

        // TODO make this next bit a threaded task to prevent lag

        String jsonString = jSonArray.toString();

        Writer output = null;
        File file = new File(FILE_NAME);
        output = new BufferedWriter(new FileWriter(file));
        output.write(jsonString);
        output.close();
        Toast.makeText(context, "Characters saved", Toast.LENGTH_LONG).show();
    }
}
