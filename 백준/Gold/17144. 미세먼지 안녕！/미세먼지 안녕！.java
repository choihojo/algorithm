import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T, map[][], top[], bottom[], count;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		boolean flag = false;
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < C; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(!flag && temp == -1) {
					top = new int[] {i, j};
					bottom = new int[] {i + 1, j};
					flag = true;
				}
			}
		}

		for(int i = 0; i < T; i++) {
			spread();
			windTop();
			windBottom();
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != 0 && map[i][j] != -1) {
					count += map[i][j];
				}
			}
		}
		
		System.out.println(count);
	}
	
	static int dx[] = new int[] {-1,1,0,0};
	static int dy[] = new int[] {0,0,-1,1};
	private static void spread() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				int temp = map[i][j];
				if(temp != 0 && temp != -1) {
					q.offer(new int[] {i, j, temp});
					int sSize = temp/5;
					for(int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != -1) {
							temp = temp - sSize;
						}
					}
					
					map[i][j] = temp;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int temp[] = q.poll();
			int x = temp[0];
			int y = temp[1];
			int z = temp[2];
			int sSize = z / 5;
			
			for(int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != -1) {
					map[nx][ny] += sSize;
				}
			}
		}
		
//		for(int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//		System.out.println();
	}
	
	private static void windTop() {
		int x = top[0];
		int y = top[1];
		Queue<Integer> q = new ArrayDeque<>();
		int ddx[] = new int[] {-1,0,1,0};
		int ddy[] = new int[] {0,1,0,-1};
		int idx = 0;
		int nx = x + ddx[idx];
		int ny = y + ddy[idx];
		while(true) {
			if(nx == x && ny == y) {
				break;
			}
//			System.out.println(nx + " " + ny);
			map[nx - ddx[idx]][ny - ddy[idx]] = map[nx][ny];		
			
			nx = nx + ddx[idx];
			ny = ny + ddy[idx];
			if(nx < 0 || ny < 0 || nx > x || ny > C - 1) {
				nx = nx - ddx[idx];
				ny = ny - ddy[idx];
				idx++;
				nx = nx + ddx[idx];
				ny = ny + ddy[idx];
			}
		}
		map[x][y] = -1;
		map[x][y + 1] = 0;
//		for(int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}

	private static void windBottom() {
		int x = bottom[0];
		int y = bottom[1];
		Queue<Integer> q = new ArrayDeque<>();
		int ddx[] = new int[] {1,0,-1,0};
		int ddy[] = new int[] {0,1,0,-1};
		int idx = 0;
		int nx = x + ddx[idx];
		int ny = y + ddy[idx];
		while(true) {
			if(nx == x && ny == y) {
				break;
			}
//			System.out.println(nx + " " + ny);
			map[nx - ddx[idx]][ny - ddy[idx]] = map[nx][ny];		
			
			nx = nx + ddx[idx];
			ny = ny + ddy[idx];
			if(nx < x || ny < 0 || nx > R - 1 || ny > C - 1) {
				nx = nx - ddx[idx];
				ny = ny - ddy[idx];
				idx++;
				nx = nx + ddx[idx];
				ny = ny + ddy[idx];
			}
		}
		map[x][y] = -1;
		map[x][y+1] = 0;
//		for(int i = 0; i < R; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
	}
	
}
