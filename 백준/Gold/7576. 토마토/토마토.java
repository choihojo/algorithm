import java.io.*;
import java.util.*;

// 설명은 이전 풀이에 자세하게 있음
// 이번엔 Data 클래스를 따로 만들고 최단경로 알고리즘을 이용하여 풀어봤음
public class Main {
	static int[][] map;
	static int M;
	static int N;
	static int day;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static boolean flag;
	
	public static void bfsByQueue() {
		Queue<Data> queue = new ArrayDeque<>();
		
//		처음 시작할 때 이미 익어있던 토마토들을 큐에 넣음
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] == 1) {
					queue.add(new Data(n, m));
				}
			}
		}
		
		Data poll;
		int pRow;
		int pCol;
		int row;
		int col;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pRow = poll.row;
			pCol = poll.col;
			
			for (int i = 0; i < 4; i++) {
				row = pRow + dRow[i];
				col = pCol + dCol[i];
//				배열 인덱스 초과 방지
				if (row >= 0 && row < N && col >= 0 && col < M) {
//					만약 한 칸 이동한 좌표의 값이 0(익지 않은 토마토)일 때만 이전 좌표에서 1을 더한 값으로 갱신함
//					이 조건문 덕분에 별도로 방문체크 배열이 필요없음
					if (map[row][col] == 0) {
						map[row][col] = map[pRow][pCol] + 1;
						queue.offer(new Data(row, col));
					}				
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		M = Integer.parseInt(srr[0]);
		N = Integer.parseInt(srr[1]);
		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			srr = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(srr[m]);
			}
		}
		
		bfsByQueue();
		
		flag = true;
		day = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
//				단 하나라도 0이 남아있다면 토마토가 다 익지 못했다는 것임
				if (map[n][m] == 0) {
					flag = false;
				}
//				처음 시작이 1이므로 총 익는 데 걸린 날은 map의 최댓값에서 1을 뺀 값임
//				어떤 좌표의 값에서 1을 빼면 그 좌표의 토마토가 익을 때까지 걸린 날임
				if (map[n][m] > day) {
					day = map[n][m] - 1;
				}
			}
		}
		
		if (flag) {
			System.out.println(day);
		}
		else {
			System.out.println(-1);
		}
		
	}
}

class Data {
	public int row;
	public int col;
	
	Data(int row, int col){
		this.row = row;
		this.col = col;
	}
}