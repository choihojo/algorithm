import java.io.*;
import java.util.*;

// ArrayList를 이용하여 그래프를 만들고 풂
public class Main {
	static int N;
	static int M;
	static int V;
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void dfsByRecursion(int e) {
//		인접행렬을 이용할 때와 가장 큰 차이점은 아래의 for문 범위임
//		인접행렬은 1에서 N까지 순회해야하지만 리스트를 이용할 때는 해당 노드에 연결된 노드들만을 탐색할 수 있음
		for (int i : graph.get(e)) {
//			이미 있는 원소들 중에서 방문 여부를 확인하기 때문에 인접행렬을 이용할 때와 달리 간선 존재 여부를 판단할 필요가 없음
			if (!visited[i]) {
				visited[i] = true;
				sb.append(i).append(" ");
				dfsByRecursion(i);
			}
		}
	}
	
	public static void dfsByStack(int e) {
		Stack<Integer> stack = new Stack<>();
		
//		스택을 이용할 때는 재귀적인 호출이 일어나지 않으므로 메서드 안에서 방문배열을 초기화했음
		visited = new boolean[N + 1];
		visited[e] = true;
		stack.push(e);
		sb.append(e).append(" ");
		int peek;
		boolean flag;
		
		while (!stack.isEmpty()) {
			peek = stack.peek();
			flag = false;
			
//			스택으로 dfs를 구현할 때의 핵심은 flag와 break
//			구현 과정을 머릿속에 그려가면서 완전히 숙지할 것
			for (int i : graph.get(peek)) {
				if (!visited[i]) {
					visited[i] = true;
					stack.push(i);
					sb.append(i).append(" ");
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
		Queue<Integer> queue = new ArrayDeque<>(); 
		
		queue.offer(e);
		visited[e] = true;
//		int poll;
		sb.append(e).append(" ");
		
		while (!queue.isEmpty()) {
//			아래 방식대로 해도 됨 (대신 위에서 sb.append는 지워야 함)
//			poll = queue.poll();
//			sb.append(poll).append(" ");
//			for (int i : graph.get(poll)) {
//				if (!visited[i]) {
//					queue.offer(i);
//					visited[i] = true;
//				}
//			}
			
			for (int i : graph.get(queue.poll())) {
				if (!visited[i]) {
					queue.offer(i);
					visited[i] = true;
					sb.append(i).append(" ");
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
		
//		인덱싱을 편하게 하기 위해서 (N + 1)개 넣어줌
//		객체 안에 객체들이 있는 구조기 때문에 반드시 이 초기화 과정이 필요함
		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}
		
		int a = 0;
		int b = 0;
		for (int m = 0; m < M; m++) {
			srr = br.readLine().split(" ");
			a = Integer.parseInt(srr[0]);
			b = Integer.parseInt(srr[1]);
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
//		작은 노드부터 방문하게 해야되므로 반드시 sort 과정이 필요함
//		입력 과정에서 크기 순서대로 넣어주는 것이 아니기 때문
//		Arrays.sort() 메서드는 Integer[]에 사용이 불가능한 듯?
		for (int n = 1; n <= N; n++) {
			Collections.sort(graph.get(n));
		}
		
//		dfsByRecursion
//		visited = new boolean[N + 1];
//		visited[V] = true;
//		sb.append(V).append(" ");
//		dfsByRecursion(V);
//		sb.deleteCharAt(sb.length() - 1);
//		System.out.println(sb.toString());
		
//		dfsByStack
		dfsByStack(V);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
		
//		bfs 돌리기 전에 공용 변수 초기화
		sb.setLength(0);
		for (int n = 0; n <= N; n++) {
			visited[n] = false;
		}
		
//		bfsByQueue
		bfsByQueue(V);
		System.out.println(sb.toString());
	}
}