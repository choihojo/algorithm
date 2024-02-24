import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String[][] map;
	static String[][] cloneMap;
	static BufferedReader br;
	static StringTokenizer st;
	static int[] output;
	static int student;
	static List<int[]> teacher;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static boolean flag;
	
	static void dfs(int cnt) {
		if (cnt == 3) {
			if (check()) {
				flag = true;
			}
			return;
		}
		
		for (int i = 0; i < N * N; i++) {
			output[cnt] = i;
			dfs(cnt + 1);
		}
	}
	
	static boolean check() {
		init();
		
		for (int i = 0; i < 3; i++) {
			int wall = output[i];
			int row = wall / N;
			int col = wall % N;
			
			if ("X".equals(cloneMap[row][col])) {
				cloneMap[row][col] = "O";
			}
			else {
				return false;
			}
		}
		
		int num = 0;
		for (int k = 0; k < teacher.size(); k++) {
			int oRow = teacher.get(k)[0];
			int oCol = teacher.get(k)[1];
			for (int l = 0; l < 4; l++) {
				int row = oRow + dRow[l];
				int col = oCol + dCol[l];
				while (row >= 0 && row < N && col >= 0 && col < N && !"O".equals(cloneMap[row][col])) {
					if ("S".equals(cloneMap[row][col])) {
						num++;
					}
					row += dRow[l];
					col += dCol[l];
				}
			}
		}
		
		return num == 0;
	}
	
	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cloneMap[i][j] = map[i][j];
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		cloneMap = new String[N][N];
		teacher = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = st.nextToken();
				if ("S".equals(map[i][j])) {
					student++;
				}
				else if ("T".equals(map[i][j])) {
					teacher.add(new int[] {i, j});
				}
			}
		}
		
		output = new int[3];
		dfs(0);
		
		System.out.println(flag ? "YES" : "NO");
	}
}