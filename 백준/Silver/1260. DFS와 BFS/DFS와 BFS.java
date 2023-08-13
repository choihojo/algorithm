import java.io.*;
import java.util.*;

// 이전 풀이와 다르게 인접행렬로 풀었음
public class Main {
	static int N;
	static int M;
	static int V;
	static boolean[][] graph;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void dfsByRecursion(int e) {
		for (int n = 1; n <= N; n++) {
			if (!visited[n] && graph[e][n]) {
				visited[n] = true;
				sb.append(n).append(" ");
				dfsByRecursion(n);
			}
		}
	}
	
	public static void dfsByStack(int e) {
		Stack<Integer> stack = new Stack<>();
		
		visited[e] = true;
		sb.append(e).append(" ");
		stack.push(e);
		int peek;
		boolean flag;
		
//		dfs는 peek와 flag를 둔다고 생각할 것
		while (!stack.isEmpty()) {
			peek = stack.peek();
			flag = false;
			
			for (int n = 1; n <= N; n++) {
				if (!visited[n] && graph[peek][n]) {
					visited[n] = true;
					stack.push(n);
					sb.append(n).append(" ");
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				stack.pop();
			}
		}
	}
	
	public static void bfsByQueue(int e) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		visited[e] = true;
		queue.offer(e);
		sb.append(e).append(" ");
		int poll;
		
//		bfs는 poll을 둔다고 생각할 것
		while (!queue.isEmpty()) {
			poll = queue.poll();
			
			for (int n = 1; n <= N; n++) {
				if (!visited[n] && graph[poll][n]) {
					visited[n] = true;
					queue.offer(n);
					sb.append(n).append(" ");
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		V = Integer.parseInt(srr[2]);
		
		graph = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		sb = new StringBuilder();
		
		int a = 0;
		int b = 0;
		for (int m = 0; m < M; m++) {
			srr = br.readLine().split(" ");
			a = Integer.parseInt(srr[0]);
			b = Integer.parseInt(srr[1]);
			graph[a][b] = true;
			graph[b][a] = true;
		}
		
//		visited[V] = true;
//		sb.append(V).append(" ");
//		dfsByRecursion(V);
//		sb.deleteCharAt(sb.length() - 1);
//		System.out.println(sb.toString());
		
		dfsByStack(V);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
		
		for (int n = 1; n <= N; n++) {
			visited[n] = false;
		}
		sb.setLength(0);
		
		bfsByQueue(V);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());	
	}
}
