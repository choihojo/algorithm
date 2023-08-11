import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] list = new int[N][2];
		for (int n = 0; n < N; n++) {
			String[] srr = br.readLine().split(" ");
			for (int i = 0; i < 2; i++) {
				list[n][i] = Integer.parseInt(srr[i]);
			}
		}
		Arrays.sort(list, (o1, o2) -> {
			if (o1[1] == o2[1]) {
				return o1[0] - o2[0];
			}
			else {
				return o1[1] - o2[1];
			}});
		
		List<Integer> idx = new ArrayList<>();
		idx.add(0);
		int j = 0;
		for (int i = 1; i < N; i++) {
			if (list[j][1] <= list[i][0]) {
				idx.add(i);
				j = i;
			}
		}
		
		System.out.println(idx.size());
	}
}