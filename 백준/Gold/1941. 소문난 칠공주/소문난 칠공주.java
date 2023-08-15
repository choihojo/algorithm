import java.io.*;
import java.util.*;

public class Main {
//	전체 s와 y에 대한 맵으로 s면 true로, y면 false로 저장함
	static boolean[][] map;
	static int sSize;
	static int ySize;
//	sList 인덱싱을 편하게 하기 위해 sList의 사이즈로 만든 배열
	static int[] sArr;
//	yList 인덱싱을 편하게 하기 위해 yList의 사이즈로 만든 배열
	static int[] yArr;
//	s의 인덱스를 저장할 리스트
	static List<Integer> sList;
//	y의 인덱스를 저장할 리스트
	static List<Integer> yList;
//	7공주가 몇 가지 경우의 수가 나오는지 카운팅할 변수
	static int cnt = 0;
//	7공주의 인덱스를 저장할 배열
	static int[] seven = new int[7];
//	7명이 인접해있는지 확인하기 위한 배열과 카운팅 변수
	static boolean[][] tempMap;
	static int tempCnt;
//	4방위 탐색을 위한 배열
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	
//	s를 goal명만큼 뽑는 메서드 (내부에 y를 goal명만큼 뽑는 메서드를 추가로 호출함)
	public static void sCombination(int goal, int sCnt, int sStart) {
		if (sCnt == goal) {
//			s를 goal명만큼 뽑았으면 seven에는 (goal - 1)번째 인덱스까지 저장된 거임
//			이제 y로 goal번째부터 6번째 인덱스까지 뽑으면 됨 (총 7명)
//			cnt가 배열 저장 위치를 의미하므로 goal번째부터 뽑으면 되기 때문에 goal을 패러미터로 넣어줌
//			sCombination에서 goal명을 뽑고 yCombination에서 (7 - goal)명을 뽑으면 총 7명이 완성됨
//			사실상 이 부분이 코드의 핵심임
			yCombination(7, goal, 0);
			return;
		}
		for (int i = sStart; i < sSize; i++) {
			seven[sCnt] = sList.get(i);
			sCombination(goal, sCnt + 1, i + 1);
		}
	}
	
	public static void yCombination(int goal, int yCnt, int yStart) {
		if (yCnt == goal) {
//			7공주 위치는 true고 나머지 위치는 모두 false로 초기화
//			tempCnt도 0으로 초기화
			initTempMap();
//			tempMap을 바탕으로 첫 번째 7공주(seven[0])를 시작으로 bfs 탐색
//			모두 카운팅되어 7이 되는지 확인 (7이 되면 모두 인접해있다는 뜻이고 아니면 떨어져있다는 뜻임)
			if (bfsByQueue()) cnt++;
			return;
		}
		for (int i = yStart; i < ySize; i++) {
			seven[yCnt] = yList.get(i);
			yCombination(goal, yCnt + 1, i + 1);
		}
	}
	
	public static void initTempMap() {
		tempMap = new boolean[5][5];
		tempCnt = 0;
		for (int i = 0; i < 7; i++) {
			tempMap[seven[i] / 5][seven[i] % 5] = true;
		}
	}
	
	public static boolean bfsByQueue() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[5][5];
//		첫 번째 7공주 큐에 넣어줌
		queue.offer(new int[] {seven[0] / 5, seven[0] % 5});
//		방문 체크까지 완료
//		만약 tempMap을 boolean 배열이 아닌 int 배열로 선언하고 최단경로 알고리즘을 이용하면 방문체크 배열을 쓰지 않아도 됨
//		어느 쪽으로 풀든 크게 차이나지는 않을 듯
		visited[seven[0] / 5][seven[0] % 5] = true;
		
//		일단 시작이 7공주이므로 tempCnt++
		tempCnt++;
		
//		poll한 좌표 변수
		int[] poll;
		int pRow;
		int pCol;
//		한 칸 이동한 좌표 변수
		int row;
		int col;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pRow = poll[0];
			pCol = poll[1];
			
			for (int i = 0; i < 4; i++) {
				row = pRow + dRow[i];
				col = pCol + dCol[i];
				if (row >= 0 && row < 5 && col >= 0 && col < 5) {
//					4방위 중 하나로 이동한 위치가 방문한 적 없고 7공주 위치가 맞다면 방문체크하고 tempCnt++
					if (tempMap[row][col] && !visited[row][col]) {
						queue.offer(new int[] {row, col});
						visited[row][col] = true;
						tempCnt++;
					}
				}
			}
		}
//		7명이 맞는지 여부 반환
		return tempCnt == 7;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		map = new boolean[5][5];
		sList = new ArrayList<>();
		yList = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			str = br.readLine();
			for (int j = 0; j < 5; j++) {
//				1행 2열은 5 * 0 + 1
//				3행 5열은 5 * 2 + 3
//				이런 식으로 이차원 좌표를 일차원 좌표로 치환했음 (일차원 인덱스로 바꿔서 조합 돌리기 위함)
				if (str.charAt(j) == 'S') {
					map[i][j] = true;
					sList.add(5 * i + j);
				}
				else {
					yList.add(5 * i + j);
				}
			}
		}
		
//		나올 수 있는 경우의 수
//		s:y
//		4:3 -> sList에서 4개를 뽑고 yList에서 3개를 뽑은 뒤 7개를 모두 true로 만든 맵에서 bfs로 영역이 모두 카운팅되는지 확인
//		5:2
//		6:1
//		7:0
		sSize = sList.size();
		ySize = yList.size();	
		
//		인덱싱 편하게 하기 위해서 각 리스트의 크기만큼 인덱싱 배열을 만듦
		sArr = new int[sSize];
		yArr = new int[ySize];
		for (int i = 0; i < sSize; i++) {
			sArr[i] = i;
		}
		for (int i = 0; i < ySize; i++) {
			yArr[i] = i;
		}
		
//		한 메서드 안에서 s에 대한 조합, y에 대한 조합을 모두 수행해야 할 듯
		if (sSize >= 4 && sSize <= 7) {
//			사실 이 조건문 없이 무조건 4부터 7까지 sCombination을 돌려도 어차피 답은 동일하게 나옴
//			왜냐하면 sSize가 4인데 6을 패러미터로 넣으면 sCombination에서 yCombination을 호출하지 못하고 종료되기 때문임
//			다만 이렇게 하면 필요없는 계산을 줄일 수 있을 것 같아 넣었음
			for (int i = 4; i <= sSize; i++) {
				sCombination(i, 0, 0);
			}
		}
		else if (sSize > 7) {
			for (int i = 4; i <= 7; i++) {
				sCombination(i, 0, 0);
			}
		}
		System.out.println(cnt);
	}
}