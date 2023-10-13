import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(b.readLine());
		int[] t = new int[N];
		Set<Integer> v = new HashSet<>();
		Set<Integer> a = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		StringTokenizer z = new StringTokenizer(b.readLine());
		StringBuilder s = new StringBuilder();
		int cnt = 0;
		for (int n = 0; n < N; n++) {
			t[n] = Integer.parseInt(z.nextToken());
			v.add(t[n]);
		}
		for (int i = 1; i <= N / 2; i++) a.add(t[i] - t[0]);
		for (int k : a) {
			boolean f = true;
			for (int n = 0; n < N; n++) {if (v.contains(t[n] - k)) continue;if (v.contains(t[n] + k)) continue;
				f = false;
				break;
			}
			if (f) {list.add(k);cnt++;}
		}
		Collections.sort(list);
		for (int k : list) {
			s.append(k).append(" ");
		}
		System.out.println(cnt);
		System.out.println(s);
	}
}