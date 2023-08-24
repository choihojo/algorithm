import java.io.*;
import java.util.*;
 
// 창용 마을에 몇 개의 무리가 존재하는지 계산
// 사람은 1번부터 N번까지 존재함
// N : 사람의 수
// M : 관계의 수

// 일단 대놓고 Union-Find로 풀라는 문제인 듯
// 근데 BFS, DFS로도 풀 수 있을 듯
// 3방법 다 해볼 예정

public class Solution {
	static int T;
	static int N, M;
//	union-find 알고리즘을 쓸 때 사용할 부모가 누구인지 알려주는 배열
	static int[] parents;
	
	static void make() {
//		인덱싱 때문에 1 크게 설정함
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static void union(int a, int b) {
		int aRoot = find(a); // 5
		int bRoot = find(b); // 4
		if (aRoot == bRoot) return;
		parents[bRoot] = aRoot;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		String[] srr;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			Set<Integer> set = new HashSet<>();
			srr = br.readLine().split(" ");
			N = Integer.parseInt(srr[0]);
			M = Integer.parseInt(srr[1]);
			
			make();
			
			for (int i = 0; i < M; i++) {
				srr = br.readLine().split(" ");
				int from = Integer.parseInt(srr[0]);
				int to = Integer.parseInt(srr[1]);
				union(from, to);
			}
			
			for (int i = 1; i <= N; i++) {
				set.add(find(i));
			}
			
			sb.append("#").append(t).append(" ").append(set.size()).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}