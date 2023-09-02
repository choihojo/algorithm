import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][][] dp;
    static int[][] map;
    
//    가로, 대각선, 세로 순으로 0, 1, 2 인덱싱
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N][N][3];
        map = new int[N][N];
        
        for (int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
//        for (int[] irr : map) {
//        	System.out.println(Arrays.toString(irr));
//        }
        
//      인덱스 예외 방지하기 위해 0번째 라인 초기화해놓고 DP 수행
        for (int j = 1; j < N; j++) {
//        	가다가 벽이 있으면 못 가는 곳이므로 0으로 초기화해야함
        	if (map[0][j] == 1) break;
            dp[0][j][0] = 1;
        }
        
        for (int i = 1; i < N; i++) {
            for (int j = 2; j < N; j++) {
//            	원하는 목표 영역에 벽이 없어야 이동 가능함
            	if (map[i][j] == 1) continue;
            	
//            	왼쪽에서 오는 경우 (이동 후 누워져 있음)
                dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
                
//              위에서 오는 경우 (이동 후 세워져 있음)
                dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
                
//              대각선으로 오는 경우 왼쪽과 위쪽 영역에도 벽이 없어야 함
                if (map[i][j - 1] == 1 || map[i - 1][j] == 1) continue;
                
//              대각선에서 오는 경우 (이동 후 기울어져 있음)
                dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
            }
        }
        
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(dp[i][j][0] + dp[i][j][1] + dp[i][j][2]);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
        
        System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
    }
}