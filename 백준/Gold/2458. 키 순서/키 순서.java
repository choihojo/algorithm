import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,arr[][];
	static int tCnt, lCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int low = Integer.parseInt(st.nextToken());
			int tall = Integer.parseInt(st.nextToken());
			arr[low][tall] = 1;
		}
		
		int count = 0;
		
		for(int i = 1; i <= N; i++) {
			lCnt = 0;
			tCnt = 0;
			lDfs(i, new boolean[N + 1]);
			tDfs(i, new boolean[N + 1]);
			if(lCnt + tCnt == N - 1) {
				count++;
			}
		}
		
		System.out.println(count);
	}
	
	static void tDfs(int cur, boolean v[]) {
		v[cur] = true;
		for(int i = 1; i <= N; i++) {
			if(!v[i] && arr[cur][i] == 1) {
				tCnt++;
				tDfs(i, v);
			}
		}
	}

	static void lDfs(int cur, boolean v[]) {
		v[cur] = true;
		for(int i = 1; i <= N; i++) {
			if(!v[i] && arr[i][cur] == 1) {
				lCnt++;
				lDfs(i, v);
			}
		}
	}
}
