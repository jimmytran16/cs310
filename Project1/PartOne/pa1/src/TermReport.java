// import java.util.ArrayList;
import java.util.Scanner;

public class TermReport {
    private static class LineUsageData {
        private LineUsage lineDataNode;

        // constructor, will be an instance of the LineUsage object
        LineUsageData(){
            lineDataNode = new LineUsage();
        }

        // insert the user into the LineUsage object to 
        public void insertUserToLineUsage(String username){
            lineDataNode.addObservation(username);
        }

        // get the Highest User Usage of that particalar user
        public String getHighestUserUsage() {
            Usage max_user = lineDataNode.findMaxUsage();

            if ( max_user == null ) return "NONE 0";
            return max_user.getUsername() + " " + max_user.getCount();
        }
    }

    public static void main(String args[]){

        // Use this array list to populate the line usage (LineUsage)
        LineUsageData[] LineUsageArray = new LineUsageData[500];

        Scanner scanner = new Scanner(System.in);

        // populate the LineUsageData Objects into the LineUsageData array
        for (int i = 0; i < LineUsageArray.length; i++) LineUsageArray[i] = new LineUsageData();

        // Read through the log file line by line 
        while(scanner.hasNextLine()){
            // split the string (TERMINAL LINE : USERNAME) into a string array, seperating the terminal line and the username

            String[] terminal_data = scanner.nextLine().split(" "); 
            System.out.printf("%s %s\n",terminal_data[0],terminal_data[1]);
            LineUsageArray[Integer.parseInt(terminal_data[0]) - 1].insertUserToLineUsage(terminal_data[1]);
        }

        // close the scanner
        scanner.close();

        // print out the terminal line # with the frequent user and frequency usage count
        for ( int element = 0; element < LineUsageArray.length; element++ ) System.out.printf("%d %s\n",element+1,LineUsageArray[element].getHighestUserUsage()); 
    }
    
}