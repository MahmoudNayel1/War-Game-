package cwk4;
import java.util.ArrayList;

public class LocationManager {
    private ArrayList<Location> locations;

    public LocationManager() {
        this.locations = new ArrayList<>();
    }

    public void addLocation(Location location) {
        this.locations.add(location);
    }

    public Location getLocation(String locationName) {
        for (Location location : locations) {
            if (location.getLocationName().equalsIgnoreCase(locationName)) {
                return location;
            }
        }
        return null;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public boolean canMove(Player player, String locationName) {
        Location currentLocation = player.getCurrentLocation();
        Location newLocation = getLocation(locationName);
        if (newLocation == null) {
            return false;
        }
        if (!currentLocation.isConnected(newLocation)) {
            return false;
        }
        if (newLocation.getLevelRequirement() > player.getLevel()) {
            return false;
        }
        return true;
    }

    public boolean move(Player player, String locationName) {
        if (!canMove(player, locationName)) {
            return false;
        }
        Location newLocation = getLocation(locationName);
        player.setCurrentLocation(newLocation);
        return true;
    }
}
