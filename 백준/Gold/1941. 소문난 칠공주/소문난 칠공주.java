import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static String map[][] = new String[5][5];
	static boolean v[] = new boolean[25];
	static int res[] = new int[7];
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			map[i] = bf.readLine().split("");
		}
		
		combi(0,0);
		System.out.println(count);
	}
	static void combi(int cnt, int start) {
		if(cnt == 7) {
			if(toDo()) {
				count++;
			}
			return;
		}
		
		for(int i = start; i < 25; i++) {
			if(v[i]) {
				continue;
			}
			v[i] = true;
			res[cnt] = i;
			combi(cnt + 1, i+1);
			v[i] = false;
		}
	}
	
	static boolean toDo() {
		int scnt = 0;
		for(int i = 0; i < 7; i++) {
			int x = res[i] / 5;
			int y = res[i] % 5;
			
			if(map[x][y].equals("S")) {
				scnt++;
			}
		}
		if(scnt < 4) {
			return false;
		}
		
		if(!isBetween()) {
			return false;
		}
//		System.out.println(scnt);
		
		return true;
	}
	
	static boolean v2[][];
	static boolean isBetween() {
//		System.out.println(Arrays.toString(res));
		Queue<int[]> q = new ArrayDeque<int[]>();
		v2 = new boolean[5][5];
		int x = res[0] / 5;
		int y = res[0] % 5;
		
		v2[x][y] = true;
		q.offer(new int[] {x, y});
		
		int dx[] = new int[] {-1,1,0,0};
		int dy[] = new int[] {0,0,-1,1};
		
		int cnt = 0;
		while(!q.isEmpty()) {
			cnt++;
			int temp[] = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !v2[nx][ny]) {
//					System.out.println("qq");
					for(int j = 1; j < 7; j++) {
						int bx = res[j] / 5;
						int by = res[j] % 5;
						if(nx == bx && ny == by) {
							v2[nx][ny] = true;
							q.offer(new int[] {nx, ny});
						}
					}
				}
			}
		}
//		System.out.println(cnt);
		if(cnt == 7) {
			return true;			
		}
		return false;
	}
}
