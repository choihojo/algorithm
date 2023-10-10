import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] planets;
    static int[] minEdge;
    static boolean[] visited;
    static List<List<Vertex>> list; 
    
    static int prim() {
    	Queue<Vertex> pQueue = new PriorityQueue<>((o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
    	minEdge = new int[N];
    	visited = new boolean[N];
    	list = new ArrayList<>();
    	for (int n = 0; n < N; n++) {
    		list.add(new ArrayList<>());
    	}
        Arrays.sort(planets, (o1, o2) -> (o1[0] > o2[0] ? 1 : (o1[0] == o2[0] ? 0 : -1)));
//        for (int n = 0; n < N; n++) {
//            System.out.println(Arrays.toString(planets[n]));
//        }
        for (int n = 1; n < N; n++) {
        	list.get(planets[n - 1][3]).add(new Vertex(planets[n][3], Math.abs(planets[n][0] - planets[n - 1][0])));
        	list.get(planets[n][3]).add(new Vertex(planets[n - 1][3], Math.abs(planets[n][0] - planets[n - 1][0])));
        }
        Arrays.sort(planets, (o1, o2) -> (o1[1] > o2[1] ? 1 : (o1[1] == o2[1] ? 0 : -1)));
//        for (int n = 0; n < N; n++) {
//            System.out.println(Arrays.toString(planets[n]));
//        }
        for (int n = 1; n < N; n++) {
        	list.get(planets[n - 1][3]).add(new Vertex(planets[n][3], Math.abs(planets[n][1] - planets[n - 1][1])));
        	list.get(planets[n][3]).add(new Vertex(planets[n - 1][3], Math.abs(planets[n][1] - planets[n - 1][1])));
        }
        Arrays.sort(planets, (o1, o2) -> (o1[2] > o2[2] ? 1 : (o1[2] == o2[2] ? 0 : -1)));
//        for (int n = 0; n < N; n++) {
//            System.out.println(Arrays.toString(planets[n]));
//        }
        for (int n = 1; n < N; n++) {
        	list.get(planets[n - 1][3]).add(new Vertex(planets[n][3], Math.abs(planets[n][2] - planets[n - 1][2])));
        	list.get(planets[n][3]).add(new Vertex(planets[n - 1][3], Math.abs(planets[n][2] - planets[n - 1][2])));
        }
        
        pQueue.offer(new Vertex(0, 0));
        Vertex pVertex;
        int pTo = 0;
        int pWeight = 0;
        int sum = 0;
        int cnt = 0;
        Arrays.fill(minEdge, 2_100_000_000);
        minEdge[0] = 0;
        while (!pQueue.isEmpty()) {
        	pVertex = pQueue.poll();
        	pTo = pVertex.to;
        	pWeight = pVertex.weight;
        	
        	if (visited[pTo]) continue;
        	
        	visited[pTo] = true;
        	sum += pWeight;
        	cnt++;
        	
        	if (cnt == N) break;
        	
        	for (Vertex vertex : list.get(pTo)) {
        		if (!visited[vertex.to] && vertex.weight < minEdge[vertex.to]) {
        			pQueue.offer(vertex);
        			minEdge[vertex.to] = vertex.weight;
        		}
        	}
        }
        
    	return sum;
    }
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        planets = new int[N][4];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            planets[n][0] = Integer.parseInt(st.nextToken());
            planets[n][1] = Integer.parseInt(st.nextToken());
            planets[n][2] = Integer.parseInt(st.nextToken());
            planets[n][3] = n;
        }
        System.out.println(prim());
//        System.out.println(Arrays.toString(minEdge));
    }
}

class Vertex {
	int to;
	int weight;
	public Vertex(int to, int weight) {
		super();
		this.to = to;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Vertex [to=" + to + ", weight=" + weight + "]";
	}
}