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
//    	행성의 개수가 최대 10만이기 때문에 모든 간선을 고려하고 MST 알고리즘을 수행하면 반드시 시간 초과가 발생함
//    	그래서 행성 간 연결 비용이 min(|x1-x2|, |y1-y2|, |z1-z2|)임을 이용하여 간선 수를 줄여야 할 필요가 있음
//    	3차원이 아니라 1차원만 존재한다고 가정하겠음
//    	그럴 경우 행성 간 연결 비용은 |x1-x2|로 계산할 수 있음
//    	근데 행성 좌표를 x에 대해 정렬해서 x1, x2, x3, ... 이렇게 있는 상태라면 x2과 가장 가까운 행성은 x1 혹은 x3임
//    	굳이 |x2-x4|, |x2-x5|, ...를 고려할 필요는 없는 것임
//    	오름차순이든 내림차순이든 정렬한 뒤에 인접한 행성끼리의 거리만 간선으로 간주하면 됨 (물론 1차원의 경우 정렬한 상태에서 그대로 이어주면 그게 그대로 답임; 그렇지만 문제처럼 3차원인 경우 여러 가지 경우가 있기 때문에 MST 알고리즘을 수행해야 함)
//    	이제 3차원으로 돌아와서 생각해보면 행성을 좌표 x에 대해 정렬, y에 대해 정렬, z에 대해 정렬한 후 각각 (N-1)개의 간선씩, 총 3*(N-1)개의 간선만 고려하면 됨
//    	참고로 여기서 과연 이렇게 하면 반드시 MST를 만들 수 있는지 보장할 수 있냐는 의문이 들 수 있음
//    	직관적으로 생각해보면 1차원에 대해서만 생각했을 때 x에 대해 정렬한 것만으로도 MST를 구성할 수 있음 (x에 대해 정렬한 후 선택한 (N-1)개 간선을 모두 선택함)
//    	이건 다른 말로 그 어떤 경우에도 뽑은 3*(N-1)개의 간선만으로 모든 행성을 연결할 수 있다는 것임
//    	그럼 이제 3*(N-1)개의 간선이 최소 비용을 반드시 뽑을 수 있는지 보면 됨
//    	애초에 3*(N-1)개의 간선을 뽑을 때 정렬한 뒤 가장 낮은 것부터 순서대로 (N-1)개의 간선을 뽑아왔기 때문에 3*(N-1)개의 간선만으로 최소 비용을 보장할 수 있음
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
        
//        프림 알고리즘
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