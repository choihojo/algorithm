import java.io.*;
public class Main {
//	입력으로 받을 N, M, K 선언
	static int N;
	static int M;
	static int K;
	
//	배열에서 최솟값을 체크할 때 임시로 쓸 변수 temp 선언
//	최종 최솟값으로 쓸 변수 min 선언 (temp가 min보다 작을 때마다 갱신)
	static int temp = 0;
	static int min = Integer.MAX_VALUE;
	
//	회전 순서가 달라질 때마다 원래 배열에서부터 다시 시작해야 하므로 원래값을 저장하고 있을 origin 배열 선언
	static int[][] origin;
	
//	회전 순서의 매 회차에 따라 입력과 출력으로 쓸 irr과 orr 배열 선언
	static int[][] irr;
	static int[][] orr;
	
//	회전 종류를 저장할 배열 선언
	static int[][] rotation;
	
//	회전 순서를 결정할 배열 선언 (인덱싱으로 순서 결정)
//	sequence는 입력 배열로 회전 종류가 3가지라면 {0, 1, 2}가 됨
//	outSequence는 이러한 sequence를 토대로 만들어질 순열을 임시로 저장할 배열
	static int[] sequence;
	static int[] outSequence;
	
//	sequence의 순열을 만들 때 쓸 방문 체크 배열
	static boolean[] visited;
	
//	입력 배열과 r, c, s를 토대로 회전시켜 출력 배열을 만드는 함수
	public static void rotate(int[][] irrPar, int[][] orrPar, int r, int c, int s) {
		
//		인덱싱하기 편하게 r에서 1을 뺀 값에 s를 더하고 빼줌
//		회전할 영역의 최소 인덱스와 최대 인덱스
//		rowNow와 colNow는 회전시킬 때 임시로 쓸 변수
		int rowMin = (r - 1 - s);
		int rowMax = (r - 1 + s);
		int rowNow = -1;
		int colMin = (c - 1 - s);
		int colMax = (c - 1 + s);
		int colNow = -1;
		
//		s가 1이면 중앙을 기준(0번째)으로 1번째 테두리를 회전시킴
//		s가 2이면 중앙을 기준으로 1번째 테두리와 2번째 테두리를 회전시킴
//		이 결과를 종합하면 총 s의 크기에 해당하는 개수의 테두리를 회전시키면 됨
//		i = 0일 때는 회전 영역의 가장 바깥쪽 테두리
//		i = 1일 때는 i = 0일 때보다 상하좌우로 한 칸씩 줄어든 테두리 이런 식
//		만약 배열 돌리기2처럼 회전 수가 많으면 테두리별로 회전 수를 나눠서 계산해야 하지만 여기서는 딱 1번만 회전시키는 것이라 불필요함
		for (int i = 0; i < s; i++) {
			
//			테두리를 4개 영역으로 나누어 회전시킴
			for (int flag = 0; flag < 4; flag++) {
//				위쪽 테두리를 시계 방향으로 1칸 옮김
//				주의할 점은 6*6 영역을 회전시킨다고 할 때 위쪽 테두리의  0~5 인덱스를 1~6 인덱스로으로 옮기면 된다는 것임
//				0~6의 인덱스를 옮기는 것이 아님에 주의할 것 (인덱스 초과 예외가 예상됨)
				if (flag % 4 == 0) {
					rowNow = rowMin;
					for (int col = colMin; col < colMax; col++) {
						orrPar[rowNow][col + 1] = irrPar[rowNow][col];
					}
				}
//				오른쪽 테두리를 시계 방향으로 1칸 옮김
				else if (flag % 4 == 1) {
					colNow = colMax;
					for (int row = rowMin; row < rowMax; row++) {
						orrPar[row + 1][colNow] = irrPar[row][colNow];
					}
				}
//				아래쪽 테두리를 시계 방향으로 1칸 옮김
				else if (flag % 4 == 2) {
					rowNow = rowMax;
					for (int col = colMax; col > colMin; col--) {
						orrPar[rowNow][col - 1] = irrPar[rowNow][col];
					}
				}
//				왼쪽 테두리를 시계 방향으로 1칸 옮김
				else if (flag % 4 == 3) {
					colNow = colMin;
					for (int row = rowMax; row > rowMin; row--) {
						orrPar[row - 1][colNow] = irrPar[row][colNow];
					}
				}
			}
			
//			바깥쪽 테두리를 돌렸으므로 상하좌우로 한 칸씩 줄여 다음은 안쪽 테두리가 되게 함
			rowMin++;
			rowMax--;
			colMin++;
			colMax--;
		}
		
//		입력 배열을 출력 배열로 바꿔줌
//		출력 배열을 다시 입력으로 넣어서 회전시켜 다음 출력 배열을 만들어야하기 때문임
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				irrPar[i][j] = orrPar[i][j];
			}
		}
	}
	
//	최솟값을 갱신하는 함수
//	어차피 각 행의 최솟값들 중에서 최솟값을 찾는 것이라 행마다 갱신하면 됨
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
	
//	회전 종류 수에 따른 기본 수열(sequence)를 토대로 만들어질 수 있는 회전 순서에 대한 경우의 수를 계산함
//	모든 변수를 static으로 선언해 각 경우의 수마다 바로바로 회전시켜서 최솟값을 갱신시켰음
	public static void permutation(int[] sequence, int[] outSequence, boolean[] visited, int cnt) {
		if (cnt == K) {
//			각 경우의 수마다 irr과 orr 원본 배열로 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					irr[i][j] = origin[i][j];
					orr[i][j] = origin[i][j];
				}
			}
			
//			회전 순서에 따라 회전시킴
			for (int i = 0; i < K; i++) {
				rotate(irr, orr, rotation[outSequence[i]][0], rotation[outSequence[i]][1], rotation[outSequence[i]][2]);
			}
			
//			최솟값 갱신
			calculate(orr);
			return;
		}
		
//		재귀를 이용하여 순열의 경우의 수를 구함
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
		
//		원본 배열 저장
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				origin[i][j] = Integer.parseInt(srr[j]);
			}
		}
		
//		회전 종류를 의미하는 rotation 배열과 각 회전에 대응되는 인덱스로 이루어진 배열 sequence 초기화
		for (int k = 0; k < K; k++) {
			srr = br.readLine().split(" ");
			for (int l = 0; l < 3; l++) {
				rotation[k][l] = Integer.parseInt(srr[l]);
			}
			sequence[k] = k;
		}
		
//		sequence를 토대로 만들어진 outSequence에 따라 회전시키고 최솟값을 갱신하는 permutation 함수 실행
		permutation(sequence, outSequence, visited, 0);
		
//		최종 최솟값 출력
		System.out.println(min);
	}
}