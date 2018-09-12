/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Shoaib Khan
 */
public class Assign3c {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String readingFileName = "assign3-urls.txt";
        String writingFileName = "assign3-summary.txt";
        FileReader readingFile;
        BufferedReader reader;
        PrintStream writer;

        try {
            readingFile = new FileReader(readingFileName);
            reader = new BufferedReader(readingFile);
            writer = new PrintStream(writingFileName);
            Database myData = new Database();
            String line;

            // Getting the start time and writing it on file
            Date startTime = new Date(System.currentTimeMillis());
            writer.println("Start Time " + startTime);

            // Writing the url's on file and adding them on the database
            while ((line = reader.readLine()) != null) {
                writer.println("Reading in " + line);
                myData.addRecords(line);
            }

            // Writing the database summary on file
            writer.println("Number of species: " + myData.getNumSpecies());
            writer.println("The list of species are");

            Set<SpeciesAtRisk> mySet = myData.getAllSpecies();

            // Writing all the species with their number of habitats and the total area on file
            for (SpeciesAtRisk s : mySet) {
                writer.println(s.getScientificName() + "(" + s.getCommonName() + ")" + " " + myData.getNumHabitats(s.getId()) + " " + myData.getTotalArea(s.getId()));
            }

            // Getting the end data and writing on file.
            Date endTime = new Date(System.currentTimeMillis());
            writer.println("End Time " + endTime);
        } 
        
        catch (IOException e) {
            System.out.println(e.getMessage());
        } 
        
        finally {
            //reader.close();       // Gives an error on my laptop but it work on Minto computers
        }
    }

}
