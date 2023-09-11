import java.io.*;
import java.util.*;

// 1. 크루스칼 알고리즘을 이용한 풀이
// 2. 프림 알고리즘을 이용한 풀이
// 두 가지로 풀어볼 예정

// 1. 크루스칼 알고리즘을 이용한 풀이
public class Main {
	static int N;
	static int M;
	static List<Edge> list;
	static int min;
	static int cnt;
	static int[] parents;
	
	static void make() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return false;
		parents[rootB] = rootA;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.add(new Edge(from, to, weight));
			list.add(new Edge(to, from, weight));
		}
		Collections.sort(list, (o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
		make();
		for (Edge edge : list) {
//			마을을 분리하는 경우가 아니었다면 (N - 1)이었겠지만 두 마을로 분리하는 경우므로 (N - 2)로 수행
//			오름차순으로 정렬했기 때문에 가장 마지막에서 한 번만 덜 탐색하면 됨
//			이 조건문이 아래에 있는 게 아니라 위에 있어야 마을이 두 개만 있는 경우를 고려함
			if (cnt == (N - 2)) break;
			
			int from = edge.from;
			int to = edge.to;
			int weight = edge.weight;
			if (union(from, to)) {
				min += weight;
				cnt++;
			}
		}
		System.out.println(min);
	}
}

class Edge {
	int from;
	int to;
	int weight;
	public Edge(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}


// 2. 프림 알고리즘을 이용한 풀이
//public class Main {
//	static int N;
//	static int M;
//	static List<List<Vertex>> list;
//	static int min;
//	static boolean[] visited;
//	static int[] minEdge;
//	static int cnt;
//	
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		N = Integer.parseInt(st.nextToken());
//		M = Integer.parseInt(st.nextToken());
//		visited = new boolean[N + 1];
//		minEdge = new int[N + 1];
//		for (int i = 1; i <= N; i++) {
//			minEdge[i] = Integer.MAX_VALUE;
//		}
//		minEdge[1] = 0;
//		list = new ArrayList<>();
//		for (int i = 0; i <= N; i++) {
//			list.add(new ArrayList<>());
//		}
//		for (int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int from = Integer.parseInt(st.nextToken());
//			int to = Integer.parseInt(st.nextToken());
//			int weight = Integer.parseInt(st.nextToken());
//			list.get(from).add(new Vertex(to, weight));
//			list.get(to).add(new Vertex(from, weight));
//		}
//		Queue<Vertex> pQueue = new PriorityQueue<>((o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
//		pQueue.offer(new Vertex(1, 0));
//		Vertex poll;
//		int pTo = 0;
//		int pWeight = 0;
//		while (!pQueue.isEmpty()) {
//			poll = pQueue.poll();
//			pTo = poll.to;
//			pWeight = poll.weight;
//			
//			if (visited[pTo]) continue;
//			
//			visited[pTo] = true;
//			min += pWeight;
//			cnt++;
//			
//			if (cnt == N) break;
//			
//			for (Vertex vertex : list.get(pTo)) {
//				if (!visited[vertex.to] && (minEdge[vertex.to] > vertex.weight)) {
//					pQueue.offer(vertex);
//					minEdge[vertex.to] = vertex.weight; 
//				}
//			}
//		}
////		두 마을로 분리할 때 가장 큰 간선을 제거하기 위한 코드
////		크루스칼 알고리즘은 오름차순이라 그냥 마지막 탐색을 안 하면 그만인데 프림은 오름차순이 아니기 때문에 탐색을 끝낸 뒤 가장 큰 간선을 제거하는 식으로 해야 함
//		int max = Integer.MIN_VALUE;
//		for (int i = 1; i <= N; i++) {
//			if (minEdge[i] > max) max = minEdge[i];
//		}
//		System.out.println(min - max);
//	}
//}
//
//class Vertex {
//	int to;
//	int weight;
//	public Vertex(int to, int weight) {
//		super();
//		this.to = to;
//		this.weight = weight;
//	}
//}