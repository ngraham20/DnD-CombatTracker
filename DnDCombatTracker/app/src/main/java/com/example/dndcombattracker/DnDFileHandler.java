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

    public void createFileIfNotExist(String file_name) throws IOException {
        File file = new File(context.getFilesDir(), file_name);

        if(!file.exists())
        {
            file.createNewFile();
        }
    }

    public String readFile(String file_name) throws IOException {
        File file = new File(context.getFilesDir(), file_name);
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

    public void writeToFile(String file_name, String json) throws IOException {
        File file = new File(context.getFilesDir(), file_name);
        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(json);
        bufferedWriter.close();
    }
}
