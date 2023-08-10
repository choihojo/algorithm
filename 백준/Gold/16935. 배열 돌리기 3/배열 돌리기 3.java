import java.io.*;

public class Main {
	static int N;
	static int M;
	static int R;
	static int[][] irr;
	static int[][] orr;
	static int[] seq;
	
	static int rowMin;
	static int rowMid1;
	static int rowMid2;
	static int rowMax;
	
	static int colMin;
	static int colMid1;
	static int colMid2;
	static int colMax;
	
	
	public static void array1() {
		reset();
		orr = new int[N][M];
		for (int row = rowMin; row <= rowMax; row++) {
			for (int col = colMin; col <= colMax; col++) {
				orr[rowMax - row][col] = irr[row][col];
			}
		}
		irr = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				irr[i][j] = orr[i][j];
			}
		}
	}
	
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
		
		for (int i : seq) {
			if (i == 1) array1();
			else if (i == 2) array2();
			else if (i == 3) array3();
			else if (i == 4) array4();
			else if (i == 5) array5();
			else if (i == 6) array6();
		}
		
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