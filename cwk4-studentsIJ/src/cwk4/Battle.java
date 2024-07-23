package cwk4;

public class Battle {
    private Force attacker;
    private Force defender;
    private boolean resolved;
    int battleNumber;
    int battleStrength;
    public BattleType battleType;
    private Force[] ForceState;


    public Battle(Force attacker, Force defender) {
        this.attacker = attacker;
        this.defender = defender;
        this.resolved = false;
    }

    public Force getAttacker() {

        return attacker;
    }

    public Force getDefender() {

        return defender;
    }

    public boolean isResolved() {

        return resolved;
    }

    public void resolve() {
        if (resolved) {
            return;
        }

        int attackPower = attacker.getSize() * attacker.getFirepower();
        int defensePower = defender.getSize() * defender.getFirepower();

        if( attackPower > defensePower) {
            int newSize = attacker.getSize() - defender.getSize();
            attacker.updateSize(newSize);
            resolved = true;
        } else {
            int newSize = defender.getSize() - attacker.getSize();
            defender.updateSize(newSize);
            resolved = true;
        }
    }

    public int getBattleNumber() {

        return battleNumber;
    }

    public int getBattleStrength() {

        return battleStrength;
    }

    public enum BattleType {
        LAND(1), SEA(2), AIR(3);

        private int value;

        BattleType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public int getGains() {
        int gains = 0;
        for (Force f : ForceState) {
            if (f.isActive()) {
                gains += f.getStrength();
            }
        }
        return gains;
    }


    public int getLosses() {
        int losses = 0;
        for (Force f : ForceState) {
            if (!f.isActive()) {
                losses += f.getSize();
            }
        }
        return losses;
    }


    public BattleType getBattleType() {
        return battleType;
    }


    public int doBattle(Force force) {
        if (force.getBattleStrength() > battleStrength) {
            return 2; // Battle lost on battle strength
        } else {
            return 0; // Battle won
        }
    }

}
