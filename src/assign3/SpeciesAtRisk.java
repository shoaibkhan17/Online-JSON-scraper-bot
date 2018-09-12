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
public class SpeciesAtRisk {

    private String scientificName;
    private String commonName;
    private int id;

    public SpeciesAtRisk(String sciName, String cName, int id) {
        scientificName = sciName;
        commonName = cName;
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public int getId() {
        return id;
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

        SpeciesAtRisk temp = (SpeciesAtRisk) other;
        return (scientificName.equals(temp.getScientificName()) && commonName.equals(temp.getCommonName()) && id == temp.getId());
    }

    @Override
    public int hashCode() {
        Integer myId = id;
        return (scientificName.hashCode() + commonName.hashCode() + myId.hashCode());
    }

    @Override
    public String toString() {
        return scientificName + ", " + commonName + ", " + id;
    }
}
