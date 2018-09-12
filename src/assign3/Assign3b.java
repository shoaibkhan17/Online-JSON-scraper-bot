/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

/**
 *
 * @author Shoaib Khan
 */
public class Assign3b {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Database myDatabase = new Database();
            String url = "http://donnees.ec.gc.ca/data/species/developplans/critical-habitat-for-species-at-risk-british-columbia/critical-habitat-for-species-at-risk-british-columbia-rocky-mountain-tailed-frog-ascaphus-montanus/CH_632_Ascaphus_montanus.json";

            // Adding species and habitats to the database
            myDatabase.addRecords(url);

            // Printing out the number of species in the database
            System.out.println("Number of species in the database: " + myDatabase.getNumSpecies() + "\n");
            
            // We know that there is only one species in the database and we need it's species id to get the habitat details
            // We could also hardcode the spicesId in this case becuase we know it is 632
            // I have done it both ways but I will comment out the hardcoded way
            
            // IF WE DIDNT HAVE THE SPECIESID GIVEN RIGHT AWAY OR USING A DIFFERENT SPECIES JSON LINK
            
            // Getting the speciesId through the database since there is only one specie in it
            int speciesId = 0;
            Set<SpeciesAtRisk> mySet = myDatabase.getAllSpecies();
            for (SpeciesAtRisk s: mySet)
            {
               speciesId = s.getId();
            }
            
            // Printing out the all the habitats for that one specie using the speciesId
            System.out.println("Printing out all habitats for species number (" + speciesId + "):");
            for (Habitat h : myDatabase.getHabitats(speciesId)) {
                System.out.println(h.toString());
            }

            // Printing out the number of habitats and the total area for that one specie using the speciesId
            System.out.println("\nNumber of habitats for species number (" + speciesId + "): " + myDatabase.getNumHabitats(speciesId));
            System.out.println("\nTotal area for species number (" + speciesId + "): " + myDatabase.getTotalArea(speciesId));
            
            /*
            // BY HARD CODING THE SPEICES ID BECUASE WE ALREADY KNOW WHAT IT IS THROUGH THE JSON FILE
            // Printing out the all the habitats for species number 632
            System.out.println("Printing out all habitats for species number (632)");
            for (Habitat h : myDatabase.getHabitats(632)) {
                System.out.println(h.toString());
            }

            // Printing out the number of habitats and the total area for species number 632
            System.out.println("\nNumber of habitats for species number (632): " + myDatabase.getNumHabitats(632));
            System.out.println("\nTotal area for species number (632): " + myDatabase.getTotalArea(632));
            */
        }   
        
        catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        }    
        
        catch (IOException e) {
            System.out.println("IO Error");
        }
    }
}
