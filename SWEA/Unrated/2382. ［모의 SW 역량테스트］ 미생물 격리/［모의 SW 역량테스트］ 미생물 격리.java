import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
	static int N, M, K, map[][][];
	static ArrayList<int[]> list;
	static boolean arr[];
	static int[] dx = new int[] {0,-1,1,0,0};
	static int[] dy = new int[] {0,0,0,-1,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			arr = new boolean[K+1];
			int temp[][] = new int[N][N];
			list.add(new int[] {0,0,0,0});
			for(int i = 1; i <= K; i++) {
				st = new StringTokenizer(bf.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				list.add(new int[] {x,y,c,d});
//				System.out.println(Arrays.toString(list.get(i)));
				temp[x][y] = i;
			}
			
//			for(int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(temp[i]));
//			}
			
			while(M > 0) {
				for(int i = 1; i <= K; i++) {
					if(arr[i]) {
						continue;
					}
					
					int x = list.get(i)[0];
					int y = list.get(i)[1];
					int c = list.get(i)[2];
					int d = list.get(i)[3];
					
					int nx = x + dx[d];
					int ny = y + dy[d];
					
					if(nx == N - 1) {
						d = d - 1;
						c = c / 2;
					}else if(nx == 0) {
						d = d + 1;
						c = c / 2;
					}else if(ny == N - 1) {
						d = d - 1;
						c = c / 2;
					}else if(ny == 0){
						d = d + 1;
						c = c / 2;
					}
					
					if(c == 0) {
						arr[i] = true;
					}else {
						list.set(i, new int[] {nx, ny, c, d});						
					}
				}
				
				map = new int[N][N][4];
				Queue<int[]> q = new ArrayDeque<int[]>();
				for(int i = 1; i <= K; i++) {
					if(arr[i]) {
						continue;
					}
					int x = list.get(i)[0];
					int y = list.get(i)[1];
					int c = list.get(i)[2];
					int d = list.get(i)[3];
					
					int z = 0;
					while(map[x][y][z] != 0) {
						z++;
					}
					
					map[x][y][z] = i;
					if(z == 0) {
//						System.out.println(x + " " + y);
						q.offer(new int[] {x,y});						
					}
				}
				
				while(!q.isEmpty()) {
					int tmp[] = q.poll();
					int x = tmp[0];
					int y = tmp[1];
					int max = list.get(map[x][y][0])[2];
					int count = max;
					int maxidx = 0;
					for(int i = 1; i < 4; i++) {
						int ctmp = list.get(map[x][y][i])[2];
//						System.out.println(map[x][y][i]);
//						System.out.println(ctmp);
						if(max <= ctmp) {
							max = ctmp;
							arr[map[x][y][maxidx]] = true;
							maxidx = i;
						}else {
							arr[map[x][y][i]] = true;
						}
						count += ctmp;
					}
					list.get(map[x][y][maxidx])[2] = count;
				}
				M = M - 1;
			}
			
			int count = 0;
			for(int i = 1; i <= K; i++) {
				if(!arr[i]) {
//					System.out.println(i + " " + list.get(i)[2]);
					count += list.get(i)[2];
				}
			}
			
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		
		System.out.println(sb);
	}

}
