import java.io.*;

// 행과 열의 번호는 1부터 시작함
// 파이프는 2개의 연속된 칸을 차지함
// 끝점이 (1, 2)에서 (N, N)으로 이동하는 방법의 개수를 구해야함
// 빈 칸은 0이고 벽은 1
// N은 3 이상 16 이하

public class Main {
	static int N;
	static int[][] map;
	static int[] dRow = new int[] {0, 1, 1};
	static int[] dCol = new int[] {1, 0, 1};
	static int cnt;
	
	static void dfs(int oRow, int oCol, int type) {
		if (oRow == (N - 1) && oCol == (N - 1)) {
			cnt++;
			return;
		}
		
		int row = 0;
		int col = 0;
//		현재 상태의 타입이 가로(0)일 때는 가로로 밀거나 대각선으로 밀 수 있음
		if (type == 0) {
			row = oRow + dRow[0];
			col = oCol + dCol[0];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 0) dfs(row, col, 0);
			}
			row = oRow + dRow[2];
			col = oCol + dCol[2];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 0 && map[row - 1][col] == 0 && map[row][col - 1] == 0) dfs(row, col, 2);
			}
		}
//		현재 상태의 타입이 세로(1)일 때는 세로로 밀거나 대각선으로 밀 수 있음
		else if (type == 1) {
			row = oRow + dRow[1];
			col = oCol + dCol[1];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 0) dfs(row, col, 1);
			}
			row = oRow + dRow[2];
			col = oCol + dCol[2];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 0 && map[row - 1][col] == 0 && map[row][col - 1] == 0) dfs(row, col, 2);
			}
		}
//		현재 상태의 타입이 대각선(2)일 때는 가로로 밀거나 세로로 밀거나 대각선으로 밀 수 있음
		else {
			row = oRow + dRow[0];
			col = oCol + dCol[0];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 0) dfs(row, col, 0);
			}
			row = oRow + dRow[1];
			col = oCol + dCol[1];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 0) dfs(row, col, 1);
			}
			row = oRow + dRow[2];
			col = oCol + dCol[2];
			if (row >= 0 && row < N && col >= 0 && col < N) {
				if (map[row][col] == 0 && map[row - 1][col] == 0 && map[row][col - 1] == 0) dfs(row, col, 2);
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		String[] srr;
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(srr[j]);
			}
		}
//		끝점이 (0, 1)에서 (N - 1, N - 1)까지 와야 됨
//		가로타입(0), 세로타입(1), 대각선타입(2)
		dfs(0, 1, 0);
		System.out.println(cnt);
	}
}