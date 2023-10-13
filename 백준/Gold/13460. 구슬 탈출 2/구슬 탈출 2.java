import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R[] = new int[2], O[] = new int[2], B[] = new int[2], res[] = new int[10], min = Integer.MAX_VALUE;
	static String map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new String[N][M];
		
		for(int i = 0; i < N; i++) {
			String[] temp = bf.readLine().split("");
			for(int j = 0; j < M; j++) {
				map[i][j] = temp[j];
				if(temp[j].equals("R")) {
					R[0] = i;
					R[1] = j;
				}else if(temp[j].equals("B")) {
					B[0] = i;
					B[1] = j;
				}else if(temp[j].equals("O")) {
					O[0] = i;
					O[1] = j;
				}
			}
//			System.out.println(Arrays.toString(map[i]));
		}
		
		toDo(0);
//		res[0] = 3;
//		toDo2();
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min+1);
		}
	}
	
	static void toDo(int cnt) {
		if(cnt == 10) {
//			System.out.println(Arrays.toString(res));
			toDo2();
			return;
		}
		
		for(int i = 1; i <= 4; i++) {
			if(cnt > 0 && i == res[cnt - 1]) {
				continue;
			}
			res[cnt] = i;
			toDo(cnt + 1);
		}
	}
	
	static int dx[] = new int[] {0,-1,1,0,0};
	static int dy[] = new int[] {0,0,0,-1,1};
	static String tmpmap[][];
	static int[] tmpR;
	static int[] tmpB;
	static boolean rGole;
	static boolean bGole;
	
	static boolean toDo2() {
		tmpmap = new String[N][M];
		for(int i = 0; i < N; i++) {
			tmpmap[i] = Arrays.copyOf(map[i], M);
		}
		tmpR = Arrays.copyOf(R, 2);
		tmpB = Arrays.copyOf(B, 2);
		
		rGole = false;
		bGole = false;
		for(int i = 0; i < 10; i++) {
			if(i > min) {
				break;
			}

			int d = res[i];
			int rx = tmpR[0];
			int ry = tmpR[1];
			int bx = tmpB[0];
			int by = tmpB[1];
			
			switch (d) {
			case 1:
				if(rx >= bx) {
					bMove(d);
					rMove(d);
				}else {
					rMove(d);
					bMove(d);
				}
				break;
			case 2:
				if(rx >= bx) {
					rMove(d);
					bMove(d);
				}else {
					bMove(d);
					rMove(d);
				}
				break;
			case 3:
				if(ry >= by) {
					bMove(d);
					rMove(d);
				}else {
					rMove(d);
					bMove(d);
				}
				break;
			case 4:
				if(ry >= by) {
					rMove(d);
					bMove(d);
				}else {
					bMove(d);
					rMove(d);
				}
				break;
			}
			
			if(bGole) {
				break;
			}
			
			if(rGole) {
				if(i < min) {
					min = i;
				}
				break;
			}
		}
		return false;
	}
	
	
	private static void rMove(int d) {
		int rx = tmpR[0];
		int ry = tmpR[1];
		tmpmap[rx][ry] = ".";
		int nx = rx + dx[d];
		int ny = ry + dy[d];
		while(true) {
			if(tmpmap[nx][ny].equals("#")) {
				tmpR[0] = nx - dx[d];
				tmpR[1] = ny - dy[d];
				tmpmap[tmpR[0]][tmpR[1]] = "R";
				break;
			}else if(tmpmap[nx][ny].equals("B")) {
				tmpR[0] = nx - dx[d];
				tmpR[1] = ny - dy[d];
				tmpmap[tmpR[0]][tmpR[1]] = "R";
				break;
			}else if(tmpmap[nx][ny].equals("O")) {
				rGole = true;
				break;
			}
			nx = nx + dx[d];
			ny = ny + dy[d];
		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
	}
	
	private static void bMove(int d) {
		int bx = tmpB[0];
		int by = tmpB[1];
		tmpmap[bx][by] = ".";
		int nx = bx + dx[d];
		int ny = by + dy[d];
		while(true) {
			if(tmpmap[nx][ny].equals("#")) {
				tmpB[0] = nx - dx[d];
				tmpB[1] = ny - dy[d];
				tmpmap[tmpB[0]][tmpB[1]] = "B";
				break;
			}else if(tmpmap[nx][ny].equals("R")) {
				tmpB[0] = nx - dx[d];
				tmpB[1] = ny - dy[d];
				tmpmap[tmpB[0]][tmpB[1]] = "B";
				break;
			}else if(tmpmap[nx][ny].equals("O")) {
				bGole = true;
				break;
			}
			nx = nx + dx[d];
			ny = ny + dy[d];
		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
	}
}
