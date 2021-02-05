import java.util.*;


public class seven {
  public static void main (String args[]) {

    // initialized a datastructure
    Map<Integer, Set<Integer>> DS = new HashMap<Integer, Set<Integer>>();
    // get all the clients
    Set<Integer>  clients = webIF.getClients();
    // iterate through the clients set and populate the clients into the dataStructure map..
    // while getting their preferences and store it as a value
    for (int client: clients) {
      // store the client (client id )as a KEY, and use the getPrefs() function..
      // to get the set of preferences for the client and store as VALUE
      DS.put(client,webIF.getPrefs(client));
    }

    Set<Integer>  prefs = webIF.getPrefs(id);

 
    public int findBestMatch(int clientId) {
      // get the client Preferences by the clientId from the datastructure
      // will return a clientsPref Set<Integer>
      Set<Integer> clientsPref = dataStructure.get(clientId);
      // get the match id
      int matchedId = 0;
      // variable to get the total match
      int TOTAL_MATCH = 0;

      // iterate through the map, and go through all of the clients preferences ..
      // and comparing them to the current client
       for (Map.Entry<Integer,Set<Integer>> entry : dataStructure.entrySet())  {
         int TOTAL_MATCH_NOW = 0;
        // iterate through clients prefs
         for(int prefs: clientsPref) {
           // iterate through current clients prefs
           for(int currentPrefs: entry.getValue()) {
             if (currentPrefs == prefs) {
               TOTAL_MATCH_NOW++;
             }
           }
         }
         // if the total match is greater than previous than assign new client matchId
         if(TOTAL_MATCH_NOW > TOTAL_MATCH ) {
           matchedId = entry.getKey();
           TOTAL_MATCH = 0;
         }
       }
      return matchId; //return the matched ID
    }

  }
}
