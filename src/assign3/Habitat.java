/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign3;

/**
 *
 * @author Shoaib Khan
 */
public class Habitat {

    private String provinceTerritory;
    private int utmZone;
    private double latitude;
    private double longitude;
    private double area;

    public Habitat(String provTerr, int utmZone, double latitude, double longitude, double area) {
        provinceTerritory = provTerr;
        this.utmZone = utmZone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.area = area;
    }

    public double getArea() {
        return area;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getProvinceterritory() {
        return provinceTerritory;
    }

    public int getUtmZone() {
        return utmZone;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        Habitat temp = (Habitat) other;
        return (provinceTerritory.equals(temp.getProvinceterritory()) && utmZone == temp.getUtmZone() && (Math.abs(latitude - temp.getLatitude()) < 0.001) && (Math.abs(longitude - temp.getLongitude()) < 0.001) && (Math.abs(area - temp.getArea()) < 0.001));
    }

    @Override
    public String toString() {
        return provinceTerritory + "," + utmZone + "," + latitude + "," + longitude + "," + area;
    }
}
