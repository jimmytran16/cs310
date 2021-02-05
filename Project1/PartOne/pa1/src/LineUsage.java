import java.util.ArrayList;
import java.util.List;

// This class is used to keep track of the terminal line's data usage
public class LineUsage implements LineUsageInterface{

    // list used to save the data of the users in that particular terminal line
    private List<Usage> usageList; 

    // constructor, instanciate the usageList
    public LineUsage(){
        usageList = new ArrayList<Usage>();
    }

    // adds the observation of the user that is using it
    public void addObservation(String username){

        // check if user exist currently, if true, then call addCountToUser()
        if(checkIfUserExist(username)){ 
            addCountToUser(username);
        }
        // if not, then create a new instance of the Usage and add it to the list
        else{ 
            Usage newUser = new Usage(username,1);
            usageList.add(newUser);
        }
    }

    // Usage funtion that returns the Usage object for the user with the highest count
    public Usage findMaxUsage(){

        // check to see if there are any usage at all for that terminal line
        if (usageList.isEmpty()) return null;

        //iterate through the list to check which user used the terminal line the most
        Usage MAX_USAGE = usageList.get(0);

        // loop through the usageList to get the max usage
        for ( int i = 0; i < usageList.size(); i++ ){
            if (MAX_USAGE.getCount() < usageList.get(i).getCount()) {
                MAX_USAGE = usageList.get(i);
            }
        }

        return MAX_USAGE;

    }

    // boolean function to check if the user exists in the usageList
    private boolean checkIfUserExist(String user) { 
        for( Usage u : usageList ){ //iterate through the Usage list
            if ( u.getUsername().equals(user) ) { return true; }
        }
        return false;
    }

    // void function to add a count to the user
    private void addCountToUser(String username){
        for ( int i = 0; i < usageList.size(); i++ ) {
            if ( usageList.get(i).getUsername().equals(username) ){
                usageList.get(i).addCount();
                return;
            }
        }
    }
}
