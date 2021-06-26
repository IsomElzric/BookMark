package com.turner.bookmark;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

public class InternalStorage {
    String textToSave;

    //get string from other class
    //called on button click
    public void InternalStorage(String getinfo){
        textToSave = getinfo;


    }
    public void writeFile(){
        try {
            FileOutputStream fileOutputStream = openFileOutput("Tutorial BookMark.txt", MODE_PRIVATE);
            fileOutputStream.write(textToSave.getBytes());
            fileOutputStream.close();

            Toast.makeText(getApplicationContext(), "Saved Text", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readFile(){
        try{
            FileInputStream fileInputStream = openFileInput("Tutorial BookMark.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = newBufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "/n");
            }
            displayText.setText(stringBuffer.toString());


        }catch(FileNotFoundException e){
            e.printSTackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
