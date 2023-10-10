import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int N,H,W,map[][],res[];
	static int min;
	static int C;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(bf.readLine());
			min = Integer.MAX_VALUE;
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			res = new int[N];
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			per(0);
//			System.out.println("#" + tc + " " + min);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void per(int cnt) { //공을 어느 위치에 떨어트릴지 중복 순열 생성
		if(cnt == N) {
//			System.out.println(Arrays.toString(res));
//			if(res[0] == 2 && res[1] == 2 && res[2] == 6) {
				toDo(); //완성된 순열을 바탕으로 시뮬레이션				
//			}
			if(C < min) { //시뮬레이션 결과값 저장
				min = C;
			}
			return;
		}
		
		for(int i = 0; i < W; i++) {
			res[cnt] = i;
			per(cnt + 1);
		}
	}
	
	static int tmpmap[][];
	private static void toDo() { //공을 떨어트리게 된다. N개의 공을 떨어트리므로 N번 반복을 돌릴까
		//시뮬레이션에  사용될 임시 맵 생성
		tmpmap = new int[H][W];
		for(int i = 0; i < H; i++) {
			tmpmap[i] = Arrays.copyOf(map[i], W);
			
		}
		//떨어진 공에 의해서 깨지는 벽돌 찾기
		for(int i = 0; i < N; i++) {
			int x = 0;
			int y = res[i];
			
			while(x < H) {
				if(tmpmap[x][y] != 0) {
					break;
				}else {
					x++;
				}
			}
			
			if(x == H) {
				continue;
			}
			//깨지는 벽돌의 연쇄작용 찾기
			findnext(x,  y, tmpmap);
			
			//깨진 후 모습
			afterBreak(tmpmap);
		}
//		for(int i = 0; i < H; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
		C = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0 ; j < W; j++) {
				if(tmpmap[i][j] != 0) {
					C++;
				}
			}
		}
//		System.out.println(C);
	}

	private static void afterBreak(int[][] tmpmap) {
		ArrayList<Integer> list;
		for(int i = 0; i < W; i++) {
			list = new ArrayList<>();
			for(int j = H - 1; j >= 0; j--) {
				if(tmpmap[j][i] != 0) {
					list.add(tmpmap[j][i]);
					tmpmap[j][i] = 0;
				}
			}
			int size = list.size();
			for(int j = H - 1; j >= H - size; j--) {
				tmpmap[j][i] = list.remove(0);
			}
		}
	}

	private static void findnext(int x, int y, int[][] tmpmap) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int temp[] = q.poll();
			int cx = temp[0];
			int cy = temp[1];
//			System.out.println(cx + " " + cy);
			int size = tmpmap[cx][cy];
			tmpmap[cx][cy] = 0;
			if(size == 1) {
				continue;
			}
			int udx = cx - 1;
			int rdy = cy + 1;
			int ldy = cy - 1;
			int ddx = cx + 1;				
			while(size > 1) {
				if(udx >= 0 && tmpmap[udx][cy] != 0) {
					q.offer(new int[] {udx, cy});
				}
				if(rdy < W && tmpmap[cx][rdy] != 0) {
					q.offer(new int[] {cx, rdy});
				}
				if(ddx < H && tmpmap[ddx][cy] != 0) {
					q.offer(new int[] {ddx, cy});
				}
				if(ldy >= 0 && tmpmap[cx][ldy] != 0) {
					q.offer(new int[] {cx, ldy});
				}
				udx = udx - 1;
				rdy = rdy + 1;
				ldy = ldy - 1;
				ddx = ddx + 1;				
				size--;
			}
		}
//		for(int i = 0; i < H; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
	}
}
