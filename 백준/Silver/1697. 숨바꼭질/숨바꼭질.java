import java.io.*;
import java.util.*;

public class Main {
//	입력으로 받을 변수
	static int N;
	static int K;

// 시간을 queue의 크기만큼 루프를 돌게 하여 측정한 방법
	static int bfs1() {
		Queue<Integer> queue = new ArrayDeque<>();
		
//		방문체크 배열
//		K가 가능한 최대의 경우가 짝수인 100_000이기 때문에 배열의 크기는 0에서 100_000까지 100_001로 선언하면 됨
//		만약 홀수였다면 한번 K 너머로 가는 경우의 수가 있기 때문에 K + 2로 선언해야 함
//		다만 이 경우 K가 짝수일 때가 최대이기 때문에 K + 1로 하면 됨
//		배열 크기 최적화를 하고 싶다면 Math.max(N, K) + n 이런 식으로 하면 될 듯
		boolean[] visited = new boolean[100_001];
		
//		총 경과한 시간
		int time = 0;
		
//		시작점 큐에 삽입
		queue.offer(N);
		visited[N] = true;
		int poll = 0;
		int size = 0;
		time = 0;
		
//		찾고자 하는 값(동생)이 발견되면 바로 while문 탈출 가능하게 while문에다 A로 라벨링
		A : while (!queue.isEmpty()) {
//			큐의 사이즈 저장
//			큐의 사이즈만큼 for루프를 돌리고 아래의 time++를 지나 다시 여기로 오면 시간이 경과된 거임
			size = queue.size();
			
			for (int i = 0; i < size; i++) {
				poll = queue.poll();
//				인덱스 초과 예외 방지하려면 등호 여부 조심할 것
//				아니면 아예 크게 배열을 선언하면 해결됨
//				왼쪽으로 가는 경우
				if (poll > 0 && poll <= 100_000) {
					if (!visited[poll - 1]) {
						queue.offer(poll - 1);
						visited[poll - 1] = true;
					}
				}
//				오른쪽으로 가는 경우
				if (poll >= 0 && poll < 100_000) {
					if (!visited[poll + 1]) {
						queue.offer(poll + 1);
						visited[poll + 1] = true;
					}
				}
//				순간이동하는 경우
				if (poll >= 0 && poll <= 50_000) {
					if (!visited[2 * poll]) {
						queue.offer(2 * poll);
						visited[2 * poll] = true;
					}
				}
//				찾고자 하는 값이 발견되면 바로 탈출
//				tim++ 부분이 이 부분보다 아래에 있기 때문에 시작과 동시에 동생을 발견한 경우도 자동으로 고려됨
				if (poll == K) {
					break A;
				}
			}

//			시간 경과
			time++;
		}
		
		return time;
	}
	
//	큐의 사이즈로 시간 경과를 체크하는 것이 아니라 수직선에 바로 갱신시키는 방법
//	최단경로 구할 때 이전 노드에서 +1한 값을 다음 노드의 값에 넣어주는 것과 원리는 동일함
	static int bfs2() {
		Queue<Integer> queue = new ArrayDeque<>();
		
//		수직선에 최소 시간을 바로 갱신시킬 int배열
//		수직선에 먼저 도달하는 경우가 반드시 최소시간임 (큐의 선입선출 구조 때문)
		int[] timeList = new int[100_001];

		queue.offer(N);
		int poll;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			if (poll == K) {
				break;
			}
//			생각해볼 부분은 timeList[N] 값은 추후에 0이 아니라 다른 값으로 갱신된다는 거임
//			그럼에도 불구하고 N 위치를 제외한 다른 곳에 영향을 주지 않음
//			N == K인 경우 바로 탈출되는 것과 timeList[i] == 0일 때만 바꾸는 것 때문임 (timeList[N]이 다른 값으로 갱신되더라도 timeList[N] == 0일 때 갈라진 분기가 무조건 더 빨리 다른 점들에 도착함)
//			왼쪽으로 가는 경우
			if (poll > 0 && poll <= 100_000) {
				if (timeList[poll - 1] == 0) {
					queue.offer(poll - 1);
					timeList[poll - 1] = timeList[poll] + 1;
				}
			}
//			오른쪽으로 가는 경우
			if (poll >= 0 && poll < 100_000) {
				if (timeList[poll + 1] == 0) {
					queue.offer(poll + 1);
					timeList[poll + 1] = timeList[poll] + 1;
				}
			}
//			순간이동하는 경우
			if (poll >= 0 && poll <= 50_000) {
				if (timeList[2 * poll] == 0) {
					queue.offer(2 * poll);
					timeList[2 * poll] = timeList[poll] + 1;
				}
			}
		}
		return timeList[K];
	}
	
//	이번엔 패러미터로 시간을 같이 넘겨주는 방법
	static int bfs3() {
		Queue<int[]> queue = new ArrayDeque<>();
		
//		N은 visited의 좌표를 의미하고 0은 시간을 의미함
		queue.offer(new int[] {N, 0});
		boolean[] visited = new boolean[100_001];
		visited[N] = true;
		int[] poll;
//		poll이 K 좌표에 도달했을 때 해당 시간을 저장할 변수
		int time = 0;
		
		while (!queue.isEmpty()) {
			poll = queue.poll();
			if (poll[0] == K) {
				time = poll[1];
				break;
			}
			
//			왼쪽으로 이동하는 경우
			if (poll[0] > 0 && poll[0] <= 100_000) {
				if (!visited[poll[0] - 1]) {
					visited[poll[0] - 1] = true;
//					첫번째 요소는 수직선에서 좌표를 의미하고 두번째 요소는 시간을 의미함
//					왼쪽으로 가는 것이므로 이전 좌표에서 -1을 하고 시간은 1을 더해줌
					queue.offer(new int[] {poll[0] - 1, poll[1] + 1});
				}
			}
//			오른쪽으로 이동하는 경우
			if (poll[0] >= 0 && poll[0] < 100_000) {
				if (!visited[poll[0] + 1]) {
					visited[poll[0] + 1] = true;
					queue.offer(new int[] {poll[0] + 1, poll[1] + 1});
				}
			}
//			순간이동하는 경우
			if (poll[0] >= 0 && poll[0] <= 50_000) {
				if (!visited[2 * poll[0]]) {
					visited[2 * poll[0]] = true;
					queue.offer(new int[] {2 * poll[0], poll[1] + 1});
				}
			}
		}
		return time;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		K = Integer.parseInt(srr[1]);
		System.out.println(bfs2());
	}
}