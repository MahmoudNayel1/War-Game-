package cwk4; 


/**
 * Details of your team
 * 
 * @author (Karim Ihab)
 * @version (28/4/2023)
 */
public class Teamwork
{
    private String[] details = new String[12];
    
    public Teamwork()
    {   // in each line replace the contents of the String 
        // with the details of your team member
        // Please list the member details alphabetically by surname 
        // i.e. the surname of member1 should come alphabetically 
        // before the surname of member 2...etc
        details[0] = "team number: 2";
        
        details[1] = "Ihab";
        details[2] = "Karim";
        details[3] = "20045521";

        details[4] = "Mohamed";
        details[5] = "Mahmoud";
        details[6] = "20045532";

        details[7] = "Ramadan";
        details[8] = "Asmaa";
        details[9] = "20022552";


        details[10] = "Sameh";
        details[11] = "Mina";
        details[12] = "20022556";

    }
    
    public String[] getTeamDetails()
    {
        return details;
    }
    
    public void displayDetails()
    {
        for(String temp:details)
        {
            System.out.println(temp.toString());
        }
    }
}
        
