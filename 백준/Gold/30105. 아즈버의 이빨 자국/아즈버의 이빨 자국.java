import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static int[] teeth;
	static boolean[] visited;
	static Set<Integer> set;
	static List<Integer> list;
	static int cnt;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		teeth = new int[N];
		visited = new boolean[1_000_000_001];
		set = new HashSet<>();
		list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			teeth[n] = Integer.parseInt(st.nextToken());
			visited[teeth[n]] = true;
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				set.add(teeth[j] - teeth[i]);
			}
		}
		for (int k : set) {
			boolean flag = true;
			for (int n = 0; n < N; n++) {
				int minus = teeth[n] - k;
				if (minus >= 0) {
					if (visited[minus]) continue; 
				}
				int plus = teeth[n] + k;
				if (plus <= 1_000_000_000) {
					if (visited[plus]) continue;
				}
				flag = false;
				break;
			}
			if (flag) {
				list.add(k);
				cnt++;
			}
		}
		Collections.sort(list);
		sb.append(cnt).append("\n");
		for (int k : list) {
			sb.append(k).append(" ");
		}
		System.out.println(sb);
	}
}