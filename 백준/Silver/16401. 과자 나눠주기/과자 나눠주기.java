import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int M, N;
	static int[] irr;
	static int max;
	static int cnt;
	
	static void binarySearch(int start, int end) {
		if (start > end) {
			return;
		}
		
		int mid = (start + end) / 2;
		
		cnt = 0;
		for (int i = 0; i < N; i++) {
			cnt += irr[i] / mid;
		}
		
		if (cnt >= M) {
			max = Math.max(max, mid);
			binarySearch(mid + 1, end);
		} else {
			binarySearch(start, mid - 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 조카 수
		N = Integer.parseInt(st.nextToken()); // 과자 개수
		st = new StringTokenizer(br.readLine());
		irr = new int[N];
		for (int i = 0; i < N; i++) {
			irr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(irr);
		binarySearch(1, 1_000_000_000);
		System.out.println(max);
	}
}