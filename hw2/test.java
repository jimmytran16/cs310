import java.util.Stack;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class test{
    public static void main(String args[]){
        
        
        // scanner is the object that reads in the lines from the standard input
        // Scanner scanner = new Scanner(System.in);
        // instansiate a Set
        // Set<String> setOfWords = new HashSet<String>();
        // scan through the Standard input and add each word to the setOfWords set


        // create a hashMap to contain the words
        Map<String,Integer> mapOfWords = new HashMap<String,Integer>();
        // create a scanner
        Scanner scanner = new Scanner(System.in);
        // iterate through the standard input and reading in the words line by line
        while(scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split(" ");
            for(int i = 0; i < line.length; i++) {
                // if the word is already contained in the hash map then..
                // add 1 to its value
                if (mapOfWords.containsKey(line[i])) {  
                    mapOfWords.put(line[i], mapOfWords.get(line[i]) + 1);
                }else{
                // if a new word, then add to hash map and give the value to 1
                    mapOfWords.put(line[i], 1);
                }
            }          
        }
        // print out the words in the hashmap with the occuracnes
        for (Map.Entry<String, Integer> entry : mapOfWords.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " "+ value);
        }
        // close the scanner
        scanner.close();



        

          // iterate through the set and display the words
        // for(String data: setOfWords) {
        //     System.out.println(data);
        // }
        // stacktest.push("test1");
        // stacktest.push("test2");s
        // stacktest.push("test3");
        // stacktest.push("test4");
        // stacktest.push("test5");



        
    }
}

