import java.util.ArrayList;
import java.util.List;
import com.packages.Usage;

// A test Usage container to hold all
public class TestUsageContainer {
    private List<Usage> mainList;
    
    // constructor 
    //instansiate the list here in the constructor
    TestUsageContainer(){
        mainList = new ArrayList<Usage>(); 
    }

    // returns the list of Usage
    public List<Usage> getList(){
        return mainList;
    }

    // adds the Usage to the list
    public void addUsage(Usage user){
        mainList.add(user);
    }

}
