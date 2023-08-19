import java.io.*;
import java.util.Arrays;

public class Main {
	static int N;
	static int M;
	static int D;
//	복사할 때 사용할 원본 배열
	static int[][] map;
//	매 조합마다 수행해야 하므로 복사할 위치
	static int[][] tempMap;
	static boolean[][] visited;
	static int[] dR = new int[] {0, -1};
	static int[] dC = new int[] {1, 0};
	static int[] input;
	static int[][] output;
	
//	디펜스 이전의 적의 수
	static int before;
//	디펜스 이후의 적의 수 -> (before - after)이 잡은 숫자임
	static int after;
//	최댓값을 저장할 변수
	static int max = 0;
	
	static void combination(int cnt, int start) {
		if (cnt == 3) {
//			뽑고 나서 디펜스 수행
			deffence();
			return;
		}
		for (int i = start; i < input.length; i++) {
//			0번째 궁수의 열을 뽑고 1번째 궁수의 열을 뽑고 2번째 궁수의 열을 뽑음
			output[cnt][1] = input[i];
			combination(cnt + 1, i + 1);
		}
	}
	
	static void deffence() {
//		배열 복사 및 초기화(사실상 조합의 수가 바뀔 때마다 초기화하는 것임) 
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				tempMap[n][m] = map[n][m];
				visited[n][m] = false;
			}
		}
		
//		궁수 배치 행좌표 초기화
		for (int i = 0; i < 3; i++) {
//			적들은 0부터 N - 1까지 포진해있음
			output[i][0] = N;
		}
		
//		적의 수 초기화
		after = 0;
		

//		궁수를 시간에 따라 전진시킴
//		왜냐면 적이 밑으로 내려오는 것이나 적이 위로 올라가는 것이나 똑같은 효과기 때문임
//		적의 탐색 범위를 전체에서 행 크기를 아래에서 1씩 줄일 필요가 있음 (궁수가 올라갔기 때문에 아래쪽 적은 사실상 놓쳐서 못 잡는 거임)
//		게임이 끝날 때는 궁수가 N번 행에 있을 때, ..., 1번 행에 있을 때임
		for (int t = 0; t < N; t++) {
//			3명의 궁수에 대해 공격하는 적들을 체크함
//			중복된 적들을 공격할 수 있기 때문에 문제가 간단해짐
//			한 타임 돌릴 때마다 공격하는 적들을 체크해놓고 끝나면 0으로 바꾸면 됨
			for (int i = 0; i < 3; i++) {
//				우선 거리 1에 대해 우선적으로 탐색하고 그 다음 2 이런 식으로 탐색함
//				만약 적을 찾았다면(방문체크 참으로 체크) 더이상 탐색하면 안되므로 아래 라벨링한 A위치까지 바로 탈출 (다음 궁수 탐색으로 넘어감)
				A : for (int d = 1; d <= D; d++) {
//					가능성 있는 적들에 대해 탐색함
//					탐색하는 행의 범위는 t를 이용하여 시간에 따라 1씩 줄어들게 함
//					궁수의 열을 기준으로 좌측 파트, 열 파트, 우측 파트 이렇게 나누어서 해야 함
//					탐색 위치는 좌측 파트, 열 파트는 밑에서부터 하고 우측 파트는 위에서부터 해야 함 (매우 중요함)
//					이렇게 하지 않으면 마름모 모양의 범위에 적이 여러 명이 있을 때 같은 거리의 왼쪽 적보다 같은 거리의 위쪽이나 오른쪽 적부터 잡음
					for (int n = N - 1 - t; n >= 0; n--) { // 열보다 좌측 파트에서 거리 d인 적 탐색
						for (int m = 0; m < output[i][1]; m++) {
//							여기서 d 이하가 아니라 정확히 d로 하는 게 불필요한 연산을 줄일 수 있을 듯 (디펜스를 생각해보면 어차피 d == 0인 경우는 존재하지 않음)
//							d == 1인 적은 최대 1명이고 다음 턴에 d == 0이 될 적은 애초에 잡아버리기 때문임
							if (tempMap[n][m] == 1 && ((Math.abs(output[i][0] - n) + Math.abs(output[i][1] - m)) == d)) {
								visited[n][m] = true;
								break A;
							}
						}
					}
					for (int n = N - 1 - t; n >= 0; n--) { // 같은 열에서 거리 d인 적 탐색
						if (tempMap[n][output[i][1]] == 1 && (Math.abs(output[i][0] - n) == d)) {
							visited[n][output[i][1]] = true;
							break A;
						}
					}
//					정말 중요한 부분인데 우측 파트를 검사할 때는 위에서부터 해야 같은 거리의 왼쪽 적부터 잡을 수 있음
//					위에서와 동일하게 밑에서부터 검사하면 같은 거리의 오른쪽 적을 잡아버림
//					이거 하나 때문에 2시간 날려먹었는데 답이 제대로 안 나오면 무조건 내가 잘못 짠 것이니 차분하게 디버깅할 필요가 있음
					for (int n = 0; n <= N - 1 - t; n++) { // 열보다 우측 파트에서 거리 d인 적 탐색
						for (int m = output[i][1] + 1; m < M; m++) {
							if (tempMap[n][m] == 1 && ((Math.abs(output[i][0] - n) + Math.abs(output[i][1] - m)) == d)) {
								visited[n][m] = true;
								break A;
							}
						}
					}
				}
			}
			
//			궁수의 적 탐색이 끝나면 공격한다고 표시된 적들을 0으로 바꿈
			for (int n = N - 1 - t; n >= 0; n--) {
				for (int m = 0; m < M; m++) {
					if (visited[n][m]) {
						tempMap[n][m] = 0;
					}
				}
			}
			
//			궁수를 한 칸 전진시킴
			for (int i = 0; i < 3; i++) {
//				적들은 0부터 N - 1까지 포진해있음
				output[i][0]--;
			}		
		}

		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (tempMap[n][m] == 1) {
					after++;
				}
			}
		}
		
		if ((before - after) > max) {
			max = (before - after);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		D = Integer.parseInt(srr[2]);
		
//		전체 맵 배치
		map = new int[N][M];
		tempMap = new int[N][M];
//		공격할 적 표시하는 배열
		visited = new boolean[N][M];
		
//		적의 수
		before = 0;
		for (int n = 0; n < N; n++) {
			srr = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(srr[m]);
//				적의 수 카운팅
				if (map[n][m] == 1) {
					before++;
				}
			}
		}
		
//		조합에 넣을 입력배열 (인덱스)
//		이 중 3개를 뽑아 궁수 배치함
		input = new int[M];
		for (int m = 0; m < M; m++) {
			input[m] = m;
		}
		
//		궁수는 3명
//		2차원으로 선언한 이유는 row와 col 좌표 때문임
		output = new int[3][2];
		
		combination(0, 0);
		System.out.println(max);
	}
}