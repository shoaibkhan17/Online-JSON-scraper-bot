package assign3;

/**
 *
 * @author Shoaib Khan
 */
/**
 * Simple example of a Java program used to query an API.
 *
 * @license http://data.gc.ca/eng/open-government-licence-canada
 *
 * Maven Dependency:
 * <dependency>
 * <groupId>org.json</groupId>
 * <artifactId>json</artifactId>
 * <version>20131018</version>
 * </dependency>
 */
import java.io.*;
import java.net.*;
import java.util.Set;
import org.json.*;

public class Assign3a {

    public static void main(String[] args) {
        try {
            // Other URL's
            //URL api_url = new URL("http://donnees.ec.gc.ca/data/species/developplans/critical-habitat-for-species-at-risk-british-columbia/critical-habitat-for-species-at-risk-british-columbia-williamson-s-sapsucker-sphyrapicus-thyroideus/CH_869_Sphyrapicus_thyroideus.json");
            //URL api_url = new URL("http://donnees.ec.gc.ca/data/species/developplans/critical-habitat-for-species-at-risk-british-columbia/critical-habitat-for-species-at-risk-british-columbia-marbled-murrelet-brachyramphus-marmoratus/CH_39_Brachyramphus_marmoratus.json");

            // Build Connection
            URL api_url = new URL("http://donnees.ec.gc.ca/data/species/developplans/critical-habitat-for-species-at-risk-british-columbia/critical-habitat-for-species-at-risk-british-columbia-rocky-mountain-tailed-frog-ascaphus-montanus/CH_632_Ascaphus_montanus.json");
            URLConnection api = api_url.openConnection();

            // Set HTTP Headers
            api.setRequestProperty("Accept", "application/json");
            api.setRequestProperty("Accept-Language", "en");

            // Get Response
            JSONTokener tokener = new JSONTokener(api.getInputStream());
            JSONObject jsondata = new JSONObject(tokener);

            // JSON Metadata: There are two top-level fields: type and features
            // We are interested in the features. Each one will be a row in our CSV,
            // or an entry in our database
            JSONArray fields = jsondata.names();
            JSONArray features = jsondata.getJSONArray("features");

            // To get the number of species in the list
            int numFeatures = features.length();
            System.out.println("Number of features: " + numFeatures);

            // JSON Metadata: Under features, there are three fields: geometry, type and properties
            //       We will focus on the use of "properties".
            //       "properties" correspond to the column headings of our CSV file.
            Set elements = ((JSONObject) features.get(0)).keySet();

            // Reading and printing out the row (with specific columns) from the JSON file
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
                String chStatus = properties.get("CHStatus").toString();
                double shapeArea = new Double(properties.get("Shape_Area").toString()).doubleValue();
                
                // Print out all the highlighted details to the console from the JSON file
                System.out.println(sciName + "," + commName + "," + speciesID + "," + provinceTerritory + "," + utmZone + "," + longitude + "," + latitude + "," + chStatus + "," + shapeArea);
            }

        } 
        
        catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        } 
        
        catch (IOException e) {
            System.out.println("IO Error");
        }
    }
}
