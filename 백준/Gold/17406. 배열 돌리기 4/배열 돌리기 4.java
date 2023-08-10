import java.io.*;
public class Main {
	static int N;
	static int M;
	static int K;
	static int temp = 0;
	static int min = Integer.MAX_VALUE;
	
	static int[][] origin;
	static int[][] irr;
	static int[][] orr;
	static int[][] rotation;
	static int[] sequence;
	static int[] outSequence;
	static boolean[] visited;
	
	
	public static void rotate(int[][] irrPar, int[][] orrPar, int r, int c, int s) {
		int rowMin = (r - 1 - s);
		int rowMax = (r - 1 + s);
		int rowNow = -1;
		int colMin = (c - 1 - s);
		int colMax = (c - 1 + s);
		int colNow = -1;
		
		for (int i = 0; i < s; i++) {
			
			for (int flag = 0; flag < 4; flag++) {
				if (flag % 4 == 0) {
					rowNow = rowMin;
					for (int col = colMin; col < colMax; col++) {
						orrPar[rowNow][col + 1] = irrPar[rowNow][col];
					}
				}
				else if (flag % 4 == 1) {
					colNow = colMax;
					for (int row = rowMin; row < rowMax; row++) {
						orrPar[row + 1][colNow] = irrPar[row][colNow];
					}
				}
				else if (flag % 4 == 2) {
					rowNow = rowMax;
					for (int col = colMax; col > colMin; col--) {
						orrPar[rowNow][col - 1] = irrPar[rowNow][col];
					}
				}
				else if (flag % 4 == 3) {
					colNow = colMin;
					for (int row = rowMax; row > rowMin; row--) {
						orrPar[row - 1][colNow] = irrPar[row][colNow];
					}
				}
			}
			
			rowMin++;
			rowMax--;
			colMin++;
			colMax--;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				irrPar[i][j] = orrPar[i][j];
			}
		}
	}
	
	public static void calculate(int[][] orr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp += orr[i][j];
			}
			if (temp < min) {
				min = temp;
			}
			temp = 0;
		}
	}
	
	public static void permutation(int[] sequence, int[] outSequence, boolean[] visited, int cnt) {
		if (cnt == K) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					irr[i][j] = origin[i][j];
					orr[i][j] = origin[i][j];
				}
			}
			for (int i = 0; i < K; i++) {
				rotate(irr, orr, rotation[outSequence[i]][0], rotation[outSequence[i]][1], rotation[outSequence[i]][2]);
			}
			calculate(orr);
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				outSequence[cnt] = sequence[i];
				visited[i] = true;
				permutation(sequence, outSequence, visited, cnt + 1);
				visited[i] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		K = Integer.parseInt(srr[2]);
		origin = new int[N][M];
		irr = new int[N][M];
		orr = new int[N][M];
		rotation = new int[K][3];
		sequence = new int[K];
		outSequence = new int[K];
		visited = new boolean[K];
		
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(srr[j]);
			}
		}
		
		for (int k = 0; k < K; k++) {
			srr = br.readLine().split(" ");
			for (int l = 0; l < 3; l++) {
				rotation[k][l] = Integer.parseInt(srr[l]);
			}
			sequence[k] = k;
		}
		
		permutation(sequence, outSequence, visited, 0);
		
		System.out.println(min);
	}
}