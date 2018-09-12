/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.*;
import java.net.*;
import org.json.*;

/**
 *
 * @author Shoaib Khan
 */
public class Database {

    private HashMap<SpeciesAtRisk, ArrayList<Habitat>> data;

    public Database() {
        data = new HashMap();
    }

    public void addRecord(SpeciesAtRisk specie, Habitat habitat) {
        
        // If the HashMap doesn't contain that specie, then add the habitat into a new ArrayList of Habitats and put it in the HaspMap
        if (!data.containsKey(specie)) {
            ArrayList<Habitat> habitatList = new ArrayList();
            habitatList.add(habitat);
            data.put(specie, habitatList);     
        } 
        
        // If the HashMap contains that specie, then add the habitat into the existing ArrayList of Habitats and put it in the HaspMap
        else {
            ArrayList<Habitat> h = data.get(specie);
            h.add(habitat);
            data.remove(specie);    // Just in case used the ".removes()" for safe side by ".put()" method replaces the value anyways 
            data.put(specie, h);
        }
    }

    public void addRecords(String urlJSON) throws MalformedURLException, IOException {
        try {
            URL api_url = new URL(urlJSON);
            URLConnection api = api_url.openConnection();

            api.setRequestProperty("Accept", "application/json");
            api.setRequestProperty("Accept-Language", "en");

            JSONTokener tokener = new JSONTokener(api.getInputStream());
            JSONObject jsondata = new JSONObject(tokener);

            JSONArray fields = jsondata.names();
            JSONArray features = jsondata.getJSONArray("features");

            int numFeatures = features.length();
            Set elements = ((JSONObject) features.get(0)).keySet();

            for (int i = 0; i < numFeatures; i++) {
                JSONObject feature = (JSONObject) features.get(i);
                JSONObject properties = feature.getJSONObject("properties");

                String sciName = properties.get("SciName").toString();
                String commName = properties.get("CommName_E").toString();
                int speciesID = new Integer(properties.get("SpeciesID").toString()).intValue();
                String provinceTerritory = properties.get("ProvTerr").toString();
                int utmZone = new Integer(properties.get("UTMZone").toString()).intValue();
                double longitude = new Double(properties.get("Longitude").toString()).doubleValue();
                double latitude = new Double(properties.get("Latitude").toString()).doubleValue();
                double shapeArea = new Double(properties.get("Shape_Area").toString()).doubleValue();

                SpeciesAtRisk specie = new SpeciesAtRisk(sciName, commName, speciesID);
                Habitat habitat = new Habitat(provinceTerritory, utmZone, latitude, longitude, shapeArea);
                
                // Adding species and habitat to the HashMap using the "addRecord()" method
                this.addRecord(specie, habitat);
            }
        } 
        
        catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        } 
        
        catch (IOException e) {
            System.out.println("IO Error");
        }

    }

    public int getNumSpecies() {
        return data.size();
    }

    public int getNumHabitats(int speciesID) {
        return getHabitats(speciesID).size();
    }

    public double getTotalArea(int speciesID) {
        double area = 0;
        for (Habitat h : getHabitats(speciesID)) {
            area += h.getArea();
        }

        return area;
    }

    public ArrayList<Habitat> getHabitats(int speciesID) {
        for (SpeciesAtRisk specie : data.keySet()) {
            if (specie.getId() == speciesID) {
                return data.get(specie);
            }
        }
        
        return null;
    }

    public Set getAllSpecies() {
        return data.keySet();
    }
}
