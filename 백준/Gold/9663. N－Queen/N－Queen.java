import java.io.*;
public class Main {
	static int N;
	static int[] rowCol;
//	열에 대한 방문체크 배열 (행은 어차피 자동으로 1행 뽑고, 2행 뽑고, ... 이런 식이라서 방문체크 배열 필요없음)
	static boolean[] visitedCol;
//	왼쪽 위에서 오른쪽 아래로 내려가는 대각선 방문체크배열
	static boolean[] visitedRightDia;
//	오른쪽 위에서 왼쪽 아래로 내려가는 대각선 방문체크배열
	static boolean[] visitedLeftDia;
	static int cnt;
//	static StringBuilder sb = new StringBuilder();
	
	public static void nQueen(int row) {
		if (row > N) {
//			for (int i = 1; i <= N; i++) {
//				sb.append(rowCol[i]).append("\n");
//			}
//			sb.deleteCharAt(sb.length() - 1);
//			System.out.println(sb.toString());
//			System.exit(0);
			cnt++;
			return;
		}
		
		for (int i = 1; i <= N; i++) {
//			rowCol은 해당 행에서 몇 번째 열에 퀸을 놓았는지 저장한 배열
//			예를 들어 rowCol[1] = 1이라면 1행에서 1열에 퀸을 놓았다는 뜻임
			rowCol[row] = i;
//			이전(사실상 row - 1) 상태까지 반영된 방문체크배열을 통해 현재(row) 행에 i열에 둘 수 있는지 체크하는 메서드
			if (isOkay(row)) {
				visitedCol[i] = true;
//				오른쪽으로 내려가는 대각선 성분들의 인덱스는 row - col이 일정함 -> 이걸 방문체크 배열의 인덱스 지표로 삼으면 됨
				visitedRightDia[row - i + N] = true;
//				왼쪽으로 내려가는 대각선 성분들의 인덱스는 row + col이 일정함 -> 이걸 방문체크 배열의 인덱스 지표로 삼으면 됨
				visitedLeftDia[row + i - 1] = true;
				nQueen(row + 1);
				visitedCol[i] = false;
				visitedRightDia[row - i + N] = false;
				visitedLeftDia[row + i - 1] = false;
			}
		}
	}
	
	public static boolean isOkay(int row) {
//		이 중에 하나라도 해당이 되면 서로 공격할 수 있다는 것이므로 return false로 그 이후의 노드 탐색 중지
		if (visitedCol[rowCol[row]] || visitedRightDia[row - rowCol[row] + N] || visitedLeftDia[row + rowCol[row] - 1]) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		rowCol = new int[N + 1];
		visitedCol = new boolean[N + 1];
		visitedRightDia = new boolean[2 * N];
		visitedLeftDia = new boolean[2 * N];
		nQueen(1);
		System.out.println(cnt);
	}
}