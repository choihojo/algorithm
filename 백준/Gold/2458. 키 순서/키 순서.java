import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static int[][] adjMatrix;
	static int result;
	static boolean[] gVisited;
	static boolean[] lVisited;
	
	static void gDfs(int row) {
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[row][i] == 0) continue;
			if (adjMatrix[i][0] == -1) {
				gDfs(i);
			}
			
			if (adjMatrix[i][0] > 0) {
				for (int j = 1; j <= N; j++) {
					if (adjMatrix[i][j] == 1) {
						adjMatrix[row][j] = 1;
					}
				}
			}
		}
		
		int count = 0;
		for (int j = 1; j <= N; j++) {
			count += adjMatrix[row][j];
		}
		adjMatrix[row][0] = count;
	}
	
	public static void main (String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjMatrix = new int[N + 1][N + 1];
		gVisited = new boolean[N + 1];
		lVisited = new boolean[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = 1;
		}
		
		for (int i = 0; i <= N; i++) {
			adjMatrix[i][0] = -1;
		}
		
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[i][0] == -1) gDfs(i);
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				adjMatrix[0][j] += adjMatrix[i][j];
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[i][0] + adjMatrix[0][i] == (N - 1)) result++;
		}
		
		System.out.println(result);
	}
}