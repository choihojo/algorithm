import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static Map<String, Integer> map;
	static int t;
	static int n;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			boolean flag = true;
			map = new HashMap<>();
			int num = Integer.parseInt(br.readLine());
			for (int j = 0; j < num; j++) {
				map.put(br.readLine(), 0);
			}
//			A : for (Entry<String, Integer> entry : map.entrySet()) {
//				for (int k = 1; k < entry.getKey().length(); k++) {
//					if (map.containsKey(entry.getKey().substring(0, k))) {
//						flag = false;
//						break A;
//					}
//				}
//			}
			A : for (String key : map.keySet()) {
				for (int k = 1; k < key.length(); k++) {
					if (map.containsKey(key.substring(0, k))) {
						flag = false;
						break A;
					}
				}
			}
			sb.append(flag ? "YES" : "NO").append("\n");
		}
		System.out.println(sb);
	}
}