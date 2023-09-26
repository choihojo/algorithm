import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static int N, M,safty,map[][];
	static boolean[] v;
	static boolean[][] v2;
	static int res[] = new int[3];
	static int min = Integer.MAX_VALUE;
	static ArrayList<int[]> b = new ArrayList<>();
	static ArrayList<int[]> s = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 0) {
					s.add(new int[] {i,j});
				}else if(temp == 2) {
					b.add(new int[] {i,j});
				}
			}
		}
		safty = s.size();
		v = new boolean[safty];
		
		combi(0,0);
		
		System.out.println(safty-min-3);
	}
	
	static void combi(int cnt, int start) {
		if(cnt == 3) {
			toDo();
			return;
		}
		for(int i = start; i < safty; i++) {
			if(v[i]) {
				continue;
			}
			v[i] = true;
			res[cnt] = i;
			combi(cnt + 1, i + 1);
			v[i] = false;
		}
	}
	
	static void toDo() {
		int temp[][] = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], M);
		}
		for(int i = 0; i < 3; i++) {
			temp[s.get(res[i])[0]][s.get(res[i])[1]] = 1;
		}
		
		v2 = new boolean[N][M];
		
		Queue<int[]> q = new ArrayDeque<>();
		int cnt = 0;
		for(int i = 0; i < b.size(); i++) {
//			System.out.println(Arrays.toString(b.get(i)));
			q.offer(b.get(i));
		}
		int dx[] = new int[] {-1,1,0,0};
		int dy[] = new int[] {0,0,-1,1};
		while(!q.isEmpty()) {
//			for(int i = 0; i < N ; i++) {
//				System.out.println(Arrays.toString(temp[i]));
//			}
//			System.out.println();
			int tmp[] = q.poll();
//			System.out.println(Arrays.toString(tmp));
			int x = tmp[0];
			int y = tmp[1];
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
//				System.out.println(nx + " " + ny);
				if(nx >= 0 && nx < N && ny >= 0 && ny < M  && temp[nx][ny] == 0) {
					cnt++;
					temp[nx][ny] = 2;
					q.offer(new int[] {nx,ny});
				}
			}
			if(cnt > min) {
				return;
			}
		}
		
		
		if(cnt < min) {
			min = cnt;
		}
	}
}
