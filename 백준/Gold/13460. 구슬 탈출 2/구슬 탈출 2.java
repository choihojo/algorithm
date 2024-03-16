import java.io.*;
import java.util.*;

public class Main {
    /*
     * N * M 크기의 보드에 빨간 구슬과 파란 구슬을 하나씩 넣은 다음
     * 빨간 구슬을 구멍을 통해 빼내야 함
     * 파란 구슬이 구멍에 들어가면 안됨
     * 기울이기 가능한 방향: 상하좌우
     * 반드시 빨간 구슬이 먼저 구멍에 빠져야 함 (같이도 불가능함)
     * 
     * 최소 몇 번의 기울임 행위를 통해 빨간 구슬을 구멍으로 빼낼 수 있을지?
     * 더 이상 구슬이 움직이지 않을 때 기울임 행위를 멈춤 (무조건 해당 방향으로 끝까지 감)
     * 만약 10번 이하로 움직여서 빨간 구슬을 빼낼 수 없으면 -1을 출력함
     * 
     * '.': 빈 칸
     * '#': 공이 이동할 수 없는 장애물
     * 'o': 구멍의 위치 (목표 위치)
     * 'R': 빨간 구슬의 위치 (구멍에 들어가야 함)
     * 'B': 파란 구슬의 위치 (구멍에 들어가면 안됨)
     * 
     * R 이동은 방문체크 해야 되고
     * B 이동은 방문체크 안해도 될 듯함
     */
    
    static BufferedReader br;
    static StringTokenizer st;
    static int N, M;
    static boolean[][] visited;
    static char[][] map;
    static int[][] marble;
    static int[] dRow = new int[] {0, -1, 0, 1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    static int cnt;
    static int result = -1;
    
    /*
     * 기본적으로 BFS 알고리즘을 이용함
     */
    
    static void bfs() {
    	Queue<int[][]> queue = new ArrayDeque<>();
    	
    	queue.offer(new int[][] {{marble[0][0], marble[0][1]}, {marble[1][0], marble[1][1]}});
    	
    	int[][] pMarble = new int[2][2];
    	int[][] dMarble = new int[2][2];
    	
    	outer : while (!queue.isEmpty()) {
    		cnt++;
    		int size = queue.size();
    		for (int s = 0; s < size; s++) {
    			pMarble = queue.poll();
    			inner : for (int i = 0; i < 4; i++) {
    				dMarble = new int[][] {{pMarble[0][0], pMarble[0][1]}, {pMarble[1][0], pMarble[1][1]}};
    				/*
    				 * blue 구슬부터 체크
    				 */
    				while (true) {
    					if (map[dMarble[1][0] + dRow[i]][dMarble[1][1] + dCol[i]] == '#' ||
    							(dMarble[1][0] + dRow[i] == dMarble[0][0] && dMarble[1][1] + dCol[i] == dMarble[0][1])) {
        					break;
    					} else if (map[dMarble[1][0] + dRow[i]][dMarble[1][1] + dCol[i]] == 'O') {
    						continue inner; 
    					} else {
    						dMarble[1][0] += dRow[i];
        					dMarble[1][1] += dCol[i];
    					}
    				}
    				/*
    				 * red 구슬 체크
    				 */
    				while (true) {
    					if (map[dMarble[0][0] + dRow[i]][dMarble[0][1] + dCol[i]] == '#' ||
    							(dMarble[0][0] + dRow[i] == dMarble[1][0] && dMarble[0][1] + dCol[i] == dMarble[1][1])) {
    	    				while (true) {
    	    					if (map[dMarble[1][0] + dRow[i]][dMarble[1][1] + dCol[i]] == '#' ||
    	    							(dMarble[1][0] + dRow[i] == dMarble[0][0] && dMarble[1][1] + dCol[i] == dMarble[0][1])) {
    	        					break;
    	    					} else if (map[dMarble[1][0] + dRow[i]][dMarble[1][1] + dCol[i]] == 'O') {
    	    						continue inner; 
    	    					} else {
    	    						dMarble[1][0] += dRow[i];
    	        					dMarble[1][1] += dCol[i];
    	    					}
    	    				}
        					break;
    					} else if (map[dMarble[0][0] + dRow[i]][dMarble[0][1] + dCol[i]] == 'O') {
    						/*
    						 * red 구슬이 빠졌을 경우 blue 구슬도 동시에 빠졌는지 다시 한번 확인해야 함
    						 * 이번에는 red 구슬이 있더라도 사실상 구멍으로 빠졌으므로 그냥 지나쳐야 함
    						 */
    	    				while (true) {
    	    					if (map[dMarble[1][0] + dRow[i]][dMarble[1][1] + dCol[i]] == '#') {
    	        					break;
    	    					} else if (map[dMarble[1][0] + dRow[i]][dMarble[1][1] + dCol[i]] == 'O') {
    	    						continue inner; 
    	    					} else {
    	    						dMarble[1][0] += dRow[i];
    	        					dMarble[1][1] += dCol[i];
    	    					}
    	    				}
    						result = cnt;
    						break outer;
    					} else {
    						dMarble[0][0] += dRow[i];
        					dMarble[0][1] += dCol[i];
    					}
    				}
    				queue.offer(new int[][] {{dMarble[0][0], dMarble[0][1]}, {dMarble[1][0], dMarble[1][1]}});
    			}
    		}
    		if (cnt == 10) {
    			break;
    		}
    	}
    }
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new char[N][M];
        marble = new int[2][2];
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = str.charAt(m); 
                if (map[n][m] == 'R') {
                    marble[0][0] = n;
                    marble[0][1] = m;
                    map[n][m] = '.';
                } else if (map[n][m] == 'B') {
                	marble[1][0] = n;
                	marble[1][1] = m;
                	map[n][m] = '.';
                }
            }
        }
        bfs();
        System.out.println(result);
    }
}