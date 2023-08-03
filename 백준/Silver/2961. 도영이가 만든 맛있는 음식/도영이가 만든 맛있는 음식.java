import java.io.*;


public class Main {
	static int min = Integer.MAX_VALUE;
	
	public static void recursion(int[][] foods, int cnt, int n, int sour, int bitter, int col) {
		if (cnt == n) {
			if (col != 0) {
				if (sour >= bitter) {
					if (min > sour - bitter) {
						min = sour - bitter;
					}
				}
				else {
					if (min > bitter - sour) {
						min = bitter - sour;
					}
				}
			}
			return;
		}
		recursion(foods, cnt + 1, n, sour * foods[cnt][0], bitter + foods[cnt][1], col + 1);
		recursion(foods, cnt + 1, n, sour, bitter, col);
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] foods = new int[n][2];
		String[] srr = new String[2];
		int cnt = 0;
		int sour = 1;
		int bitter = 0;
		int col = 0;
		for (int i = 0; i < n; i++) {
			srr = br.readLine().split(" ");
			foods[i][0] = Integer.parseInt(srr[0]);
			foods[i][1] = Integer.parseInt(srr[1]);
		}
		recursion(foods, cnt, n, sour, bitter, col);
		System.out.println(min);
		
	}
}