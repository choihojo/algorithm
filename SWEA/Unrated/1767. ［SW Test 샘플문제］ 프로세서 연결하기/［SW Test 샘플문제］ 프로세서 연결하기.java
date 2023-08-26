import java.io.*;
import java.util.*;

// 멕시노스는 N * N
// 한 칸에는 코어 혹은 전선이 올 수 있음
// 가장 자리에는 전원이 흐름 (가장자리까지 전선을 연결해야 함)
// 전선은 직선으로만 설치 가능하고 교차 불가능함
// 가장자리에 있는 코어는 이미 전원에 연결되어 있는 것이고 전선 길이에 포함되지 않음

// 최대한 많은 코어에 전원을 연결(1)했을 경우 전선 길이의 합(2)을 구해야함
// 만약 똑같은 개수의 코어에 연결하는 방법이 여러 가지일 경우 전선 길이의 최소가 되는 값을 구해야함
// N은 7 이상 12 이하
// 코어의 개수는 1개 이상 12개 이하
// 반드시 모든 코어에 연결될 필요는 없음

// 60개 테케 합쳐서 4초
// 일단 바로 느껴지는 건 코어를 4방향으로 다 돌려보면 됨
// 최대 가짓수 : 4^12 == 2^24 == 400만 -> 완탐 가능할 듯 -> 시간초과남
// 중복순열 활용 -> DFS로 백트래킹 써야할 듯

public class Solution {
	static int T;
	static int N;
	static int[][] map;
	static List<int[]> core;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static int coreCnt;
	static int length;
	static int maxCoreCnt;
	static int minLength;

	static void dfs(int cnt) {
		if (cnt == core.size()) {
//			코어 카운트가 기존 맥스보다 크면 무조건 길이를 갱신해야함
			if (coreCnt > maxCoreCnt) {
				maxCoreCnt = coreCnt;
				minLength = length;
			}
//			코어 카운트가 같은 경우 길이가 더 짧은 경우에만 최소길이 갱신함
			else if (coreCnt == maxCoreCnt) {
				if (length < minLength) minLength = length;
			}
			return;
		}
		
//		coreCnt는 현재까지 누적된 연결된 코어의 수를 의미함
//		(core.size - cnt)는 앞으로 최대로 늘릴 수 있는 연결된 코어의 개수를 의미함
//		결국 coreCnt + core.size() - cnt는 현재 시점에서 이론상 가능한 최대 코어의 개수를 의미함
		if (maxCoreCnt > (coreCnt + core.size() - cnt)) return;
		
//		코어 하나에 대해 4방향 중 하나로 끝까지 나아가보고 다음 코어 호출
		for (int i = 0; i < 4; i++) {
			int oRow = core.get(cnt)[0];
			int oCol = core.get(cnt)[1];
			int row = oRow + dRow[i];
			int col = oCol + dCol[i];
			boolean flag = true;
			
			while (row >= 0 && row < N && col >= 0 && col < N) {
//				여기서 바로 -1로 바꾸면 절대 안됨
//				전원에 연결될 수 있을 경우에만 확정으로 전선을 깔아야함
//				그리고 가장자리에 있는 코어부터 고려해야될 듯 -> 생각해보니 자동으로 가장자리부터 고려되는 효과가 생김
//				왜냐면 가장자리에 코어가 있는 경우 어차피 전선이 깔리다 막히기 때문임
//				이게 자동으로 고려돼서 문제 난이도가 훨씬 내려간 듯함 -> 시간 초과 이슈...
//				모든 경우 고려하는 건 사실상 힘듦
				if (map[row][col] != 0) {
					flag = false;
					break;
				}
				row += dRow[i];
				col += dCol[i];
			}
			
//			만약 flag가 참이라면 전선을 깔 수 있다는 뜻임
			if (flag) {
//				전선을 깔 수 있으므로 카운팅
				coreCnt++;
				row = oRow + dRow[i];
				col = oCol + dCol[i];
//				전선 설치
				while (row >= 0 && row < N && col >= 0 && col < N) {
					map[row][col] = -1;
					length++;
					row += dRow[i];
					col += dCol[i];
				}
			}
			
			dfs(cnt + 1);
			
//			원상복구
			if (flag) {
				coreCnt--;
				row = oRow + dRow[i];
				col = oCol + dCol[i];
				while (row >= 0 && row < N && col >= 0 && col < N) {
					map[row][col] = 0;
					length--;
					row += dRow[i];
					col += dCol[i];
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
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			core = new ArrayList<>();
			maxCoreCnt = Integer.MIN_VALUE;
			minLength = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				srr = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(srr[j]);
//					코어 위치 좌표 core 리스트에 추가
//					가장자리는 모든 경우에 대해 공통이므로 고려할 필요 없음
					if (i == 0 || i == (N - 1) || j == 0 || j == (N - 1)) continue;
					if (map[i][j] == 1) core.add(new int[] {i, j}); 
				}
			}
			
			dfs(0);
			
			sb.append("#").append(t).append(" ").append(minLength).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}