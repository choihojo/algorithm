import java.io.*;
import java.util.*;
 
public class Solution {
    static int T;
    static int N, K;
    static int[][] map;
    static int max;
    static int maxLength;
    static List<int[]> list;
    static boolean[][] visited;
    static int[] dRow = new int[] {0, -1, 0, 1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    
    static void dfs(int a, int b, int length, boolean flag, boolean end) {
        if (end) {
            if (maxLength < length) maxLength = length;
            return;
        }
        int oRow = a;
        int oCol = b;
//        앞으로 1씩 내려가서 등산로를 만들더라도 최댓값을 갱신할 수 없다면 프루닝
        if ((map[oRow][oCol] + length) < maxLength) return;
         
        for (int i = 0; i < 4; i++) {
            int row = oRow + dRow[i];
            int col = oCol + dCol[i];
            if (row < 0 || row >= N || col < 0 || col >= N || visited[row][col]) dfs(row, col, length, flag, true);
            else {
                if (map[row][col] < map[oRow][oCol]) {
                    visited[row][col] = true;
                    dfs(row, col, length + 1, flag, end);
                    visited[row][col] = false;
                }
//              만난 위치가 기존 위치보다 같거나 높은 경우 깎아야 함
//              이전에 깎은 적이 없었다면(flag == false) 1번 깎을 수 있음
                else {
//                	만약 깎은 적이 있었다면 이제 못 깎는 것이므로 end를 참으로 만들고 다음 재귀로 넘겨서 종료시킴
                    if (flag) dfs(row, col, length, flag, true);
                    else {
                        for (int k = 1; k <= K; k++) {
//                        	높이를 k == 1부터 깎아감
                            if ((map[row][col] - k) < map[oRow][oCol]) {
                                visited[row][col] = true;
                                map[row][col] -= k;
                                dfs(row, col, length + 1, true, end);
                                visited[row][col] = false;
                                map[row][col] += k;
                            }
                            else dfs(row, col, length, flag, true);
                        }
                    }
                }
            }
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        String[] srr;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            maxLength = 0;
            max = 0;
            srr = br.readLine().split(" ");
            N = Integer.parseInt(srr[0]);
            K = Integer.parseInt(srr[1]);
            list = new ArrayList<>();
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                srr = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(srr[j]);
                    if (map[i][j] > max) max = map[i][j];
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == max) list.add(new int[] {i, j});
                }
            }
            for (int i = 0; i < list.size(); i++) {
                visited[list.get(i)[0]][list.get(i)[1]] = true;
                dfs(list.get(i)[0], list.get(i)[1], 1, false, false);
                visited[list.get(i)[0]][list.get(i)[1]] = false;
            }
            sb.append("#").append(t).append(" ").append(maxLength).append("\n");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}