import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;
	static int[] teeth;
//	static boolean[] visited;
	static Map<Integer, Integer> map;
	static Set<Integer> set;
	static List<Integer> list;
	static int cnt;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		teeth = new int[N];
//		visited = new boolean[1_000_000_001];
		map = new HashMap<>();
		set = new HashSet<>();
		list = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			teeth[n] = Integer.parseInt(st.nextToken());
			map.put(teeth[n], 0);
//			visited[teeth[n]] = true;
		}
		for (int i = 1; i < N; i++) {
			set.add(teeth[i] - teeth[0]);
		}
		for (int k : set) {
			boolean flag = true;
			for (int n = 0; n < N; n++) {
//				int minus = teeth[n] - k;
//				if (minus >= 0) {
//					if (visited[minus]) continue; 
//				}
//				int plus = teeth[n] + k;
//				if (plus <= 1_000_000_000) {
//					if (visited[plus]) continue;
//				}
				if (map.containsKey(teeth[n] - k)) continue;
				if (map.containsKey(teeth[n] + k)) continue;
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