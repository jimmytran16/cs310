import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

public class ShortestPath {

    public static void main(String args[]) {
        // get the instance of the graph
        MetroGraph2 mG = new MetroGraph2(args[0]);
        // get the metro graph object
        Graph G = mG.getGraph();
        // JFK to BOWDOIN
        final int CURRENT_LOCATION_1 = 98, DESTINATION_1 = 24;
        final int CURRENT_LOCATION_2 = 98, DESTINATION_2 = 3;

        // call the getShortestPath function to get the shortest paths of the CURRENT locations and DESTINATIONS
        System.out.println("\nFrom JFK/Umass to Bowdoin");
        System.out.println(getShortestPath(CURRENT_LOCATION_1,DESTINATION_1,G,mG));
        System.out.println("\nFrom JFK/Umass to Wonderland");
        System.out.println(getShortestPath(CURRENT_LOCATION_2,DESTINATION_2,G,mG));

    }

    // function to return the REPORT of the shortest paths of two stations 
    private static String getShortestPath(int CURRENT_LOCATION, int DESTINATION, Graph G, MetroGraph2 mG) {
        // instansiate the BreadtheFirstPath
        BreadthFirstPaths BFS = new BreadthFirstPaths(G, CURRENT_LOCATION);
        List<Platform> LIST_OF_STATIONS_TO_DESTINATION = new ArrayList<Platform>();
        StringBuilder TRAVEL_REPORT = new StringBuilder();

        // check if there is a path from JFK/UMASS to BOWDOIN,
        // if there is print out the stations
        if (BFS.hasPathTo(DESTINATION)) {
            for (int v : BFS.pathTo(DESTINATION)) {
                // populate the list with the stations to the platform
                LIST_OF_STATIONS_TO_DESTINATION.add(mG.platformOf(v));
            }
        } else {
            System.out.println("There are no current paths to the destination from your current location");
        }

        // get the first station name of the station first needed to get there
        String CURRENT_TRAIN_LINE = LIST_OF_STATIONS_TO_DESTINATION.get(0).getTrainLine();
        TRAVEL_REPORT.append(CURRENT_TRAIN_LINE + ":(");
        // print out the routes to the destination
        for (Platform platform : LIST_OF_STATIONS_TO_DESTINATION) {
            // check for the transfers of the train lines
            if (!platform.getTrainLine().equals(CURRENT_TRAIN_LINE)) {
                CURRENT_TRAIN_LINE = platform.getTrainLine();
                TRAVEL_REPORT.append(")\n" + CURRENT_TRAIN_LINE + ":(");
                TRAVEL_REPORT.append(platform.getStationName() + ",");
            } else {
                TRAVEL_REPORT.append(platform.getStationName() + ",");
            }
        }
        // close up the parenthesis of the string
        TRAVEL_REPORT.append(")");
        return TRAVEL_REPORT.toString();
    }

}
