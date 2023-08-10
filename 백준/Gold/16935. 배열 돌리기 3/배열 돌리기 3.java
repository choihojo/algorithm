import java.io.*;

public class Main {
//	입력으로 받을 N, M, R 변수 선언
	static int N;
	static int M;
	static int R;
	
//	입력 배열과 출력 배열
	static int[][] irr;
	static int[][] orr;
	
//	연산 순서 저장할 배열
	static int[] seq;
	
//	5번과 6번 연산에 대비해 미리 인덱싱
//	행은 rowMin~rowMid1, rowMid2~rowMax로 이등분
//	관련 식은 reset() 참고
	static int rowMin;
	static int rowMid1;
	static int rowMid2;
	static int rowMax;
	
//	열은 colMin~colMid1, colMid2~colMax로 이등분
	static int colMin;
	static int colMid1;
	static int colMid2;
	static int colMax;
	
//	상하 반전 연산
//	인덱스 변화를 관찰하면 쉽게 짤 수 있음
//	주의할 점은 reset() 부분
//	출력 배열의 크기가 입력 배열과 동일한 연산의 경우에는 별 상관이 없음
//	하지만 크기가 다른 경우 reset()을 통해 N, M을 입력 배열의 행과 열로 초기화하는 과정이 필요함
	public static void array1() {
		reset();
		orr = new int[N][M];
		for (int row = rowMin; row <= rowMax; row++) {
			for (int col = colMin; col <= colMax; col++) {
				orr[rowMax - row][col] = irr[row][col];
			}
		}
//		출력 배열을 입력 배열에 복사하여 다음 연산을 가능하게 함
//		예를 들어 1번째 연산의 출력 배열이 2번째 연산에서는 입력이 되기 때문임
		irr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				irr[i][j] = orr[i][j];
			}
		}
	}
	
//	좌우 반전 연산
	public static void array2() {
		reset();
		orr = new int[N][M];
		for (int row = rowMin; row <= rowMax; row++) {
			for (int col = colMin; col <= colMax; col++) {
				orr[row][colMax - col] = irr[row][col];
			}
		}
		irr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				irr[i][j] = orr[i][j];
			}
		}
	}
	
//	오른쪽 90도 회전 연산
	public static void array3() {
		reset();
		orr = new int[M][N];
		for (int row = rowMin; row <= rowMax; row++) {
			for (int col = colMin; col <= colMax; col++) {
				orr[col][rowMax - row] = irr[row][col];
			}
		}
		irr = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				irr[i][j] = orr[i][j];
			}
		}
	}
	
//	왼쪽 90도 회전 연산
	public static void array4() {
		reset();
		orr = new int[M][N];
		for (int row = rowMin; row <= rowMax; row++) {
			for (int col = colMin; col <= colMax; col++) {
				orr[colMax - col][row] = irr[row][col];
			}
		}
		irr = new int[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				irr[i][j] = orr[i][j];
			}
		}
	}
	
//	부분 배열로 나누고 시계 방향으로 돌리는 연산
	public static void array5() {
		reset();
		orr = new int[N][M];
		for (int row = rowMin; row <= rowMid1; row++) {
			for (int col = colMin; col <= colMid1; col++) {
				orr[row][col + colMid2] = irr[row][col];
			}
		}
		for (int row = rowMin; row <= rowMid1; row++) {
			for (int col = colMid2; col <= colMax; col++) {
				orr[row + rowMid2][col] = irr[row][col];
			}
		}
		for (int row = rowMid2; row <= rowMax; row++) {
			for (int col = colMid2; col <= colMax; col++) {
				orr[row][col - colMid2] = irr[row][col];
			}
		}
		for (int row = rowMid2; row <= rowMax; row++) {
			for (int col = colMin; col <= colMid1; col++) {
				orr[row - rowMid2][col] = irr[row][col];
			}
		}
		irr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				irr[i][j] = orr[i][j];
			}
		}
	}
	
//	부분 배열로 나누고 반시계 방향으로 돌리는 연산
	public static void array6() {
		reset();
		orr = new int[N][M];
		for (int row = rowMin; row <= rowMid1; row++) {
			for (int col = colMin; col <= colMid1; col++) {
				orr[row + rowMid2][col] = irr[row][col];
			}
		}
		for (int row = rowMin; row <= rowMid1; row++) {
			for (int col = colMid2; col <= colMax; col++) {
				orr[row][col - colMid2] = irr[row][col];
			}
		}
		for (int row = rowMid2; row <= rowMax; row++) {
			for (int col = colMid2; col <= colMax; col++) {
				orr[row - rowMid2][col] = irr[row][col];
			}
		}
		for (int row = rowMid2; row <= rowMax; row++) {
			for (int col = colMin; col <= colMid1; col++) {
				orr[row][col + colMid2] = irr[row][col];
			}
		}
		irr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				irr[i][j] = orr[i][j];
			}
		}
	}
	
	public static void reset() {
		N = irr.length;
		M = irr[0].length;
		rowMin = 0;
		rowMid1 = (N / 2 - 1);
		rowMid2 = (N / 2);
		rowMax = (N - 1);
		
		colMin = 0;
		colMid1 = (M / 2 - 1);
		colMid2 = (M / 2);
		colMax = (M - 1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		R = Integer.parseInt(srr[2]);
		irr = new int[N][M];
		seq = new int[R];
		
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				irr[i][j] = Integer.parseInt(srr[j]);
			}
		}
		
		srr = br.readLine().split(" ");
		for (int i = 0; i < R; i++) {
			seq[i] = Integer.parseInt(srr[i]);
		}
		
//		연산 순서에 맞게 각 연산을 호출함
		for (int i : seq) {
			if (i == 1) array1();
			else if (i == 2) array2();
			else if (i == 3) array3();
			else if (i == 4) array4();
			else if (i == 5) array5();
			else if (i == 6) array6();
		}
		
//		각 줄 마지막에는 띄어쓰기 안 하도록 했음
//		개행문자의 경우에도 입력 마지막에는 안 되게 함
//		이렇게 하든 하지 않든 똑같이 정답인 듯?
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < orr.length; i++) {
			for (int j = 0; j < orr[0].length; j++) {
				sb.append(orr[i][j]);
				if (j != (orr[0].length - 1)) sb.append(" ");
			}
			if (i != (orr.length - 1)) sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
