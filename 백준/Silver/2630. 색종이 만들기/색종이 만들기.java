import java.io.*;

public class Main {
	static int N;
	static boolean[][] map;
	static int blue;
	static int white;
	static int blueNum;
	
	static void divideConquer(int size, int[] start) {
		blueNum = 0;
		
		for (int i = start[0]; i < start[0] + size; i++) {
			for (int j = start[1]; j < start[1] + size; j++) {
				if (map[i][j]) {
					blueNum++;
				}
			}
		}
		
		if (blueNum == size * size) {
			blue++;
		}
		else if (blueNum == 0) {
			white++;
		}
		else {
			divideConquer(size / 2, new int[] {start[0], start[1]});
			divideConquer(size / 2, new int[] {start[0], start[1] + size / 2});
			divideConquer(size / 2, new int[] {start[0] + size / 2, start[1]});
			divideConquer(size / 2, new int[] {start[0] + size / 2, start[1] + size / 2});
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] srr;
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = srr[j].equals("1") ? true : false;
			}
		}
		divideConquer(N, new int[] {0, 0});
		System.out.println(white);
		System.out.println(blue);
	}
}