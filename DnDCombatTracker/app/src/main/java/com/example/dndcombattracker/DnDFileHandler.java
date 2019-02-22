package com.example.dndcombattracker;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DnDFileHandler {
    private static DnDFileHandler handle = new DnDFileHandler();
    //private static String FILE_NAME = "combats.json";
    public enum type {CHARACTER, COMBAT}
    private Context context;

    private DnDFileHandler()
    {
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public static DnDFileHandler getInstance()
    {
        return handle;
    }

    public void createFileIfNotExist(String fileName) throws IOException {
        File file = new File(context.getFilesDir(), fileName);

        if(!file.exists())
        {
            file.createNewFile();
        }
    }

    public String readFile(String fileName) throws IOException {
        File file = new File(context.getFilesDir(), fileName);
        StringBuffer output = new StringBuffer();
        FileReader fileReader = new FileReader(file.getAbsolutePath());
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = "";

        while((line = bufferedReader.readLine()) != null)
        {
            output.append(line+"\n");
        }

        bufferedReader.close();

        return output.toString();
    }

    public void writeToFile(type type, String fileName, String json) throws IOException {
        File file = new File(context.getFilesDir(), fileName);
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(json);
        bufferedWriter.close();
    }
}
