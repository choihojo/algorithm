import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static List<List<Integer>> graph;
	static int cnt = 0;
//	방문체크용 배열
	static boolean[] visited;
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		
//		총 N개의 노드에 대해 같은 영역인지 조사함
		for (int i = 1; i <= N; i++) {
//			만약 방문하지 않은 노드의 경우 bfs 시작
			if (!visited[i]) {
				queue.offer(i);
				visited[i] = true;
				int poll = 0;
				
				while (!queue.isEmpty()) {
					poll = queue.poll();
					
//					poll한 노드에 연결된 노드를 큐에 넣고 방문체크
					for (int e : graph.get(poll)) {
						if (!visited[e]) {
							visited[e] = true;
							queue.offer(e);
						}
					}
				}
//				하나의 연결된 영역을 다 탐색했으면 카운팅
				cnt++;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
//		정점의 개수
		N = Integer.parseInt(srr[0]);
//		간선의 개수
		M = Integer.parseInt(srr[1]);
		
		graph = new ArrayList<>();
		
//		인덱싱 편하게 하기 위해 크기 (N+1)로 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		visited = new boolean[N + 1];
		
		int a = 0;
		int b = 0;
		for (int m = 0; m < M; m++) {
			srr = br.readLine().split(" ");
			a = Integer.parseInt(srr[0]);
			b = Integer.parseInt(srr[1]);
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		bfs();
		
		System.out.println(cnt);
	}
}