import java.io.*;
import java.util.*;

// 1인 모든 지점에서 동시에(같은 day) bfs로 1칸씩 가야함

public class Main {
	static String[] srr;
	static BufferedReader br;
	static int M;
	static int N;
	static int[][] map;
//	방문체크 배열이 필요함
//	왜 필요한지 이해하는 게 중요한데 이미 주위 상하좌우로 익게 한 토마토의 경우 살펴볼 필요가 없음
	static boolean[][] bMap;
	static int cnt;
	static int total;
	static int day;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static List<int[]> start;
	
	public static boolean bfsByQueue(List<int[]> start) {
		Queue<int[]> queue = new ArrayDeque<>();
		int[] poll;
		int row;
		int col;
		int pRow;
		int pCol;
		int size;
		boolean flag;
		
		for (int i = 0; i < start.size(); i++) {
			int tempRow = start.get(i)[0];
			int tempCol = start.get(i)[1];
//			시작점들은 poll하고 4방위를 고려하는 게 확정이므로 미리 여기서 방문체크해줌
//			이 문제에서는 방문체크 자체가 답에 영향을 주는 건 아니지만 시간을 유의미하게 개선해줄 것으로 보임
			bMap[tempRow][tempCol] = true;
			queue.offer(new int[] {tempRow, tempCol});
		}
		
//		while문에서 탈출하면 모든 익을 수 있는 토마토는 익은 상태임
		while (!queue.isEmpty()) {
//			익은 토마토 개수가 변했는지 체크하기 위한 변수
//			만약 이걸 안 넣으면 목표 값보다 +1된 값이 나옴
//			offer 없이 단순히 poll만 하는(익은 토마토 개수 변화X) 경우에도 day++가 이루어지기 때문임
			flag = false;
			
//			큐의 사이즈를 재는 것이 곧 day를 계산하는 것이고 핵심임
//			지금 큐의 사이즈만큼 poll하고 그에 따른 4방위 요소들을 넣어줘야 함
//			현재 큐에 (1일 1일 1일) 이렇게 들어있다고 할 때 (2일 2일 2일, ..., 2일) 이렇게 될 때까지 빼주는 것임
			size = queue.size();
			
			for (int s = 0; s < size; s++) {
				poll = queue.poll();
				pRow = poll[0];
				pCol = poll[1];
				
				for (int i = 0; i < 4; i++) {
					row = pRow + dRow[i];
					col = pCol + dCol[i];
					if (row >= 0 && row < N && col >= 0 && col < M) {
//						bMap을 쓰면 이미 고려된 곳은 바로 넘어가서 속도 개선이 이뤄질 것으로 예상됨
//						근데 bMap 안 써도 통과되긴 함
						if (!bMap[row][col]) {
//							여기까지 조건을 통과했으면 poll한 노드의 4방위 이동 후 노드를 방문했다는 것이므로 체크
//							-1을 방문체크해놓으면 다음번에 애초에 들어올 필요도 없음
//							0이어도 아래에서 1로 바꾸고 큐에 넣어주는 순간 역할을 다한 거임
//							값이 1인 좌표는 애초에 이 조건문에 들어올 수 없음 (시작할 때의 1들은 이미 방문체크를 해놨었음)
//							나중에 익어서 생기는 1은 아래 조건문을 통과한 0에서 바뀐 1들인데 그 전에 방문체크를 하는 코드가 있기 때문임
							bMap[row][col] = true;
							if (map[row][col] == 0) {
								map[row][col] = 1;
								queue.offer(new int[] {row, col});
								cnt++;
								flag = true;
							}
						}
					}
				}
			}
			
			if (flag) {
				day++;	
			}
		}
//		이 반환문 하나면 처음부터 익은 토마토가 하나도 없는 경우, 모두 익었을 경우 등 모두 고려 가능함
		return cnt == total;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		srr = br.readLine().split(" ");
		M = Integer.parseInt(srr[0]);
		N = Integer.parseInt(srr[1]);
		map = new int[N][M];
//		bMap이 핵심인데 아래 루프문 설명 참고
		bMap = new boolean[N][M];
		start = new ArrayList<>();
		
//		며칠이 지났는지 확인하기 위한 변수
		day = 0;
//		익지 않은 토마토의 개수
		total = 0;
//		현재까지 몇 개를 익게 했는지 알려주는 변수
		cnt = 0;
		
		for (int n = 0; n < N; n++) {
			srr = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(srr[m]);
//				익게 해야되는 토마토의 개수 카운팅 (목표 개수)
				if (map[n][m] == 0) {
					total++;
				}
//				처음 시작할 때 익은 토마토의 좌표를 리스트에 추가
				else if (map[n][m] == 1) {
					start.add(new int[] {n, m});
				}
			}
		}
		
//		bfsByQueue가 참이면 토마토를 모두 익게 할 수 있다는 것이고 day 출력
//		그렇지 않을 경우 -1 출력
		if (bfsByQueue(start)) {
			System.out.println(day);
		}
		else {
			System.out.println(-1);
		}
	}
}