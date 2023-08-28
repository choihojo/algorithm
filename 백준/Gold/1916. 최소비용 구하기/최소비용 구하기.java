import java.io.*;
import java.util.*;

// 최단경로 연습하는 문제
// 다익스트라 알고리즘은 프림 알고리즘에서 누적된 값을 넣어준다는 것만 제외하면 거의 비슷함

public class Main {
	static int N;
	static int M;
//	정점 중심 알고리즘이므로 정점의 리스트들로 이루어진 리스트를 생성함 (인덱스는 시작 정점을 의미함)
	static List<List<Vertex>> list;
//	프림에서는 minEdge 배열을 사용했다면 다익스트라에서는 minDistance 배열을 사용함 (의미적으로 누적 거리라는 것을 고려하기 위해서)
	static int[] minDistance;
	static boolean[] visited;
	
	static void dijkstra(int start, int end) {
//		방문체크, 최단경로 배열 초기화
		visited = new boolean[N + 1];
		minDistance = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			minDistance[i] = Integer.MAX_VALUE;
		}
//		우선순위 큐 생성하고(거리에 대해 오름차순이 되도록; 물론 여기서 거리라 함은 다익스트라 알고리즘이므로 누적거리를 의미함) 시작점 초기화
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>((o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
		pQueue.offer(new Vertex(start, 0));
		minDistance[start] = 0;

		while (!pQueue.isEmpty()) {
			Vertex poll = pQueue.poll();
			int pNo = poll.no;
			int pWeight = poll.weight;
			
			if (visited[pNo]) continue;
			
			visited[pNo] = true;
			
			if (pNo == end) break;
			
			for (Vertex vertex : list.get(pNo)) {
				int no = vertex.no;
				int weight = vertex.weight;
				if (!visited[no] && (pWeight + weight) < minDistance[no]) {
					minDistance[no] = (pWeight + weight);
					pQueue.offer(new Vertex(no, (pWeight + weight)));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(from).add(new Vertex(to, weight));
//			이 문제는 유향 그래프임
//			list.get(to).add(new Vertex(from, weight));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dijkstra(start, end);
		System.out.println(minDistance[end]);
	}
}

class Vertex {
	int no;
	int weight;
	public Vertex(int no, int weight) {
		super();
		this.no = no;
		this.weight = weight;
	}
}