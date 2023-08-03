import java.io.*;


public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		String[] srr = new String[2];
		int n = 0;
		int m = 0;
		int sum = 0;
		int max = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			srr = br.readLine().split(" ");
			n = Integer.parseInt(srr[0]);
			m = Integer.parseInt(srr[1]);
			String[][] strMap = new String[n][n];
			for (int s = 0; s < n; s++) {
				strMap[s] = br.readLine().split(" ");
			}
			int[][] map = new int[n][n];
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					map[a][b] = Integer.parseInt(strMap[a][b]);
				}
			sum = 0;
			max = 0;
			for (int r = 0; r < n - m + 1; r++) {
				for (int c = 0; c < n - m + 1; c++) {
					for (int j = 0; j < m; j++) {
						for (int k = 0; k < m; k++) {
							sum += map[r + j][c + k];
						}
					}
					if (sum > max) {
						max = sum;
					}
					sum = 0;
				}
			}
			}
			if (i == t - 1) {
				sb.append("#" + (i + 1) + " ").append(max);
			}
			else {
				sb.append("#" + (i + 1) + " ").append(max).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}