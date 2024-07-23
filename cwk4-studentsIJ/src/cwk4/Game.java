package cwk4;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Game {
    private String admiralName;
    private int warChest;
    private List<Force> activeStarFleet;
    private List<Force> uffDock;
    private List<Force> destroyedForces;
    private Set<Force> UFFDockRefs;

    public Game(String admiralName, int warChest) {
        this.admiralName = admiralName;
        this.warChest = warChest;
        activeStarFleet = new ArrayList<>();
        uffDock = new ArrayList<>();
        destroyedForces = new ArrayList<>();
        UFFDockRefs = new HashSet<>();
    }

    public String getAllForces() {
        String result = "";

        // list all forces in active star fleet
        if (activeStarFleet.isEmpty()) {
            result += "Active Star Fleet: No forces\n";
        } else {
            result += "Active Star Fleet:\n";
            for (Force force : activeStarFleet) {
                result += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }

        // list all forces in uff dock
        if (uffDock.isEmpty()) {
            result += "UFF Dock: No forces\n";
        } else {
            result += "UFF Dock:\n";
            for (Force force : uffDock) {
                result += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }

        // list all destroyed forces
        if (destroyedForces.isEmpty()) {
            result += "Destroyed Forces: No destroyed forces\n";
        } else {
            result += "Destroyed Forces:\n";
            for (Force force : destroyedForces) {
                result += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }

        return result;
    }

    public boolean isInUFFDock(String ref) {
        return UFFDockRefs.contains(ref);
    }
}
