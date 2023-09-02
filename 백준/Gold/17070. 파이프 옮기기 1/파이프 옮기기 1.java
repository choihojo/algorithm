import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][][] dp;
    static int[][] map;
    
//    가로, 대각선, 세로 순으로 0, 1, 2 인덱싱
    
    static long dfs(int row, int col, int type) {
    	if (row == 0 || col == 1) return dp[row][col][type];
    	if (map[row][col] == 1) return 0;
    	
    	if (dp[row][col][type] == -1) {
    		dp[row][col][type] = 0;
    		if (type == 0) {
    			dp[row][col][type] = dfs(row, col - 1, 0) + dfs(row, col - 1, 1);
    		}
    		else if (type == 1) {
    			if (map[row - 1][col] == 0 && map[row][col - 1] == 0) {
        			dp[row][col][type] = dfs(row - 1, col - 1, 0) + dfs(row - 1, col - 1, 1) + dfs(row - 1, col - 1, 2);
    			}
    		}
    		else if (type == 2) {
    			dp[row][col][type] = dfs(row - 1, col, 1) + dfs(row - 1, col, 2);
    		}
    	}
    	
    	return dp[row][col][type];
    }
    
    
    static void dfs2(int row, int col, int type) {
    	if (row == 0 || col == 1) return;
    	if (map[row][col] == 1) {
    		dp[row][col][type] = 0;
    		return;
    	}
    	
    	if (dp[row][col][type] == -1) {
    		dp[row][col][type] = 0;
    		if (type == 0) {
    			dfs2(row, col - 1, 0);
    			dfs2(row, col - 1, 1);
    			dp[row][col][type] = dp[row][col - 1][0] + dp[row][col - 1][1];
    		}
    		else if (type == 1) {
    			if (map[row - 1][col] == 0 && map[row][col - 1] == 0) {
    				dfs2(row - 1, col - 1, 0);
    				dfs2(row - 1, col - 1, 1);
    				dfs2(row - 1, col - 1, 2);
        			dp[row][col][type] = dp[row - 1][col - 1][0] + dp[row - 1][col - 1][1] + dp[row - 1][col - 1][2];
    			}
    		}
    		else if (type == 2) {
    			dfs2(row - 1, col, 1);
    			dfs2(row - 1, col, 2);
    			dp[row][col][type] = dp[row - 1][col][1] + dp[row - 1][col][2];
    		}
    	}
    }
    
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
        
//      인덱스 예외 방지하기 위해 0번째 라인 초기화해놓고 DP 수행
        for (int j = 1; j < N; j++) {
//        	가다가 벽이 있으면 못 가는 곳이므로 0으로 초기화해야함
        	if (map[0][j] == 1) break;
            dp[0][j][0] = 1;
        }
        
        for (int i = 1; i < N; i++) {
        	for (int j = 2; j < N; j++) {
        		for (int k = 0; k < 3; k++) {
        			dp[i][j][k] = -1;
        		}
        	}
        }
        
        dfs2(N - 1, N - 1, 0);
        dfs2(N - 1, N - 1, 1);
        dfs2(N - 1, N - 1, 2);
//        System.out.println(dfs(N - 1, N - 1, 0) + dfs(N - 1, N - 1, 1) + dfs(N - 1, N - 1, 2));
        long sum = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(sum);
    }
}