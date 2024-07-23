package cwk4;

public class Force {
    private String referenceCode;
    private int strength;
    private int size;
    private int firepower;
    private boolean active;
    private String name;
    private String ref;
    private ForceType forceType;
    private BattleType battleType;

    public Force(String referenceCode, int size, int firepower) {
        this.referenceCode = referenceCode;
        this.size = size;
        this.firepower = firepower;
        this.active = false;
        this.name = name;
    }

    public String getReferenceCode() {

        return referenceCode;
    }

    public int getSize() {

        return size;
    }

    public int getFirepower() {

        return firepower;
    }

    public int getStrength() {
        // calculate and return the strength of the force
        return size * firepower;
    }

    public boolean isActive() {

        return active;
    }

    public void activate() {

        active = true;
    }

    public void deactivate() {

        active = false;
    }

    public void updateSize(int newSize) {

        size = newSize;
    }

    public void updateFirepower(int newFirepower) {

        firepower = newFirepower;
    }

    public String getName() {

        return name;
    }

    public String getRef() {

        return this.ref;
    }

    public int getActivationFee() {

        return 0;
    }

    public enum ForceType {
        LAND(1), AIR(2), SEA(3), SPACE(4);

        private int value;

        private ForceType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public void setState(String state) {
        this.active = true;
    }

    public ForceType getForceType() {
        return forceType;
    }

    public Force(ForceType forceType) {

        this.forceType = forceType;
    }

    public int getBattleStrength() {
        int forceTypeValue = forceType.getValue();
        int battleTypeValue = battleType.getValue();
        return forceTypeValue * battleTypeValue;
    }


    public boolean canEngage(Battle battle) {
        if (!this.getForceType().toString().equals(battle.getBattleType().toString())) {
            return false;
        }
        
        if (this.getBattleStrength() < battle.getBattleStrength()) {
            return false;
        }
        return true;
    }

}

