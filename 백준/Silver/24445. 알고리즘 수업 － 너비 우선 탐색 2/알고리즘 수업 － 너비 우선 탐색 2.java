import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int[] visited;
	static List<List<Integer>> list;
	static int N, M, R;
	static StringBuilder sb;

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(R);
		visited[R] = 1;
		
		int poll = 0;
		int order = 2;
		while (!queue.isEmpty()) {
			poll = queue.poll();

			for (int i : list.get(poll)) {
				if (visited[i] == 0) {
					queue.offer(i);
					visited[i] = order++;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		visited = new int[N + 1];
		list = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			list.add(new ArrayList<>());
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.get(start).add(end);
			list.get(end).add(start);
		}
		for (int n = 1; n <= N; n++) {
			Collections.sort(list.get(n), (o1, o2) -> (o1 < o2 ? 1 : (o1 == o2 ? 0 : -1)));;
		}
		bfs();
		sb = new StringBuilder();
		for (int n = 1; n <= N; n++) {
			sb.append(visited[n]).append("\n");
		}
		System.out.println(sb);
	}
}