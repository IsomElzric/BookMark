package com.turner.bookmark;

import android.app.Activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class InternalStorage extends Activity {
    ArrayList<String> textToSave = new ArrayList<String>();
    ArrayList<String> dataThatsSaved = new ArrayList<String>();

    //get string from other class
    //called on button click
    public void InternalStorage(ArrayList getinfo){
        textToSave = getinfo;
        writeFile();

    }
    public void writeFile(){
        try {
            FileWriter myWriter = new FileWriter("BookMarkApp.txt");
            myWriter.write(String.valueOf(textToSave));
            myWriter.close();
//            FileOutputStream fileOutputStream = openFileOutput("Tutorial BookMark.txt", MODE_PRIVATE);
//            fileOutputStream.write(textToSave.getBytes());
//            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readFile() {
        try {
            File readTheAwesomeFile = new File("BookMarkApp.txt");
            Scanner myReader = new Scanner(readTheAwesomeFile);
            while (myReader.hasNextLine()) {
                dataThatsSaved = myReader.nextLine();
                return dataThatsSaved;
            }
            myReader.close();

//            FileInputStream fileInputStream = openFileInput("Tutorial BookMark.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            StringBuffer stringBuffer = new StringBuffer();
//
//            String lines;
//            while((lines = bufferedReader.readLine()) != null){
//                stringBuffer.append(lines + "/n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

