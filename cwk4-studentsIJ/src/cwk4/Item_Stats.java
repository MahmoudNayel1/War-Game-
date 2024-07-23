package cwk4;
public class Item_Stats {
    private int bonusHealth;
    private int bonusStrength;
    private int bonusAgility;
    private int bonusDefense;

    public Item_Stats(int bonusHealth, int bonusStrength, int bonusAgility, int bonusDefense) {
        this.bonusHealth = bonusHealth;
        this.bonusStrength = bonusStrength;
        this.bonusAgility = bonusAgility;
        this.bonusDefense = bonusDefense;
    }

    public int getBonusHealth() {
        return bonusHealth;
    }

    public void setBonusHealth(int bonusHealth) {
        this.bonusHealth = bonusHealth;
    }

    public int getBonusStrength() {
        return bonusStrength;
    }

    public void setBonusStrength(int bonusStrength) {
        this.bonusStrength = bonusStrength;
    }

    public int getBonusAgility() {
        return bonusAgility;
    }

    public void setBonusAgility(int bonusAgility) {
        this.bonusAgility = bonusAgility;
    }

    public int getBonusDefense() {
        return bonusDefense;
    }

    public void setBonusDefense(int bonusDefense) {
        this.bonusDefense = bonusDefense;
    }

    @Override
    public String toString() {
        return "Item Stats:\n" +
                "Bonus Health: " + bonusHealth + "\n" +
                "Bonus Strength: " + bonusStrength + "\n" +
                "Bonus Agility: " + bonusAgility + "\n" +
                "Bonus Defense: " + bonusDefense;
    }

}
