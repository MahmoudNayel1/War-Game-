package cwk4; 
import java.io.*;
/**
 * Enumeration class BattleType - write a description of the enum class here
 * 
 * @author A.Marczyk
 * @version 02/11/2019
 */
public enum BattleType implements Serializable
{
    SKIRMISH (" Skirmish"), AMBUSH(" Ambush"), FIGHT(" Fight") ;
    private String type;
    
    private BattleType(String ty)
    {
        type = ty;
    }
    
    public String toString()
    {
        return type;
    }

    public int getValue() {
        switch(this) {
            case SKIRMISH:
                return 1;
            case AMBUSH:
                return 2;
            case FIGHT:
                return 3;
            default:
                return 0;
        }
    }

}
