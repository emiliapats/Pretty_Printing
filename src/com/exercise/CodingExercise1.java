package com.exercise;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Created by rpatil on 10/11/15.
 */
public class CodingExercise1 {


    public static void main(String [] args) {

        String fileName = "input/data.in";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // This will reference one line at a time
        String line = null;
        Persons persons = new Persons();
        PersonsParser parser = new PersonsParser();
        int index=0;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                Person person = parser.parseString(line);
                if(person!=null)
                {
                    persons.getEntries().add(person);
                }
                else
                {
                    persons.getErrors().add(index);
                }
                index++;
            }
            String jsonRepresentation = gson.toJson(persons);
            System.out.println(""+jsonRepresentation);
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }

    
}
