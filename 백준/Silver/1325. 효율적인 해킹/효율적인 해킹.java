import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static List<Integer>[] list;
	static boolean[] visited;
	static int[] counted;
	static StringBuilder sb;
	static int max;
	
	static void bfs(int i, int temp) {
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int k = 1; k < temp; k++) {
			visited[k] = false;
		}
		
		queue.offer(i);
		visited[i] = true;
		int poll = 0;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			for (int j : list[poll]) {
				if (!visited[j]) {
					queue.offer(j);
					visited[j] = true;
					counted[j]++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int temp = N + 1;
		
		list = new ArrayList[temp];
		for (int i = 0; i < temp; i++) {
			list[i] = new ArrayList<>();
		}
		
		visited = new boolean[temp];
		counted = new int[temp];
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		
		for (int i = 1; i < temp; i++) {
			bfs(i, temp);			
		}
		
		for (int i = 1; i < temp; i++) {
			max = max < counted[i] ? counted[i] : max;
		}
		sb = new StringBuilder();
		for (int i = 1; i < temp; i++) {
			if (counted[i] == max) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(sb);
		
	}
	
}