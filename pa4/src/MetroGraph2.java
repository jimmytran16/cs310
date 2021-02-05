
// For cs310 pa4 Boston metro graph 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.lang.StringBuilder;
import edu.princeton.cs.algs4.*;

public class MetroGraph2 {
    // The platforms, one for each vertex, i.e. app info for that vertex
    private Platform[] platforms = null;
    // The graph, made available by getGraph()
    private Graph G;

    public Graph getGraph() {
        return G;
    }

    public Platform[] getAllOfThePlatforms() {
        return this.platforms;
    }
    // provide app info on this vertex via a Platform object
    public Platform platformOf(int id) {
        if (id > 0 && id < G.V()) {
            return platforms[id];
        } else
            return null;
    }

    private int countVertices(String filePath) {
        int nV = 1; // one for spot 0, not used
        try {
            Scanner in = null;
            in = new Scanner(new File(filePath));
            while (in.hasNextLine()) {
                nV++;
                in.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            return 0;
        }
        return nV;
    }

    public MetroGraph2(String filePath) {
        int nV = countVertices(filePath);
        G = new Graph(nV);
        platforms = new Platform[nV];
        // handle file-not-found here, for convenience of caller
        // (like SymbolGraph in S&W)
        try {
            System.out.println("calling fillGraph...");
            fillGraph(filePath); // including fake vertex 0
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            return;
        }
        // connect the vertices for one station all together
        // with edges for transfers
        Set<String> stations = new HashSet<String>();
        for (int i = 1; i < nV; i++)
            stations.add(platforms[i].getStationName());
        for (String s : stations) {
            // Find all vertices for station
            Set<Integer> ids = new HashSet<Integer>();
            for (int i = 1; i < nV; i++) {
                if (platforms[i].getStationName().equals(s))
                    ids.add(i);
            }
            if (ids.size() > 1) {
                // case of multiple platforms for a station: link together
                // to represent possible connections for riders to take
                for (int i : ids)
                    for (int j : ids)
                        if (i < j)
                            G.addEdge(i, j);
            }
        }
        System.out.println("orig graph #edges =" + G.E());
        deDup();
        System.out.println("after dedup, graph #edges =" + G.E());
    }

    // Helper to constructor: fill platform array
    // while building graph from file data
    private void fillGraph(String filePath) throws FileNotFoundException {
        Scanner in = null;
        in = new Scanner(new File(filePath));
        // placeholder for spot 0
        platforms[0] = new Platform("fake vertex", " ", 0, 0, 0);

        int platformId = 1;
        while (in.hasNextLine()) {
            double lat = 0.0, lon = 0.0;
            String line1 = in.nextLine();
            String[] tokens = line1.split(",");
            int thisId = Integer.parseInt(tokens[0]);
            int thisPlatformId = platformId++;
            // System.out.println("doing station " + tokens[1] + " id " + thisId);
            assert thisId == thisPlatformId : "bad line Id: expect ids in order in file";
            if (tokens.length > 5 && !tokens[5].isEmpty())
                lat = Double.parseDouble(tokens[5]);
            if (tokens.length > 6 && !tokens[6].isEmpty())
                lon = Double.parseDouble(tokens[6]);
            platforms[thisId] = new Platform(tokens[1], tokens[2], lat, lon, thisId);
            // System.out.println("3:"+tokens[3]+ "4:" + tokens[4]);
            int neighborId1 = Integer.parseInt(tokens[3]);
            int neighborId2 = Integer.parseInt(tokens[4]);
            if (neighborId1 > 0)
                G.addEdge(thisId, neighborId1);
            if (neighborId2 > 0)
                G.addEdge(thisId, neighborId2);
        }
        in.close();
    }

    // Check that an edge between x and y would be reasonable.
    // Edges should be between platforms on the same-color trainLine or
    // platforms at the same station, so check if neither is true
    // Note this allow a RedA station to connect directly to a Red station
    // because that's just following a split, no rider transfer needed.
    // The first three characters of a trainLine String give the trainline
    // grouping: Red, Gre, Blu, Ora, Sil i.e. the trainline color
    private void ckEdge(int x, int y) {
        // check edge: OK to match RedA with Red and GreenC with Green
        // since these connections allow trains to go through, no transfer
        if (!platforms[x].getStationName().equals(platforms[y].getStationName())
                && !platforms[x].getTrainLine().substring(0, 3).equals(platforms[y].getTrainLine().substring(0, 3))) {
            System.out.println("Data error: edge connects different stations on different-color lines: "
                    + platforms[x].getStationName() + " on " + platforms[x].getTrainLine() + " and "
                    + platforms[y].getStationName() + " on " + platforms[y].getTrainLine());
        }
    }

    // named SimpleEdge to be different from Edge of book
    // edge for undirected graph: SimpleEdge(x,y) and SimpleEdge<y,x) are .equals
    private class SimpleEdge {
        int x, y;

        SimpleEdge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object other) {
            // code on pg. 103, adapted
            if (this == other)
                return true;
            if (other == null)
                return false;
            if (this.getClass() != other.getClass())
                return false;
            SimpleEdge o = (SimpleEdge) other;
            return x == o.x && y == o.y || x == o.y && y == o.x;
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(x).hashCode() & Integer.valueOf(y).hashCode();
        }

    }

    // de-duplicate edges by using a Set of undirected edges
    // Rebuild G using dedup'd edges
    private void deDup() {
        Set<SimpleEdge> edges = new HashSet<SimpleEdge>();
        for (int i = 1; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                if (i != j) {
                    edges.add(new SimpleEdge(i, j));
                } else {
                    System.out.println("Unexpected i<->i edge, i = " + i);
                }
            }
        }
        G = new Graph(G.V()); // recreate it
        for (SimpleEdge e : edges) {
            ckEdge(e.x, e.y);
            G.addEdge(e.x, e.y);
        }
    }

    private int getFirstTrainStationNextVertex(int VERTEX) {
        Iterable<Integer> VERTICES = G.adj(VERTEX);
        int[] vertices_arr = new int[2];
        int i = 0;
        for (int vert : VERTICES) {
            vertices_arr[i++] = vert;
        }
        // if the vertex is 0 then return the other one that points to the next station
        return vertices_arr[0] == 0 ? vertices_arr[1] : vertices_arr[0];
    }

    private int findVertexOfStation(Platform p) {
        for (int i = 1; i <= 148; i++) {
            Platform trainStation = platformOf(i);
            if (trainStation.getStationName().equals(p.getStationName())) {
                System.out.println("FOUND VERTEX!!! " + i);
                return i;
            }
        }
        System.out.println("DIDNT FIND");
        return 0;
    }

    // function to return the list of vertex that belongs to the station line
    private Set<Integer> getListOfVertexForStationLine(String line) {
        System.out.println(line);
        Set<Integer> setOfVertex = new HashSet<Integer>();
        for (int i = 1; i <= 148; i++) {
            Platform trainStation = platformOf(i);
            if (trainStation.getTrainLine().equals(line)) {
                setOfVertex.add(i);
            }
        }
        return setOfVertex;
    }

    // will print out all the stations on the line that is passed onto the parameter
    // find all the orange lines and put it inside the set
    public String printTrainLine(String line) {
        Platform firstStation;
        int VERTEX = 0;
        StringBuilder trainLineBuilder = new StringBuilder();
        // keep track of the previous vertex
        int PREV_VERTEX = 0;
        Set<Integer> SET_OF_VERTEX = new HashSet<Integer>(getListOfVertexForStationLine(line));
        if (line.equals(line)) {
            // get the furthest end of the station on the line
            firstStation = endOfLine(line);
            PREV_VERTEX = findVertexOfStation(firstStation);
            VERTEX = getFirstTrainStationNextVertex(PREV_VERTEX);
            trainLineBuilder.append(firstStation.getStationName() + ", ");


            System.out.println(SET_OF_VERTEX);

            boolean keepGoing = true;

            while (keepGoing) {
                Iterable<Integer> adj_vertices = G.adj(VERTEX);
                Platform currentStation = platformOf(VERTEX);
                boolean FOUND = false;

                // add the station to the list
                // stationByLineList.add(currentStation.getStationName());
                trainLineBuilder.append(currentStation.getStationName() + ", ");
                ArrayList<Integer> VERTICES_ARR = new ArrayList<Integer>();
                // int[] VERTICES_ARR = new int[2];
                // int index = 0;

                // populate the vertex into the ARRAY
                for (int V : adj_vertices) {
                    System.out.println(currentStation.getStationName() + "......" + V);
                    VERTICES_ARR.add(V);

                    // check for the next train station (VERTEX)
                    if(V != PREV_VERTEX && SET_OF_VERTEX.contains(V) && V != 0) {
                        PREV_VERTEX = VERTEX;
                        FOUND = true;
                        VERTEX = V;
                        break;
                    }
                }

                System.out.println(VERTICES_ARR.size());
                // if it is not found, that means that it is at its last station on the line
                if (!FOUND) {
                    keepGoing = false;
                }
            }
        trainLineBuilder.setLength(trainLineBuilder.length() - 2);
        return line + ": " + trainLineBuilder.toString();

        } else {
            System.out.println("Invalid ENTRY!");
            return "Invalid";
        }
    }

    // func to get all of the train lines with their stations and store it inside a SET
    public Set<String> getTrainLineList() {
        Set<String> trainList = new HashSet<String>();
        for (int i = 1; i <= 148; i++) {
            Platform platform = platformOf(i);
            trainList.add(platform.getTrainLine());
        }
        return trainList;
    }

    // GET ALL POSSIBLE TRAIN LINES 
    public Set<String> findAllTrainLines() {
        Set<String> trainLines = new TreeSet<String>();
        Set<String> trainLists = getTrainLineList();
        for(String lines : trainLists) {
            trainLines.add(printTrainLine(lines));
        }
        return trainLines;
    }

    // FIND the last STATION that is the furthest from BOSTON
    private Platform endOfLine(String trainLine) {
        Platform FURTHEST_STATION = platformOf(0);
        double DISTANCE = 0;
        // iterate through all of the platforms
        for (int i = 1; i <=148; i++) {
            Platform CURRENT_PLATFORM = platformOf(i);
            // System.out.println(trainLine + " EQUaLS? " + CURRENT_PLATFORM.getStationName().toLowerCase());
            if(CURRENT_PLATFORM.getTrainLine().equals(trainLine)) {
                // check if the current platform has a greater distance from boston as the current one
                if (CURRENT_PLATFORM.distanceFromBoston() > DISTANCE) {
                    // reassign the furthest station, reassign the longest distance
                    FURTHEST_STATION = CURRENT_PLATFORM;
                    DISTANCE = CURRENT_PLATFORM.distanceFromBoston();
                }
            }
        }
        return FURTHEST_STATION;
    }

    public static void main(String[] args) {
        MetroGraph2 mG = new MetroGraph2(args[0]);

        // print out all of the train liens and the stations within those lines
        Set<String> trainLines = mG.findAllTrainLines();
        for (String lines: trainLines) {
            System.out.println(lines);
        }
    }
}

