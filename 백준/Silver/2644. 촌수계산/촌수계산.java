import java.io.*;
import java.util.*;

// 굳이 단방향으로 할 필요 없이 방문체크 배열 만들고 양방향 연결리스트로 탐색하면 될 듯
public class Main {
	static int n;
	static int m;
	static int x;
	static int y;
	static List<List<Integer>> list;
	static int[] visited;
	static int distance;
	
	public static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(x);
		
//		방문체크배열 대신 최단경로 거리 더해주는 알고리즘으로 풀었음
//		사실상 거리 갱신이 방문 체크도 겸하는 것임
		visited[x] = 0;
		int poll;
		
//		발견되지 않는 경우를 위해 -1로 초기화
		distance = -1;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			
			if (poll == y) {
				distance = visited[poll];
			}
			
//			이전 거리에다 1을 더해준 값을 저장함
			for (int e : list.get(poll)) {
				if (visited[e] == 0) {
					visited[e] = visited[poll] + 1;
					queue.offer(e);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String[] srr = br.readLine().split(" ");
		
//		계산해야 하는 촌수의 번호
		x = Integer.parseInt(srr[0]);
		y = Integer.parseInt(srr[1]);
		m = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
//		인덱싱 편하게 하기 위해서 n보다 1 크게 리스트 초기화
		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}
		visited = new int[n + 1];
		
		for (int i = 0; i < m; i++) {
			srr = br.readLine().split(" ");
			int a = Integer.parseInt(srr[0]);
			int b = Integer.parseInt(srr[1]);
			list.get(a).add(b);
			list.get(b).add(a);
		}
		
		bfs();
		System.out.println(distance);
	}
}