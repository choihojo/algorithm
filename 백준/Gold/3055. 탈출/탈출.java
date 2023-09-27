import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R,C,S[],D[];
	static String map[][];
	static ArrayList<int[]> W = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new String[R][C];
		wv = new int[R][C];
		sv = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			map[i] = bf.readLine().split("");
			for(int j = 0; j < C; j++) {
				if(map[i][j].equals("D")) {
					D = new int[] {i, j};
				}else if(map[i][j].equals("S")) {
					S = new int[] {i, j};
				}else if(map[i][j].equals("*")) {
					W.add(new int[] {i, j});
				}
			}
		}
		
		toDo();
	}
	static void toDo() {	
		doWater();
		doMove();
	}
	
	static int[][] wv;
	private static void doWater() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for(int i = 0; i < W.size(); i++) {
			int x = W.get(i)[0];
			int y = W.get(i)[1];
			q.offer(new int [] {x,y});
			wv[x][y] = 1;
		}
		
		int dx[] = new int[] {-1,1,0,0};
		int dy[] = new int[] {0,0,-1,1};
		int cnt = 1;
		while(!q.isEmpty()) {
			int size = q.size();
			cnt++;
			for(int k = 0; k < size; k++) {
				int[] temp = q.poll();
				int x = temp[0];
				int y = temp[1];
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if(nx >= 0 && nx < R && ny >= 0 && ny < C && wv[nx][ny] == 0 && map[nx][ny].equals(".")) {
						wv[nx][ny] = cnt;
						map[nx][ny] = "*";
						q.offer(new int[] {nx, ny});
					}
				}				
			}
		}
	}
	static boolean sv[][];
	private static void doMove() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int [] {S[0], S[1]});
		int dx[] = new int[] {-1,1,0,0};
		int dy[] = new int[] {0,0,-1,1};
		int cnt  = 1;
		while(!q.isEmpty()) {
			cnt++;
			int size = q.size();
			for(int i = 0; i < size; i++) {
				int temp[] = q.poll();
				int x = temp[0];
				int y = temp[1];
				for(int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
						if(map[nx][ny].equals("D")) {
							System.out.println(cnt - 1);
							return;
						}
						if((wv[nx][ny] > cnt || wv[nx][ny] == 0) && !sv[nx][ny] && !map[nx][ny].equals("X")) {
							sv[nx][ny] = true;
							q.offer(new int[] {nx, ny});
						}
					}
				}
			}
		}
		
		System.out.println("KAKTUS");
	}
}
