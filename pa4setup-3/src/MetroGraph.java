
// For cs310 pa4 Boston metro graph 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import edu.princeton.cs.algs4.*;

public class MetroGraph {
	// The platforms, one for each vertex, i.e. app info for that vertex
	Platform[] platforms = null;
	// The graph, made available by getGraph()
	Graph G;

	public Graph getGraph() {
		return G;
	}

	// provide app info on this vertex via a Platform object
	Platform platformOf(int id) {
		if (id > 0 && id < G.V()) {
			return platforms[id];
		} else
			return null;
	}

	int countVertices(String filePath) {
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

	public MetroGraph(String filePath) {
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
	void fillGraph(String filePath) throws FileNotFoundException {
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
			//System.out.println("doing station " + tokens[1] + " id " + thisId);
			assert thisId == thisPlatformId : "bad line Id: expect ids in order in file";
			if (tokens.length > 5 && !tokens[5].isEmpty())
				lat = Double.parseDouble(tokens[5]);
			if (tokens.length > 6 && !tokens[6].isEmpty())
				lon = Double.parseDouble(tokens[6]);
			platforms[thisId] = new Platform(tokens[1], tokens[2], lat, lon, thisId);
			//System.out.println("3:"+tokens[3]+ "4:" + tokens[4]);
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
	void ckEdge(int x, int y) {
		// check edge: OK to match RedA with Red and GreenC with Green
		// since these connections allow trains to go through, no transfer
		if (!platforms[x].getStationName().equals(platforms[y].getStationName())
				&& !platforms[x].getTrainLine().substring(0, 3)
				.equals(platforms[y].getTrainLine().substring(0, 3))) {
			System.out.println("Data error: edge connects different stations on different-color lines: " 
				+ platforms[x].getStationName() + " on "
					+ platforms[x].getTrainLine() + " and "
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
	void deDup() {
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

	public static void main(String[] args) {
		MetroGraph mG = new MetroGraph(args[0]);
		Graph G = mG.getGraph();
		System.out.println(G.toString());
		System.out.println("Graph G has " + G.V() + " vertices, including unused vertex 0");
		Platform p = mG.platformOf(1);
		System.out.println("Platform 1 is for station " + p.getStationName());
	}
}
