package cwk4; 
import java.util.*;
import java.io.*;
/**
 * This class implements the behaviour expected from a WIN
 system as required for 5COM2007 - March 2023
 * 
 * @author Team 2
 * @version March 2023
 */
public class SpaceWars implements WIN {
    private Force[] uffDock;  // Array of Force objects stored in the UFF dock
    private Battle[] battles; // Array of Battle objects fought during the game

    private String admiralName;
    private String admiral;

    private int warChest;     // The number of coins in the warChest
    private int numPlayers;

    private boolean defeated;
    private boolean isInBattle;

    private List<Force> activeStarFleet;
    private List<Force> destroyedForces = new ArrayList<>();
    private ArrayList<Force> activeForces = new ArrayList<Force>();

//**************** WIN **************************  
    /** Constructor requires the name of the admiral
     * @param admiralName the name of the admiral
     */

   public SpaceWars(String admiralName){
       this.admiralName = admiralName;
       this.admiral = "Admiral " + admiralName;
       this.warChest = 1000;
       this.defeated = false;
       this.activeStarFleet = new ArrayList<>();
       setupForces();
       setupBattles();
   }


    /** Second constructor - task 3.5
     *  To be added for task 3.5
     */

    public static List<Battle> readBattles(String filename) {
        List<Battle> battles = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String attackerCode = parts[0];
                String defenderCode = parts[1];
                int battleNumber = Integer.parseInt(parts[2]);
                Battle.BattleType battleType = Battle.BattleType.valueOf(parts[3]);
                int battleStrength = Integer.parseInt(parts[4]);

                // Find attacker and defender forces
                Force attacker = null;
                Force defender = null;
                Iterable<? extends Force> forces = null;
                for (Force force : forces) {
                    if (force.getReferenceCode().equals(attackerCode)) {
                        attacker = force;
                    } else if (force.getReferenceCode().equals(defenderCode)) {
                        defender = force;
                    }
                    if (attacker != null && defender != null) {
                        break;
                    }
                }

                // Create and add the battle object to the list
                if (attacker != null && defender != null) {
                    Battle battle = new Battle(attacker, defender);
                    battle.battleNumber = battleNumber;
                    battle.battleType = battleType;
                    battle.battleStrength = battleStrength;
                    battles.add(battle);
                } else {
                    System.out.printf("Invalid battle input: attacker %s or defender %s not found.\n", attackerCode, defenderCode);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return battles;
    }






    public SpaceWars(String admiralName, int warChest, int numPlayers, String filename) {
        this.admiralName = admiralName;
        this.admiral = "Admiral " + admiralName;
        this.warChest = warChest;
        this.defeated = false;
        this.numPlayers = numPlayers;
        this.activeStarFleet = new ArrayList<>();
        setupForces();
        readBattles(filename);
    }




    /**Returns a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * Active Star Fleet(ASF),(or, "No forces" if Star Fleet is empty)
     * @return a String representation of the state of the game,
     * including the name of the admiral, state of the war chest,
     * whether defeated or not, and the forces currently in the 
     * Star Fleet,(or, "No forces" if Active Star Fleet is empty)
     **/


    public String toString() {
        return "SpaceWars[admiralName=" + admiralName + ", warChest=" + warChest +
                ", defeated=" + defeated + ", activeStarFleet=" + activeStarFleet + "]";
    }


    public String getGameState() {
        String gameState = "";

        // Add admiral's name to gameState
        gameState += "Admiral: " + admiralName + "\n";

        // Add war chest state to gameState
        gameState += "War Chest: " + warChest + "\n";

        // Check if the game is defeated or not
        if (isDefeated()) {
            gameState += "Game Over: Defeated\n";
        } else {
            gameState += "Game Over: Not Defeated\n";
        }

        // Add Active Star Fleet to gameState
        if (activeStarFleet.isEmpty()) {
            gameState += "Active Star Fleet: No forces\n";
        } else {
            gameState += "Active Star Fleet:\n";
            for (Force force : activeStarFleet) {
                gameState += "- " + force.getReferenceCode() + "\n";
            }
        }

        return gameState;
    }



    /** returns true if war chest <=0 AND the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     * @returns true if war chest <=0 and the active Star Fleet(ASF) has no 
     * forces which can be recalled. 
     */

    public boolean isGameOver() {
        // Check if war chest is empty
        if (warChest <= 0) {
            // Check if all forces in the active Star Fleet are currently in battle
            for (Force force : activeStarFleet) {
                if (!force.isActive()) {
                    return false;
                }
            }
            // If all forces in the active Star Fleet are currently in battle, return true
            return true;
        }
        // If war chest is not empty, return false
        return false;
    }

    public boolean isDefeated() {
        // Check if the war chest is empty and there are no active forces left
        if (warChest <= 0 && activeStarFleet.size() == 0) {
            defeated = true;
        } else {
            defeated = false;
        }
        return defeated;
    }



    /** returns the number of bitcoins in the war chest
     * @returns the number of bitcoins in the war chest
     */
    public int getWarchest()
    {
        return warChest;
    }
    
    /** Returns a list of all forces in the system by listing :
     * All forces in the Active Star Fleet(ASF), or "No forces in ASF")
     * All forces remaining in the UFF dock, or "No forces in UFF dock
     * All forces destroyed as a result of losing a battle, or "No destroyed forces"
     */
    public String getAllForces() {
        String result = "";

        // Active Star Fleet
        result += "Active Star Fleet:\n";
        if (activeStarFleet.isEmpty()) {
            result += "No forces in ASF\n";
        } else {
            for (Force force : activeStarFleet) {
                result += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }

        // UFF Dock
        result += "UFF Dock:\n";
        if (uffDock.length == 0) {
            result += "No forces in UFF dock\n";
        } else {
            for (Force force : uffDock) {
                result += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }

        // Destroyed forces
        result += "Destroyed forces:\n";
        if (destroyedForces.isEmpty()) {
            result += "No destroyed forces\n";
        } else {
            for (Force force : destroyedForces) {
                result += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }

        return result;
    }




    /**Returns true if force is in the United Forces Fleet(UFF), else false
     * @param ref reference of the force
     * @return a String representation of all forces in the United Forces Fleet(UFF)
     **/
    public boolean isInUFFDock(String ref) {
        for (Force force : uffDock) {
            if (force.getRef().equals(ref)) {
                return true;
            }
        }
        return false;
    }

    /**Returns a String representation of all forces in the United Forces Fleet(UFF) dock.
     * Does not include destroyed forces
     * @return a String representation of all forces in the United Forces Fleet(UFF) dock.
     **/
    public String getForcesInDock() {
        String s = "\n\n************ Forces available in UFFleet Dock ********\n";
        if (uffDock.length == 0) {
            s += "No forces in UFF dock";
        } else {
            for (Force force : uffDock) {
                s += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }
        return s;
    }


    /** Returns a list of all destroyed forces in the system
     * @return all destroyed forces   
     */
    public String getDestroyedForces() {
        String s = "\n***** Destroyed Forces ****\n";
        if (destroyedForces.isEmpty()) {
            s += "No destroyed forces\n";
        } else {
            for (Force force : destroyedForces) {
                s += "- " + force.getName() + " (" + force.getStrength() + ")\n";
            }
        }
        return s;
    }


    /** Returns details of the force with the given reference code, or "No such force" 
     * @param ref the reference of the force
     * @return details of the force with the given reference code
     **/
    public String getForceDetails(String ref) {
        for (Force force : activeStarFleet) {
            if (force.getRef().equals(ref)) {
                return force.toString();
            }
        }

        for (Force force : uffDock) {
            if (force.getRef().equals(ref)) {
                return force.toString();
            }
        }

        for (Force force : destroyedForces) {
            if (force.getRef().equals(ref)) {
                return force.toString();
            }
        }

        return "\nNo such force";
    }



    // ***************** Active Star Fleet Forces ************************
    /** Allows a force to be activated into the Active Star Fleet(ASF), but 
     * only if there are enough resources for the activation fee.The force's 
     * state is set to "active"
     * @param ref represents the reference code of the force
     * @return 0 if force is activated, 1 if force is not in the UFF dock or is destroyed
      * 2 if not enough money, -1 if no such force
     **/
    public int activateForce(String ref) {
        // Search for the force with the given reference code in the UFF dock
        Force forceToActivate = null;
        for (Force force : uffDock) {
            if (force.getReferenceCode().equals(ref)) {
                forceToActivate = force;
                break;
            }
        }

        // If the force was not found in the UFF dock, return 1
        if (forceToActivate == null) {
            return -1;
        }

        // If the force is already in the Active Star Fleet or is destroyed, return 1
        if (destroyedForces.contains(forceToActivate) || activeForces.contains(forceToActivate)) {
            return 1;
        }

        // Calculate the activation fee and check if there are enough resources
        int activationFee = forceToActivate.getActivationFee();
        if (warChest < activationFee) {
            return 2;
        }

        // Deduct the activation fee from the war chest and activate the force
        warChest -= activationFee;
        forceToActivate.setState("active");
        activeForces.add(forceToActivate);

        return 0;
    }




    /** Returns true if the force with the reference code is in 
     * the Active Star Fleet(ASF), false otherwise.
     * @param ref is the reference code of the force
     * @return returns true if the force with the reference code
     * is in the active Star Fleet(ASF), false otherwise.
     **/
    public boolean isInASFleet(String ref) {
        for (Force f : activeForces) {
            if (f.getRef().equals(ref)) {
                return true;
            }
        }
        return false;
    }


    /**Returns a String representation of the forces in the active 
     * Star Fleet(ASF), or the message "No forces activated"
     * @return a String representation of the forces in the active
     * Star Fleet, or the message "No forces activated"
     **/
    public String getASFleet()
    {
        String s = "\n****** Forces in the Active Star Fleet******\n";
        
        return s;
    }


    /** Recalls a force from the Star Fleet(ASF) back to the UFF dock, but only
     * if it is in the Active Star Fleet(ASF)
     * @param ref is the reference code of the force
     **/
    @Override
    public void recallForce(String ref) {
    }
    public void recallForce(String ref, Force force) {
        // Find the first empty slot in the uffDock array
        int emptySlot = -1;
        for (int i = 0; i < uffDock.length; i++) {
            if (uffDock[i] == null) {
                emptySlot = i;
                break;
            }
        }
        if (emptySlot != -1) {
            // Add the force to the first empty slot in the uffDock array
            uffDock[emptySlot] = force;
            // Print a message to confirm the force was recalled
            System.out.println("Force " + ref + " has been recalled to the UFF dock.");
        } else {
            System.out.println("UFF dock is full and force " + ref + " cannot be recalled.");
        }

    }




//**********************Battles************************* 
    /** returns true if the number represents a battle
     * @param num is the number of the required battle
     * @returns true if the number represents a battle
     **/
    public boolean isBattle(int num) {
        if (num >= 0 && num < battles.length) {
            return true;
        } else {
            return false;
        }
    }
    
    
    /** Provides a String representation of a battle given by 
     * the battle number
     * @param num the number of the battle
     * @return returns a String representation of a battle given by 
     * the battle number
     **/
    public String getBattle(int num) {
        if (num >= 0 && num < battles.length) {
            return battles[num].toString();
        } else {
            return "No such battle";
        }
    }
    /** Provides a String representation of all battles 
     * @return returns a String representation of all battles
     **/
    public String getAllBattles() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n************ All Battles ************\n");
        for (Battle battle : battles) {
            sb.append(battle.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
     
     
    /** Retrieves the battle represented by the battle number.Finds 
      * a force from the Active Star Fleet which can engage in the battle.The  
      * results of battle will be one of the following: 
      * 0 - Battle won, battle gains added to the war chest, 
      * 1 - Battle lost as no suitable force available, battle losses 
      * deducted from war chest 
      * 2 - Battle lost on battle strength , battle losses 
      * deducted from war chest and force destroyed
      * 3 - If a battle is lost and admiral completely defeated (no resources and 
      * no forces to recall) 
      * -1 - no such battle
      * @param battleNo is the number of the battle
      * @return an int showing the result of the battle (see above)
      */
    public int doBattle(int battleNo) {
        // Find the battle with the given number
        Battle battle = null;
        for (Battle b : battles) {
            if (b.getBattleNumber() == battleNo) {
                battle = b;
                break;
            }
        }
        if (battle == null) {
            return -1; // No such battle
        }

        // Find a force from activeStarFleet which can engage in the battle
        Force force = null;
        for (Force f : activeStarFleet) {
            if (f.canEngage(battle)) {
                force = f;
                break;
            }
        }
        if (force == null) {
            // Battle lost as no suitable force available, battle losses deducted from war chest
            warChest -= battle.getLosses();
            if (warChest <= 0 && activeStarFleet.isEmpty()) {
                defeated = true; // Admiral completely defeated
                return 3;
            }
            return 1;
        }

        // Do battle
        int battleResult = battle.doBattle(force);

        if (battleResult == 0) {
            // Battle won, battle gains added to the war chest
            warChest += battle.getGains();
        } else if (battleResult == 2) {
            // Battle lost on battle strength, battle losses deducted from war chest and force destroyed
            warChest -= battle.getLosses();
            activeStarFleet.remove(force);
            destroyedForces.add(force);
        } else {
            // Battle lost, battle losses deducted from war chest
            warChest -= battle.getLosses();
        }

        if (warChest <= 0 && activeStarFleet.isEmpty()) {
            defeated = true; // Admiral completely defeated
            return 3;
        }

        return battleResult;
    }


    //*******************************************************************************
    private void setupForces()
    {
        uffDock = new Force[5];

        uffDock[0] = new Force("USS Enterprise", 1, 1000);
        uffDock[1] = new Force("Voyager", 1, 800);
        uffDock[2] = new Force("Prometheus", 1, 1200);
        uffDock[3] = new Force("Defiant", 1, 500);
        uffDock[4] = new Force("Valiant", 1, 300);

        //Size 1 is the Starship
    }
    
    private void setupBattles()
    {
        battles = new Battle[3];

        Force borgCube = new Force("Borg Cube", 100,10);
        Force borgSphere = new Force("Borg Sphere", 75,8);
        Force dominionBattleship = new Force("Dominion Battleship", 80,9);
        Force federationFleet = new Force("federationFleet", 80,8);
        Force klingonFleet = new Force("klingonFleet", 80,8);

        battles[0] = new Battle(borgCube, federationFleet);
        battles[1] = new Battle(borgSphere, federationFleet);
        battles[2] = new Battle(dominionBattleship, klingonFleet);  //Adjust sizes & firepower
    }
    
    //**************************Add your own private methods here ***********************

   /* private Force findForceForBattle(Battle battle) {
        // This method should find a suitable force from the Active Star Fleet
        // to engage in the given battle.
        for (Force force : activeStarFleet) {
            if (force.getStrength() >= battle.getStrength()) {
                return force;
            }
        }
        return null;
    }*/

    private void updateWarChest(int amount) {
        // This method should update the war chest by adding or subtracting
        // the given amount.
        warChest += amount;
    }

    private void handleDefeatedForce(Force force) {
        // This method should handle a defeated force by removing it from
        // the Active Star Fleet and performing any other necessary actions.
        activeStarFleet.remove(force);
    }

    private Force getForce(String attackerCode) {
        return null;
    }
    
    //*******************************************************************************
  
    //These methods are not needed until Task 3.5. Uncomment them to complete task 3.5
    // ***************   file write/read  *********************

      //     /** Writes whole game to the specified file
     //      * @param fname name of file storing requests
    //      */
    public void saveGame(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     //     /** Writes whole game to the specified file
    //      * @param fname name of file storing requests
   //      */
    public static SpaceWars loadGame(String filename) {
        SpaceWars spaceWars = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            spaceWars = (SpaceWars) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("SpaceWars class not found");
            c.printStackTrace();
            return null;
        }
        return spaceWars;
    }



//     /** reads all information about the game from the specified file 
//      * and returns a SpaceWars object
//      * @param fname name of file storing the game
//      * @return the game (as a SpaceWars object)
//      */
    public SpaceWars restoreGame(String fname) {
       try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fname))) {
        SpaceWars game = (SpaceWars) ois.readObject();
        return game;
    } catch (IOException | ClassNotFoundException e) {
           System.out.println("Error loading game: " + e.getMessage());
           return null;
    }
}

//
//     /** Reads information about battles from the specified file into the appropriate collection
//      * @param the name of the file
//      */
    private void readBattles() {
       try {
           File file = new File("battles.txt");
           Scanner scanner = new Scanner(file);
           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
               String[] parts = line.split(",");
               String attackerCode = parts[0];
               String defenderCode = parts[1];
               BattleType battleType = BattleType.valueOf(parts[2].toUpperCase());
               int attackerSize = Integer.parseInt(parts[3]);
               int attackerFirepower = Integer.parseInt(parts[4]);
               int defenderSize = Integer.parseInt(parts[5]);
               int defenderFirepower = Integer.parseInt(parts[6]);
               Force attacker = getForce(attackerCode);
               Force defender = getForce(defenderCode);
               Battle battle = new Battle(attacker, defender);
               battle.getBattleType();
               attacker.updateSize(attackerSize);
               attacker.updateFirepower(attackerFirepower);
               defender.updateSize(defenderSize);
               defender.updateFirepower(defenderFirepower);
           }
           scanner.close();
       } catch (FileNotFoundException e) {
           e.printStackTrace();
     }
}
}
