package cwk4;

public class Starship {
    private String name;
    private int shields;
    private int firepower;
    private boolean isInBattle;

    public Starship(String name, int shields, int firepower) {
        this.name = name;
        this.shields = shields;
        this.firepower = firepower;
        this.isInBattle = false; // set isInBattle to false by default
    }

    // getter method for isInBattle
    public boolean isInBattle() {
        return isInBattle;
    }

    // setter method for isInBattle
    public void setInBattle(boolean isInBattle) {
        this.isInBattle = isInBattle;
    }

    public String getName() {
        return name;
    }

    // other methods and variables
    // ...
}
