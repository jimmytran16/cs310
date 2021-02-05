import edu.princeton.cs.algs4.*;

public class ShortestWPath {
    private MetroGraph2 mG;
    private int DEFAULT_WEIGHT = 1;
    private int TRANSFERS_OF_STATIONS = 3;
    private int TRANSFERS_OF_PLATFORM = 7;
    public ShortestWPath(MetroGraph2 mGraph) {
        mG = mGraph;
    }
    // function to get the edgeWeightDigraph
    public EdgeWeightedDigraph getEdgeWeightedDigraph() {
        // get the Graph from the metro graph instance
        Graph G = mG.getGraph();
        EdgeWeightedDigraph dG = new EdgeWeightedDigraph(G.V());
        // get all of the platforms from the graph
        Platform[] platforms = mG.getAllOfThePlatforms();
        // loop through the vertices of the graph
        for (int i = 1; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                if (i < j) { 
                    double THE_WEIGHT = DEFAULT_WEIGHT; 
                    if (platforms[i].getStationName().equals(platforms[j].getStationName())) { THE_WEIGHT = TRANSFERS_OF_PLATFORM; }
                    if (platforms[i].getTrainLine().equals("Silver") && platforms[j].getTrainLine().equals("Silver")) { THE_WEIGHT = TRANSFERS_OF_STATIONS; }
                    DirectedEdge e1 = new DirectedEdge(i, j, THE_WEIGHT);
                    dG.addEdge(e1);
                    DirectedEdge e2 = new DirectedEdge(j, i, THE_WEIGHT);
                    dG.addEdge(e2);
                }
            }
        }
        return dG;
    }

    public static void main(String args[]){
        // get the instance of the Metro Graph 2 and pass in the bostonmetro.csv
        MetroGraph2 mG = new MetroGraph2(args[0]);
        // instantiate the shortestPath passing in the Mg as a contructor parameter
        ShortestWPath shortestWPath = new ShortestWPath(mG);
        // print out the digraph
        System.out.println(shortestWPath.getEdgeWeightedDigraph());
    }
}