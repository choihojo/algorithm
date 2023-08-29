import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] parents;
	
	static void make() {
		parents = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB) return false;
		parents[rootB] = rootA;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		make();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (cmd == 0) union(a, b);
			else sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
		}
		if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}