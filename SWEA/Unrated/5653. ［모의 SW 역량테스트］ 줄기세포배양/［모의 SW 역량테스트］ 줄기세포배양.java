import java.io.*;
import java.util.*;
 
public class Solution {
    static int T;
    static int N;
    static int M;
    static int K;
    static int[][] map;
    static boolean[][] visited;
    static int cnt;
     
    static int[] dRow = new int[] {0, -1, 0, 1};
    static int[] dCol = new int[] {1, 0, -1, 0};
     
    static void bfs() {
//    	카운팅 초기화
        cnt = 0;
        Queue<Cell> queue = new ArrayDeque<>();
        List<Cell> input = new ArrayList<>();
         
//      값이 0보다 크면 세포가 존재한다는 것이므로 방문 체크한 뒤 input 리스트에 추가
//      바로 큐에 넣는 게 아니라 리스트에서 생명력이 높은 세포가 앞에 오도록 정렬한 뒤 넣어야 함
        for (int i = K; i < K + N; i++) {
            for (int j = K; j < K + M; j++) {
                if (map[i][j] > 0) {
                    visited[i][j] = true;
                    input.add(new Cell(i, j, map[i][j], map[i][j], map[i][j]));
                }
            }
        }
        
//        생명력을 기준으로 내림차순으로 정렬
        Collections.sort(input);
         
//        큐에 삽입
        for (int i = 0; i < input.size(); i++) {
            queue.offer(input.get(i));
        }
         
//      배양 시간동안 큐 반복
        for (int time = 0; time < K; time++) {
//        	한 타임동안에는 큐의 사이즈만큼 poll을 하면 됨 (사실상 depth 체크)
            int size = queue.size();
             
            for (int s = 0; s < size; s++) {
                Cell poll = queue.poll();
                int pRow = poll.row;
                int pCol = poll.col;
                
//              여기에 조건문 넣으면 안 됨 (매우 중요)
//              예를 들어 이렇게 하면 3시간 후에 분열시키고 난 뒤 죽어야 하는 세포가 다음 타임이 되어야 죽게 됨
//              결국 분열시키고 나서 해당 타임에 1을 빼주고 0이면 바로 map의 값을 바꿔줘야 함
//              life가 0이면 죽은 것이므로 해당 위치의 값을 0으로 바꿔주고 다음 poll로 넘어감
//                if (poll.life == 0) {
//                	map[pRow][pCol] = 0;
//                	continue;
//                }
                
//              세포가 활성화 상태(남은 기간인 active가 0)고 살아있으면(life가 양수) 분열
                if (poll.active == 0) {
                    if (poll.life > 0) {
                        for (int i = 0; i < 4; i++) {
                            int row = pRow + dRow[i];
                            int col = pCol + dCol[i];

                            if (row >= 0 && row < (N + 2 * K) && col >= 0 && col < (M + 2 * K)) {
                                if (!visited[row][col]) {
//                                  우선 인덱스 초과 체크하고 분열한 적 없는 위치면 조건문 진입
                                    visited[row][col] = true;
//                                  분열된 세포를 큐에 새로 넣어줌 (새로 생겨난 세포이므로 active와 life도 power로 초기화해야함)
                                    queue.offer(new Cell(row, col, poll.power, poll.power, poll.power));
//                                  비활성 세포와 활성 세포의 개수를 카운팅해야 하므로 1로 표시 (딱히 생명력이 1이라는 것하고는 관련 없음)
//                                  사실상 세포의 모든 정보는 Cell에 담겨있기 때문에 map은 단순히 카운팅할 용도로 사용함
                                    map[row][col] = 1;
                                }
                            }
                        }
//                      분열이 끝났으므로 life를 1 줄임
                        poll.life--;
//                      life가 0이라면 해당 위치를 0으로 바꿈
                        if (poll.life == 0) map[pRow][pCol] = 0;
//                      만약 그래도 life가 남아있다면 다시 큐에 넣어줌
                        else queue.offer(new Cell(pRow, pCol, poll.power, poll.active, poll.life));  
                    }
                }
//              active가 0이 아니므로 세포가 비활성화된 상태이므로 활성화될 때까지의 기간을 1 줄여서 다시 큐에 넣어줌
                else queue.offer(new Cell(pRow, pCol, poll.power, poll.active - 1, poll.life));
            }
        }
        
//      0이 아닌 위치가 아직 비활성화된 세포거나 활성화된 세포이므로 카운팅
        for (int i = 0; i < N + 2 * K; i++) {
            for (int j = 0; j < M + 2 * K; j++) {
                if (map[i][j] != 0) cnt++;
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        String[] srr;
        StringBuilder sb = new StringBuilder();
         
        for (int t = 1; t <= T; t++) {
            srr = br.readLine().split(" ");
            N = Integer.parseInt(srr[0]);
            M = Integer.parseInt(srr[1]);
            K = Integer.parseInt(srr[2]);
//          최대 분열 고려해서 넉넉하게 크기 설정해서 초기화
            map = new int[N + 2 * K][M + 2 * K];
            visited = new boolean[N + 2 * K][M + 2 * K];
             
            for (int i = K; i < K + N; i++) {
                srr = br.readLine().split(" ");
                for (int j = K; j < K + M; j++) {
                    map[i][j] = Integer.parseInt(srr[j - K]);
                }
            }
            bfs();
            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
 

class Cell implements Comparable<Cell> {
    public int row;
    public int col;
//    세포의 생명력
    public int power;
//    비활성 상태의 세포가 활성 상태로 진입하기까지 남은 시간
    public int active;
//    활성 상태의 세포가 죽기까지 남은 시간
    public int life;
     
    public Cell (int row, int col, int power, int active, int life) {
        this.row = row;
        this.col = col;
        this.power = power;
        this.active = active;
        this.life = life;
    }
 
    @Override
    public int compareTo(Cell o) {
//      생명력이 더 높은 세포가 앞에 오도록 내림차순으로 정렬
        return o.power - this.power;
    }
}