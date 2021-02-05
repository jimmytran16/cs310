public class Usage{
    // get the username of the employee
    private String username;

    // get the count
    private int count;

    // Constructor
    public Usage(String username, int count){
        this.username = username;
        this.count = count;
    }

    // GETTER FUNCTIONS
    public String getUsername(){
        return username;
    }

    public int getCount(){
        return count;
    }

    // function to increment the count member
    public void addCount(){
        count+=1;
    }
}