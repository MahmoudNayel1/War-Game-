package cwk4;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    private int locationID;
    private String locationName;
    private String locationDescription;
    public Location_Type locationType;
    private ArrayList<Item> locationItems;
    private HashMap<String, Location> exits;
    private int levelRequirement;

    public Location(int locationID, String locationName, String locationDescription, Location_Type locationType, int levelRequirement) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationType = locationType;
        this.locationItems = new ArrayList<Item>();
        this.exits = new HashMap<String, Location>();
        this.levelRequirement = levelRequirement;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public boolean isConnected(Location otherLocation) {
        for (String exit : exits.keySet()) {
            if (exits.get(exit) == otherLocation) {
                return true;
            }
        }
        return false;
    }

    public int getLevelRequirement() {

        return levelRequirement;
    }

    public void setLevelRequirement(int levelRequirement) {

        this.levelRequirement = levelRequirement;
    }

    public void setLocationName(String locationName) {

        this.locationName = locationName;
    }

    public String getLocationDescription() {

        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {

        this.locationDescription = locationDescription;
    }

    public Location_Type getLocationType() {

        return locationType;
    }

    public void setLocationType(Location_Type locationType) {

        this.locationType = locationType;
    }

    public ArrayList<Item> getLocationItems() {

        return locationItems;
    }

    public void setLocationItems(ArrayList<Item> locationItems) {

        this.locationItems = locationItems;
    }

    public HashMap<String, Location> getExits() {

        return exits;
    }

    public void setExits(HashMap<String, Location> exits) {

        this.exits = exits;
    }

    @Override
    public String toString() {
        String locationDetails = "Location ID: " + locationID + "\n" +
                "Location Name: " + locationName + "\n" +
                "Location Description: " + locationDescription + "\n" +
                "Location Type: " + locationType + "\n" +
                "Level Requirement: " + levelRequirement + "\n" +
                "Exits: " + exits.keySet() + "\n" +
                "Location Items: ";

        if (locationItems.isEmpty()) {
            locationDetails += "None";
        } else {
            for (Item item : locationItems) {
                locationDetails += "\n- " + item.getItemName();
            }
        }

        return locationDetails;
    }

}
