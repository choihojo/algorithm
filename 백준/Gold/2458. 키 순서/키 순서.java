import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static boolean[] visited;
	static int[][] adjMatrix;
	static int count;
	static int result;
	
	static int lDfs(int start) {
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[start][i] == 0) continue;
			if (visited[i]) continue;
			else {
				visited[i] = true;
				count++;
				lDfs(i);
			}
		}
		return 0;
	}
	
	static int gDfs(int end) {
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[i][end] == 0) continue;
			if (visited[i]) continue;
			else {
				visited[i] = true;
				count++;
				gDfs(i);
			}
		}
		return 0;
	}
	
	public static void main (String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		adjMatrix = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = 1;
		}
		for (int i = 1; i <= N; i++) {
			lDfs(i);
			gDfs(i);
			if (count == (N - 1)) result++;
			Arrays.fill(visited, false);
			count = 0;
		}
		System.out.println(result);
	}
}