package cs310;

import java.util.Set;
import java.util.TreeSet;

import java.util.Arrays;
import java.util.Scanner;

import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestTokenizer {

    // method to get a set of indentifiers to store into the Set<String> such as
    // "System in 20"
    public Set<String> executeTokenizer(JavaTokenizer tok) {
        String current;
        Set<String> tokSet = new TreeSet<String>();
        // loop through the stream until end of file,
        // .. populating the ids with line number into the TreeSet
        while ((current = tok.getNextID()) != "") {
            tokSet.add(current + " in " + tok.getLineNumber());
        }
        return tokSet; // returns the TreeSet of of tokenizer
    }

    // method to check if the tokenizers both have the same indentifiers inside of them
    public void checkIfTokenizerSetsMatch(JavaTokenizer wtok, JavaTokenizer regTok) {
        // execute the two tokenizer to get the Sets returned back with indentifier data
        Set<String> testRegexTree = executeTokenizer(wtok);
        Set<String> testWtokTree = executeTokenizer(regTok);

        // make a copy of the Wtokenizer
        Set<String> wtokenizer_copy = new TreeSet<String>(testWtokTree);

        // print out the two TreeSets
        printOutSets(testWtokTree,testRegexTree);

        // remove from A-B and B-A to check if they are equals
        wtokenizer_copy.removeAll(new TreeSet<String>(testRegexTree));
        testRegexTree.removeAll(testWtokTree);

        System.out.println("BOTH OF THE LIST AFTER REMOVEDALL() -- \n REGEXTOKENIZER-" + testRegexTree + "\n WTOKENIZER-" + wtokenizer_copy);
        // check if both sets are removed , and are empty, that means that they are both equal
        System.out.print("-------------------------------\n| RESULT: ");
        if(testRegexTree.isEmpty() && wtokenizer_copy.isEmpty()) { 
            System.out.println("THEY ARE BOTH EQUAL |");
        }else{
            System.out.println("THEY ARE NOT EQUAL |");
        }
        System.out.println("-------------------------------");
    }

    private void printOutSets(Set<String> wTok, Set<String> rTok) {
        System.out.println("\nWTOKENIZER RESULT: \n"+wTok);
        System.out.println("\nREGEXTOKENIZER RESULT: \n"+ rTok + "\n");
    }

    public static void main(String args[]) {
        
        if (args.length != 0) { 
            // init two instances of a FileReader to get two copies of the file content
            FileReader regFile = null;
            FileReader wtokFile = null;
            try {
                // get the files from the command line arguments
                regFile = new FileReader(args[0]);
                wtokFile = new FileReader(args[0]);

                // init a test tokenizer instance
                TestTokenizer testTokenizer = new TestTokenizer();

                // init 2 instances of the 2 tokenizer competitors, passing in the file instance
                JavaTokenizer testRegex = new RegexTokenizer(regFile);
                JavaTokenizer testWtok = new WTokenizer(wtokFile);

                // pass the two tokenizers to the function to check if they are equal
                testTokenizer.checkIfTokenizerSetsMatch(testWtok, testRegex);

            } catch (IOException e) {
                System.err.println(e + args[0]);
            } finally {
                // close the files
                try {
                    if (regFile != null)
                        regFile.close();
                    if (wtokFile != null)
                        wtokFile.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }else {
            System.out.println("Please pass in the file as a command line argument! example: java cs310.TestTokenizer <inputfile>");
            return;
        }
    }
}
