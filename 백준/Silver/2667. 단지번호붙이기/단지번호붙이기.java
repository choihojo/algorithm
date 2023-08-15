import java.io.*;
import java.util.*;

// 방문체크 배열 만들고 true일 경우 시작
// 한번이라도 방문하면 false로 만듦 (houseCnt++)
// 자료구조가 비면 cnt++ (단지 하나 추가)
// 자료구조가 빌 때 houseCnt 저장
// dfs, bfs 모두 가능할 것으로 보임

public class Main {
//	단지 수 카운팅 변수
	static int cnt;
//	단지당 집의 수 카운팅 변수
	static int houseCnt;
//	집의 수 저장할 배열
	static int[] houseList;
//	전체 집 상태 저장할 배열
	static int[][] map;
//	방문체크 배열
	static boolean[][] visited;
	static int N;
//	상하좌우 연결되었는지 판단하기 위한 방향 배열 (극좌표처럼 반시계로 돌림)
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static String str;
	
	public static void dfsByRecursion(int[] start) {
//		start를 기점으로 4방위 탐색 (사실 4방위가 아니라 위를 제외한 3방위도 될 것 같음)
//		어차피 위에서부터 좌에서 우로 탐색하기 때문에 위는 검사할 필요가 없을 것 같은 느낌
		for (int i = 0; i < 4; i++) {
			int row = start[0] + dRow[i];
			int col = start[1] + dCol[i];
//			1칸 나아간 인덱스가 전체 인덱스 범위를 초과하는지 확인
			if (row >= 0 && row < N && col >= 0 && col < N) {
//				만약 그 곳에 방문한 적 없고 갈 수 있는 곳(1)이라면 조건문에 진입
//				굳이 방문체크 배열을 쓰지 않고 지나갔던 곳을 0으로 바꾸는 방식으로도 가능함
				if (!visited[row][col] && map[row][col] == 1) {
//					방문 체크
					visited[row][col] = true;
//					집의 개수 추가
					houseCnt++;
//					거길 시작으로 다시 dfs 돌림
//					System.out.println(row + "|" + col); // dfs 과정을 볼 수 있음
					dfsByRecursion(new int[] {row, col});
				}
			}
		}
	}
	
	public static void dfsByStack(int[] start) {
		Stack<int[]> stack = new Stack<>();
		stack.push(start);
		int[] peek;
		boolean flag;
		int pRow;
		int pCol;
		int row;
		int col;
		
		while (!stack.isEmpty()) {
			peek = stack.peek();
			flag = false;
			pRow = peek[0];
			pCol = peek[1];
			for (int i = 0; i < 4; i++) {
				row = pRow + dRow[i];
				col = pCol + dCol[i];
				if (row >= 0 && row < N && col >= 0 && col < N) {
					if (!visited[row][col] && map[row][col] == 1) {
						visited[row][col] = true;
						stack.push(new int[] {row, col});
						flag = true;
						houseCnt++;
						break;
					}
				}
			}
			
			if (!flag) {
				stack.pop();
			}
		}
	}
	
	public static void bfsByQueue(int[] start) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(start);
		int[] poll;
		int pRow;
		int pCol;
		int row;
		int col;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pRow = poll[0];
			pCol = poll[1];
			
			for (int i = 0; i < 4; i++) {
				row = pRow + dRow[i];
				col = pCol + dCol[i];
				if (row >= 0 && row < N && col >= 0 && col < N) {
					if (!visited[row][col] && map[row][col] == 1) {
						visited[row][col] = true;
						queue.offer(new int[] {row, col});
						houseCnt++;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
//		배열 크기 주의
		houseList = new int[N * N];
		
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
//				이렇게 말고 map[i][j] = str.charAt(j) - '0';이 더 깔끔한 듯
				map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
//					우선 방문되지 않은 곳 중에서 1인 곳이 있었다는 건 거길 시작으로 하는 단지가 존재한다는 뜻임
					cnt++;
//					최소 집의 수가 1개는 된다는 것이므로 houseCnt 1로 초기화
					houseCnt = 1;
//					방문했다고 판정
					visited[i][j] = true;
//					dfsByRecursion으로 단지 탐색
					dfsByRecursion(new int[] {i, j});
//					N번째 단지의 집의 수는 (N - 1)번째에 저장됨 (0번째부터 시작하므로)
					houseList[cnt - 1] = houseCnt;
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (!visited[i][j] && map[i][j] == 1) {
//					cnt++;
//					houseCnt = 1;
//					visited[i][j] = true;
////					dfsByStack으로 단지 탐색
//					dfsByStack(new int[] {i, j});
//					houseList[cnt - 1] = houseCnt;
//				}
//			}
//		}
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				if (!visited[i][j] && map[i][j] == 1) {
//					cnt++;
//					houseCnt = 1;
//					visited[i][j] = true;
////					bfsByQueue로 단지 탐색
//					bfsByQueue(new int[] {i, j});
//					houseList[cnt - 1] = houseCnt;
//				}
//			}
//		}
		
//		집의 수를 cnt - 1까지만 오름차순으로 정렬 (그 이후까지 하는 건 무의미함)
		Arrays.sort(houseList, 0, cnt);
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for (int i = 0; i < cnt; i++) {
			sb.append(houseList[i]).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		
		System.out.println(sb.toString());
	}
}