public interface LineUsageInterface {
    
    // adds the observation
    void addObservation(String username);

    // returns the Usage object for the user with the highest count
    Usage findMaxUsage();

}