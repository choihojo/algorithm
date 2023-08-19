import java.io.*;
import java.util.*;

//	이전에는 for반복문으로 풀었고 이번에는 BFS로 풀었음
//	동시성 체크가 필요한데 거기에 더해 궁수 3명에 대해서 따로 돌려야 해서(아마..) 생각하는 게 굉장히 어려웠음
//	만약 궁수 3명을 같이 큐에 넣고 돌리게 되면 방문체크도 같이 돼서 도저히 방법이 생각나지 않았음 (토마토 같은 문제는 방문체크를 같이 해도 무방하지만 이건 같이하면 안될 느낌임)
//	내가 생각해낸 전체적인 풀이 흐름은 아래와 같음
//	조합으로 궁수 3명의 위치를 뽑음 ->
//	t=0일 때 궁수0에 대해 범위D까지 BFS (적 탐색 범위: 0행~(N-1)행) -> t=0일 때 궁수1에 대해 범위D까지 BFS -> t=0일 때 궁수2에 대해 범위D까지 BFS ->
//	공격하기로 선택된 적들을 0으로 바꾸고 cnt++
//	궁수 3명의 위치를 N에서 (N-1)로 올림 ->
//	t=1일 때 궁수0에 대해 범위D까지 BFs (적 탐색 범위: 0행~(N-2)행) -> t=1일 때 궁수1에 대해 범위D까지 BFS -> t=1일 때 궁수2에 대해 범위D까지 BFS -> ...
//	이렇게 하나의 경우의 수에 대한 디펜스가 끝나고 다음 경우의 수가 되면 위 과정 반복
public class Main {
	static int N, M, D;
//	원본 적 배열
	static int[][] map;
//	각 경우의 수마다 디펜스를 돌려야하므로 그 때마다 map을 복사한 tempMap을 사용함
	static int[][] tempMap;
//	조합의 수를 뽑을 때 사용할 인덱스 배열
	static int[] input;
//	input을 통해 뽑은 궁수의 위치
//	output이 2차원인 이유는 행의 인덱스와 input을 통해 뽑은 열의 인덱스를 같이 저장하기 때문임
	static int[][] output;
//	궁수의 위치를 중심으로 위쪽 마름모 범위 탐색을 할 때 쓸 방향좌표
//	왼쪽에 있는 적부터 잡아야 하기 때문에 좌, 상, 우 순으로 배치함
	static int[] dRow = new int[] {0, -1, 0};
	static int[] dCol = new int[] {-1, 0, 1};
//	궁수를 중심으로 퍼져나가는 BFS를 돌릴 때 쓸 방문체크 배열
	static boolean[][] visited;
//	한 라운드(예를 들어 t=1일 때)에서 공격할 적들을 표시할 배열
//	공격 배열을 따로 만들어서 관리해야 중복된 적을 공격하는 경우를 고려할 수 있음
	static boolean[][] attack;
//	매 경우의 수마다 잡은 궁수의 수가 기존 max보다 크면 갱신함
	static int max;
	
//	궁수가 3명이라 시간복잡도가 그리 부담되지 않아 NextPermutation을 이용하지 않고 그냥 빠르게 재귀로 짰음
	static void combination(int cnt, int start) {
		if (cnt == 3) {
//			궁수 3명을 뽑았으면 defenceByBfs 메서드 호출
			defenceByBfs();
			return;
		}
//		cnt번째 궁수의 1번째(열) 요소를 뽑음
		for (int i = start; i < input.length; i++) {
			output[cnt][1] = input[i];
			combination(cnt + 1, i + 1);
		}
	}
	
//	매 경우의 수마다 수행하는 디펜스
	static void defenceByBfs() {
//		조합 경우의 수에 따라 tempMap, attack 초기화
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				tempMap[n][m] = map[n][m];
				attack[n][m] = false;
			}
		}
		
//		행, 열 좌표로 쓰기 위해 int[]배열을 원소로 갖는 큐 선언
		Queue<int[]> queue = new ArrayDeque<>();
//		큐에서 poll할 때 쓸 변수
		int[] poll;
//		poll의 행, 열을 저장할 변수
		int pRow = 0;
		int pCol = 0;
//		poll의 행, 열에다 dRow, dCol을 고려한 변수 (3방향 탐색 좌표)
		int row = 0;
		int col = 0;
//		적이 내려온다고 생각하지 않고 궁수가 올라간다고 생각하기로 함
//		적이 내려온다고 생각하려면 배열을 매번 수정해줘야 함
//		근데 반대로 생각하면 t를 이용하여 궁수 좌표만 올리면 됨
		int t = 0;
		int cnt = 0;
		int size = 0;
		
//		t=0일 때(0번째 행부터 (N-1)번째 행까지 적이 위치해 있다고 했을 때 궁수는 N번째 행에 위치)부터 t=(N-1)일 때(궁수는 1번째 행에 위치)까지 디펜스가 진행됨
		for (t = 0; t < N; t++) {
//			조합에 나올 궁수의 위치 중 행의 좌표는 (N-t)로 초기화
//			이렇게 하면 궁수가 N번째 행에서 1번째 행까지 전진하게 됨 (예를 들어 1번째 행에 위치하면 0번째 행에 있는 적을 공격하고 디펜스가 종료되는 것임)
			for (int j = 0; j < 3; j++) {
				output[j][0] = N - t;
			}
//			궁수 3명에 대한 반복문
			for (int i = 0; i < 3; i++) {
//				i번째 궁수를 큐에 넣어줌
				queue.offer(output[i]);
//				궁수의 공격범위 D를 고려하기 위한 반복문 A(라벨링)
//				d=1~D까지 반복됨 (도중에 공격할 적 발견되면 A를 탈출해서 다음 궁수의 적 탐색이 시작됨)
				A : for (int d = 1; d <= D; d++) {
//					범위 d가 1일 때 공격 가능한 적이 있는지 살펴보고 d가 2일 때 있는지 살펴보고 이런 식으로 진행됨
//					그렇기 때문에 각 d의 경우에 따라 얼마나 큐에서 poll해줘야 되는지 알아야하고 그래서 큐의 사이즈만큼 반복문을 돌림
//					만약 이렇게 하지 않으면 D범위까지만 탐색하는 것이 아니라 무한정으로 퍼져나가서 탐색하게 됨
//					그냥 while(!queue.isEmpty())로 돌리고 매번 거리를 체크해서 거리가 사정거리를 벗어나면 break하는 방법도 있긴 함
					size = queue.size();
					for (int s = 0; s < size; s++) {
						poll = queue.poll();
						pRow = poll[0];
						pCol = poll[1];
//						poll한 위치를 시작으로 3방향 탐색 시작
						for (int j = 0; j < 3; j++) {
							row = pRow + dRow[j];
							col = pCol + dCol[j];
//							3방향 좌표가 인덱스 초과하는지 확인
//							row가 N이 아니라 (N-t)인 이유는 궁수가 올라감에 따라 탐색 범위도 1행씩 줄어들기 때문임
							if (row >= 0 && row < (N - t) && col >= 0 && col < M) {
//								만약 방문한 적이 없으면 방문체크
//								방문체크는 인덱스가 탐색범위 안에만 속하면 반드시 수행됨
								if (!visited[row][col]) {
									visited[row][col] = true;
									queue.offer(new int[] {row, col});
//									만약 방문한 적이 없었고 적이 있었다면(1) 공격할 위치로 표시하고 A 반복분 탈출(초기화 후 다음 궁수 탐색으로 넘어감)
									if (tempMap[row][col] == 1) {
										attack[row][col] = true;
										break A;
									}
								}
							}
						}
					}
				}
				
//				도중에 break된 경우 큐에 남아있을 수 있기 때문에 다음 궁수 탐색에 지장주지 않도록 큐 초기화
				queue.clear();
//				방문배열 초기화 (이전 궁수랑 방문체크 겹치면 3방향 탐색이 제대로 이뤄지지 않음)
				for (int n = 0; n < N; n++) {
					for (int m = 0; m < M; m++) {
						visited[n][m] = false;
					}
				}
			}
			
//			궁수 3명에 대한 공격위치 선정이 끝났으면 공격 배열을 토대로 tempMap에 반영함
			for (int n = 0; n < (N - t) ; n++) {
				for (int m = 0; m < M; m++) {
//					tempMap이 1이고 공격할 적이면 0으로 바꾸고 카운팅
//					tempMap이 1인지 체크하는 부분이 없으면 중복카운팅이 될 수 있음
					if (tempMap[n][m] == 1 && attack[n][m]) {
						tempMap[n][m] = 0;
						cnt++;
					}
				}
			}
		}
		
//		이번 경우의 수에서 cnt가 max보다 크면 max값 갱신
		if (cnt > max) {
			max = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
//		각종 변수 입력 및 초기화
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		D = Integer.parseInt(srr[2]);
		map = new int[N][M];
		tempMap = new int[N][M];
		visited = new boolean[N][M];
		attack = new boolean[N][M];
		for (int n = 0; n < N; n++) {
			srr = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(srr[m]);
			}
		}
		
//		조합에 쓸 input배열 초기화
		input = new int[M];
		for (int m = 0; m < M; m++) {
			input[m] = m;
		}
//		조합으로 궁수의 열위치에 대한 경우의 수를 뽑았으면 최종적으로 궁수의 행렬 좌표를 output에 저장할 것임
		output = new int[3][2];

//		combination 수행 (내부에 defence 메서드 호출)
		combination(0, 0);
//		답 출력
		System.out.println(max);
	}
}
