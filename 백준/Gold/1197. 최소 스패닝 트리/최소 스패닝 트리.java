import java.io.*;
import java.util.*;

// 기본적인 MST 문제
// 이번엔 크루스칼 알고리즘(간선 중심)이 아니라 프림 알고리즘(정점 중심)으로 풀어봄

public class Main {
	static int V, E;
	static int A, B, C;
	
	static boolean[] visited;
	static int[] minEdge;
	static List<List<Vertex>> adjList;
	static long sum;
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		V = Integer.parseInt(srr[0]);
		E = Integer.parseInt(srr[1]);
		
		adjList = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			adjList.add(new ArrayList<>());
		}
		
//		양방향이므로 그에 맞게 초기화
		for (int i = 0; i < E; i++) {
			srr = br.readLine().split(" ");
			A = Integer.parseInt(srr[0]);
			B = Integer.parseInt(srr[1]);
			C = Integer.parseInt(srr[2]);
			adjList.get(A).add(new Vertex(B, C));
			adjList.get(B).add(new Vertex(A, C));
		}
		
//		인덱싱 편하게 1 크게 초기화
		minEdge = new int[V + 1];
//		minEdge 배열은 시작과 동시에 최대 정수값으로 초기화
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		visited = new boolean[V + 1];
//		시작점인 1의 간선 비용은 0으로 초기화
		minEdge[1] = 0;
		
//		낮은 비용의 간선부터 뽑도록 오름차순으로 정렬
		Queue<Vertex> pQueue = new PriorityQueue<>((o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
		pQueue.add(new Vertex(1, 0));
		Vertex poll;
		int pNo;
//		int pWeight;
		
		while (!pQueue.isEmpty()) {
			poll = pQueue.poll();
			pNo = poll.no;
//			pWeight = poll.weight;
			
//			이거 안 해도 답은 나올텐데 그럼 시간초과 나는지 확인해보자
//			if (visited[pNo]) continue;
			
			visited[pNo] = true;
//			sum += pWeight;
//			cnt++;
			
//			if (cnt == V) break;
			
			for (Vertex vertex : adjList.get(pNo)) {
				if (!visited[vertex.no] && vertex.weight <= minEdge[vertex.no]) {
					pQueue.offer(vertex);
					minEdge[vertex.no] = vertex.weight; 
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			sum += minEdge[i];
		}
		
		System.out.println(sum);
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