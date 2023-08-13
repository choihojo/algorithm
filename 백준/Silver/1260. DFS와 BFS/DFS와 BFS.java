import java.io.*;
import java.util.*;


public class Main {
	static int N;
	static int M;
	static int V;
	static int[][] irr;
	static boolean[] visitedDfs;
	static StringBuilder sbDfs = new StringBuilder();
	static boolean[] visitedBfs;
	static StringBuilder sbBfs = new StringBuilder();
	
	public static void dfs(int V) {
		for (int i = 1; i <= N; i++) {
			if (!visitedDfs[i] && (irr[V][i] == 1)) {
				visitedDfs[i] = true;
				sbDfs.append(i).append(" ");
				dfs(i);
			}
		}
	}
	
	
//	public static void dfsStack(int V) {
//		Stack<Integer> stack = new Stack<>();
//		
//		stack.push(V);
//		
//		while (!stack.isEmpty()) {
//			int temp = stack.pop();
//			sbDfs.append(temp).append(" ");
//			
//			for (int i = 1; i <= N; i++) {
//				if (!visitedDfs[i] && irr[temp][i] == 1) {
//					visitedDfs[i] = true;
//					stack.push(i);
////					아래 break가 dfs로 stack을 구현할 때 핵심임
//					break;
//				}
//			}
//		}
//	}
	
	
	public static void bfs(int V) {
		Queue<Integer> queue = new ArrayDeque<>();
		
//		시작할 V 노드 추가
		queue.offer(V);
		
//		queue가 비어질 때까지 루프 반복
		while (!queue.isEmpty()) {
//			queue의 head에 있는 노드를 꺼내고 저장
			int temp = queue.poll();
			sbBfs.append(temp).append(" ");
			
			for (int i = 1; i <= N; i++) {
//				i 노드가 꺼낸 노드와 연결되어 있고 방문한 적이 없었을 경우에 queue에 추가
				if (!visitedBfs[i] && (irr[temp][i] == 1)) {
					visitedBfs[i] = true;
					queue.offer(i);
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M  = Integer.parseInt(srr[1]);
		V = Integer.parseInt(srr[2]);
		
//		인덱싱 편하게 하기 위해 N이 아닌 N + 1로 설정
//		0번째는 그냥 비워둠
		irr = new int[N + 1][N + 1];
		visitedDfs = new boolean[N + 1];
		visitedDfs[V] = true;
		visitedBfs = new boolean[N + 1];
		visitedBfs[V] = true;
		
		int a = 0;
		int b = 0;
		for (int m = 0; m < M; m++) {
			srr = br.readLine().split(" ");
			a = Integer.parseInt(srr[0]);
			b = Integer.parseInt(srr[1]);
			irr[a][b] = 1;
			irr[b][a] = 1;
		}
		
		sbDfs.append(V).append(" ");
		dfs(V);
		sbDfs.deleteCharAt(sbDfs.length() - 1);
		System.out.println(sbDfs);
		
		bfs(V);
		sbBfs.deleteCharAt(sbBfs.length() - 1);
		System.out.println(sbBfs);
	}
}