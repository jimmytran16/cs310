import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class n7 {
  public static void main(String args[]) {
    // init a hashset
    Set<String> hashSet = new HashSet<String>();
    // init a TreeMap
    Set<String> treeSet = new TreeSet<String>();

    // add items to hashset and treemap
    hashSet.add("apple");
    hashSet.add("pear");

    treeSet.add("apple");
    treeSet.add("pear");

    // print out the output of the equals() function
    System.out.println("EQUALS CHECK: "+hashSet.equals(treeSet));
    treeSet.add("pear1");
    System.out.println("AFTER additional element is added in tree: "+hashSet.equals(treeSet));

  }
}
