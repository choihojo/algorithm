import java.io.*;
import java.util.*;

// 연결 관계를 인접행렬로 표현할 것인가? -> 이 문제에서 1, 0은 연결 정보를 나타내는 것이 아님 -> 적절하지 않음
// 연결 관계를 인접리스트로 표현할 것인가? -> 마찬가지로 이 문제에서 1, 0은 연결 정보를 나타내는 것이 아님 -> 적절하지 않음
// 인접행렬이나 인접리스트로 풀 수 있는 문제려면 노드가 여러개 있고 노드 간 연결 여부를 입력으로 받을 수 있어야 함
// (1, 1)에서 시작했을 때 (2, 1)로 갈 수 있는데 이 연결 정보를 따로 저장하지 않는 한 DFS와 BFS 문제처럼 접근하면 안될 듯함
// 만약 저장한다고 하더라도 저장 배열의 크기는 (N*M) * (N*M)이라 좋은 방법이 아닌 듯

// 일단 전체 맵을 이차원 배열에다 저장함
// DFS는 너무 깊게 들어가면 빠져나오지 못할 수 있으므로 BFS가 적절해 보임
// 상하좌우 이동방향에 대한 배열을 만들고 각 좌표에서 1이면 이동하고 0이면 이동하지 않음


public class Main {
	public static int bfs(int[][] irr, int[] start, int[] target) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(start);
		
//		poll한 성분과 4방향을 종합하여 얻는 이동 결과 좌표
		int row = 0;
		int col = 0;
		
//		poll한 성분의 row와 col
		int pRow = 0;
		int pCol = 0;
		
//		4방향 표현
		int[] dRow = {0, -1, 0, 1};
		int[] dCol = {1, 0, -1, 0};
		int[] poll;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pRow = poll[0];
			pCol = poll[1];
			for (int i = 0; i < 4; i++) {
				row = pRow + dRow[i];
				col = pCol + dCol[i];
				if (row >= 1 && row < irr.length && col >= 1 && col < irr[1].length) {
//					방문체크 배열을 따로 만들 수도 있지만 여기에서는 더한 거리가 다시 저장되기 때문에 이미 왔던 곳(2 이상)일 경우에는 이렇게만 해도 가지 않음
					if (irr[row][col] == 1) {
						queue.offer(new int[] {row, col});
						irr[row][col] = irr[pRow][pCol] + 1;
					}
				}
			}
		}
//		목표점의 값(거기까지 누적된 거리)을 출력
		return irr[target[0]][target[1]];
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int N = Integer.parseInt(srr[0]);
		int M = Integer.parseInt(srr[1]);
		String str;
//		인덱싱 편하게 하기 위해서 1 더 크게 설정
		int[][] irr = new int[N + 1][M + 1];
		for (int n = 1; n <= N; n++) {
			str = br.readLine();
			for (int m = 1; m <= M; m++) {
				irr[n][m] = Integer.parseInt(String.valueOf(str.charAt(m - 1)));
			}
		}
		System.out.println(bfs(irr, new int[] {1, 1}, new int[] {N, M}));
	}
}