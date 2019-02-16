package com.example.dndcombattracker;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DnDFileHandler {
    private File file;
    private Context context;
    private FileOutputStream outputStream;
//    private final String CHARACTERS_FILE_NAME = "characters.json";
//    private final String COMBATS_FILE_NAME = "combats.json";

    DnDFileHandler(Context context)
    {
        this.context = context;
    }

    public JSONObject loadJson(String file_string) throws IOException, JSONException {
        FileInputStream is = (FileInputStream) this.context.getAssets().open(file_string);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        String json = new String(buffer, "UTF-8");

        return new JSONObject(json);
    }
}
