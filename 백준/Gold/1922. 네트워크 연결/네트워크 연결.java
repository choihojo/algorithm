import java.io.*;
import java.util.*;

// MST 연습하는 문제
// 프림 알고리즘 이용

public class Main {
	static int N;
	static int M;
//	프림 알고리즘은 정점 중심 알고리즘이므로 정점 리스트들의 리스트로 저장함 (리스트의 인덱스는 시작 정점을 의미함)
	static List<List<Vertex>> list;
	static boolean[] visited;
	
	static int[] minEdge;
	static int sum;
	static int cnt;
	
	static void prim() {
//		방문체크 및 간선 배열 초기화
		visited = new boolean[N + 1];
		minEdge = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			minEdge[i] = Integer.MAX_VALUE;
		}
//		우선순위 큐 생성
		PriorityQueue<Vertex> pQueue = new PriorityQueue<>((o1, o2) -> (o1.weight > o2.weight ? 1 : (o1.weight == o2.weight ? 0 : -1)));
		pQueue.offer(new Vertex(1, 0));
//		시작 정점의 간선 거리는 0으로 초기화
//		프림에서 시작 정점은 어느 정점이든 상관없음
		minEdge[1] = 0;
		Vertex poll;
		int pNo;
		int pWeight;
		while (!pQueue.isEmpty()) {
			poll = pQueue.poll();
			pNo = poll.no;
			pWeight = poll.weight;
			
			if (visited[pNo]) {
				continue;
			}
			
			visited[pNo] = true;
			sum += pWeight;
			cnt++;
			
			if (cnt == N) break;
			
			for (Vertex vertex : list.get(pNo)) {
				int no = vertex.no;
				int weight = vertex.weight;
				if (!visited[no] && weight < minEdge[no]) {
					minEdge[no] = weight;
					pQueue.offer(vertex);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
//		인덱싱 편하게 하기 위해 1 크게 설정함
		for (int i = 0; i <= N; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
//			유향인지 무향인지 잘 구별할 것
			list.get(from).add(new Vertex(to, weight));
			list.get(to).add(new Vertex(from, weight));
		}
		prim();
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