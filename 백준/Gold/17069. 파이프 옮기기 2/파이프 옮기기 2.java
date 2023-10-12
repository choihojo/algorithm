import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//가로로 들어왔는가, 새로로 들어왔는가, 대각선으로 들어왔는가
public class Main {
	static int N; 
	static long map[][][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(bf.readLine());
		
		map = new long[N][N][4];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j][0] = Long.parseLong(st.nextToken());
			}
		}
		
		int dxdy[][][] = new int[][][] {{{0,1},{1,1}},{{1,0},{1,1}},{{0,1},{1,0},{1,1}}};
		
		map[0][1][1] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < N; j++) {
				for(int k = 1; k < 4; k++) { //가로 새로 대각선 성분이 있는지 검사
					if(map[i][j][k] != 0) {
						for(int z = 0; z < dxdy[k-1].length; z++) {
							int dx = i + dxdy[k-1][z][0];
							int dy = j + dxdy[k-1][z][1];
							if(dx < N && dy < N && map[dx][dy][0] != 1) {
								if(k == 1 && z == 0) {
									map[dx][dy][1] += map[i][j][k];
								}else if(k == 1 && z == 1) {
									if(map[dx][dy - 1][0] == 1 || map[dx - 1][dy][0] == 1) {
										continue;
									}
									map[dx][dy][3] += map[i][j][k];									
								}else if(k == 2 && z == 0) {
									map[dx][dy][2] += map[i][j][k];
								}else if (k == 2 && z == 1){
									if(map[dx][dy - 1][0] == 1 || map[dx - 1][dy][0] == 1) {
										continue;
									}
									map[dx][dy][3] += map[i][j][k];
								}else if(k == 3) {
									if(z == 2) {
										if(map[dx][dy - 1][0] == 1 || map[dx - 1][dy][0] == 1) {
											continue;
										}
									}
									map[dx][dy][z+1] += map[i][j][k];
								}
							}
						}
					}
				}
			}
		}
		System.out.println(map[N-1][N-1][1]+map[N-1][N-1][2]+map[N-1][N-1][3]);
	}
}
