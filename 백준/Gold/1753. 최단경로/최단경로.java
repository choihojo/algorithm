import java.io.*;
import java.util.*;

// 시작점에서 다른 모든 정점으로의 최단 경로 -> 기본적인 다익스트라 연습문제
// 모든 가중치는 10 이하의 자연수
// 정점 번호는 1 ~ V번

public class Main {
	static int V, E;
//	시작 정점
	static int K;
	static List<List<Vertex>> list;
	static int[] minDistance;
	static boolean[] visited;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		V = Integer.parseInt(srr[0]);
		E = Integer.parseInt(srr[1]);
		K = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
//		인덱싱 편하게 1 크게 설정함
		for (int i = 0; i <= V; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			srr = br.readLine().split(" ");
			int from = Integer.parseInt(srr[0]);
			int to = Integer.parseInt(srr[1]);
			int weight = Integer.parseInt(srr[2]);
			list.get(from).add(new Vertex(to, weight));
		}
		
		visited = new boolean[V + 1];
		minDistance = new int[V + 1];
		for (int i = 0; i <= V; i++) {
			minDistance[i] = Integer.MAX_VALUE;
		}
		minDistance[K] = 0;
		
		Queue<Vertex> pQueue = new PriorityQueue<>((o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
		pQueue.offer(new Vertex(K, 0));
		Vertex poll;
		int pNo;
		int pWeight;
		
		while (!pQueue.isEmpty()) {
			poll = pQueue.poll();
			pNo = poll.no;
			pWeight = poll.weight;
			
			if (visited[pNo]) continue;
			if (cnt == V) break;
			
			visited[pNo] = true;
			cnt++;
			
			for (Vertex vertex : list.get(pNo)) {
				if (visited[vertex.no]) continue;
				if (minDistance[vertex.no] > (pWeight + vertex.weight)) {
					minDistance[vertex.no] = (pWeight + vertex.weight);
					pQueue.offer(new Vertex(vertex.no, (pWeight + vertex.weight)));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (minDistance[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(minDistance[i]).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
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