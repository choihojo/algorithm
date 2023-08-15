import java.io.*;
import java.util.*;

public class Main {
//	접근 편하게 하기 위해서 연결정보와 방문정보는 static으로 선언
	static List<List<Integer>> network;
	static boolean[] visited;
	static int cnt = 0;
	
	public static void dfsByRecursion(int start) {
		for (int e : network.get(start)) {
			if (!visited[e]) {
				visited[e] = true;
				cnt++;
				dfsByRecursion(e);
			}
		}
	}
	
	public static void dfsByStack(int start) {
		Stack<Integer> stack = new Stack<>();
		stack.push(start);
		visited[start] = true;
		int peek;
		boolean flag;
		
		while (!stack.isEmpty()) {
			peek = stack.peek();
			flag = false;
			
			for (int e : network.get(peek)) {
				if (!visited[e]) {
					visited[e] = true;
					cnt++;
					flag = true;
					stack.push(e);
					break;
				}
			}
			
			if (!flag) {
				stack.pop();
			}
		}
	}
	
	public static void bfsByQueue(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;
		int poll;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			
			for (int e : network.get(poll)) {
				if (!visited[e]) {
					visited[e] = true;
					cnt++;
					queue.offer(e);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int computers = Integer.parseInt(br.readLine());
		int lines = Integer.parseInt(br.readLine());
		
//		인접배열이 아닌 리스트로 풀어볼 예정
//		bfs, dfs 모두 가능할 것으로 예상됨
		network = new ArrayList<>();
		for (int c = 0; c <= computers; c++) {
			network.add(new ArrayList<>());
		}
		
		visited = new boolean[computers + 1];
		
		String[] srr;
		int a = 0;
		int b = 0;
		for (int l = 0; l < lines; l++) {
			srr = br.readLine().split(" ");
			a = Integer.parseInt(srr[0]);
			b = Integer.parseInt(srr[1]);
			network.get(a).add(b);
			network.get(b).add(a);
		}
		
		int start = 1;
		
		visited[start] = true;
		dfsByRecursion(start);
		System.out.println(cnt);
		
//		dfsByStack(start);
//		System.out.println(cnt);
		
//		bfsByQueue(start);
//		System.out.println(cnt);
	}
}