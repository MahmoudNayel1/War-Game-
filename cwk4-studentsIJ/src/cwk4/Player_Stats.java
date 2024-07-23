package cwk4;
public class Player_Stats {
    private int health;
    private int strength;
    private int agility;
    private int defense;

    public Player_Stats(int health, int strength, int agility, int defense) {
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.defense = defense;
    }

    public int getHealth() {

        return health;
    }

    public void setHealth(int health) {

        this.health = health;
    }

    public int getStrength() {

        return strength;
    }

    public void setStrength(int strength) {

        this.strength = strength;
    }

    public int getMaxHealth() {
        return this.health * 10;
    }

    public int getAgility() {

        return agility;
    }

    public void setAgility(int agility) {

        this.agility = agility;
    }

    public int getDefense() {

        return defense;
    }

    public void setDefense(int defense) {

        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Player Stats:\n" +
                "Health: " + health + "\n" +
                "Strength: " + strength + "\n" +
                "Agility: " + agility + "\n" +
                "Defense: " + defense;
    }

}
