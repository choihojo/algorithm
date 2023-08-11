import java.io.*;

public class Main {
	static int N;
	static int[][] irr;
	static int[] seq;
	static boolean[] brr;
	static int idx;
	static int max = Integer.MIN_VALUE;
	static int temp = 0;
	
	static void combination(int start) {
		if (start >= N) {
			temp = 0;
			for (int i = 0; i < N; i++) {
				if (brr[i]) {
					temp += irr[i][2];
				}
			}
			if (temp > max) {
				max = temp;
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			brr[i] = true;
			combination(i + 2);
			brr[i] = false;
		}
		
		
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		irr = new int[N][3];
		seq = new int[N];
		brr = new boolean[N];
		
		
		for (int n = 0; n < N; n++) {
			String[] srr = br.readLine().split(" ");
			for (int i = 0; i < 3; i++) {
				irr[n][i] = Integer.parseInt(srr[i]);
			}
		}
		
		for (int i = 0; i < N; i++) {
			seq[i] = i;
		}
		
		combination(0);
		System.out.println(max);
		
	}
}
