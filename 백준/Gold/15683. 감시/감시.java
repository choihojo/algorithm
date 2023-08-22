import java.io.*;
import java.util.*;

// CCTV의 정보가 주어졌을 때, CCTV의 방향을 조절해서 사각지대 최소크기
// CCTV는 벽을 통과할 수 없고 다른 CCTV는 통과할 수 있음
// 사무실의 크기 N*M (1이상 8 이하)
// CCTV의 최대 개수는 8개를 넘지 않음

// 1번은 0(우), 1(상), 2(좌), 3(하) 인덱스 중 하나
// 2번은 (0, 2), (1, 3) 인덱스 중 하나
// 3번은 (0, 1), (1, 2), (2, 3), (3, 4) 인덱스 중 하나
// 4번은 (0, 1, 2), (1, 2, 3), (2, 3, 0), (3, 0, 1) 인덱스 중 하나
// 5번은 항상 (0, 1, 2, 3) 인덱스
// 어차피 최대 8개이므로 5번을 제외한 개수만큼 중복순열로 뽑으면 될 듯
// 4^8 = 2^16 = 2^6 * 1024 = 640000 -> 그냥 5번까지 같이 뽑아도 시간 될 듯?

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int[][] tempMap;
//	카메라들의 리스트인 변수명 cctv
//	Camera의 프로퍼티에는 카메라의 행과 열 좌표, 그리고 카메라 타입이 존재함
//	이차원 맵을 순회하면서 순서대로 카메라를 넣어줌
	static List<Camera> cctv;
//	우상좌하 순으로 배치한 방향좌표
//	카메라의 회전을 의미함
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	
	static int[] input = new int[] {0, 1, 2, 3};
	static int[] output;
//	매 경우의 수마다 사각지대를 카운팅할 변수
	static int cnt;
//	최솟값 저장할 변수
	static int min = Integer.MAX_VALUE;
	
//	카메라 타입이 특정 회전각도일 때 바라보는 방향을 나타내는 배열
//	예를 들어 타입1은 0(우)쪽을 탐색할 수도 있고, 회전하면 1(상)쪽을 탐색할 수도 있는 등 총 4가지 경우의 수가 있음
//	타입2 같은 경우에는 2가지 경우의 수가 있고 타입5 같은 경우에는 1가지만 존재함
//	하지만 그럼에도 불구하고 각각 4가지 회전 방향이 있다고 생각해도 시간복잡도가 그렇게 높지 않음
//	그래서 그냥 모든 카메라가 4가지 회전의 경우의 수가 있다 생각하고 풀었음
	static int[][] no1 = new int[][] {{0}, {1}, {2}, {3}};
	static int[][] no2 = new int[][] {{0, 2}, {1, 3}, {0, 2}, {1, 3}};
	static int[][] no3 = new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
	static int[][] no4 = new int[][] {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}};
	static int[][] no5 = new int[][] {{0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}};
//	각 카메라 타입과 회전방향에 대한 정보를 담고 있는 cctvType
//	no1, no2, no3, no4, no5가 리스트에 들어가있음 (인덱싱 편하게 하기 위해 0번째는 의미없는 배열로 채움)
	static List<int[][]> cctvType = new ArrayList<>();
	
//	각각 4가지가 존재할 수 있으므로 중복순열
	static void doublePermutation(int cnt) {
		if (cnt == cctv.size()) {
//			경우 하나를 뽑았으면 그걸 가지고 caculate 메서드(사각지대 계산) 호출
			calculate();
			return;
		}
		
		for (int i = 0; i < input.length; i++) {
			output[cnt] = input[i];
			doublePermutation(cnt + 1);
		}
	}
	
	static void calculate() {
//		매 경우의 수마다 cnt와 tempMap 초기화
		cnt = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				tempMap[n][m] = map[n][m];
			}
		}
//		i번째 cctv의 회전 타입은 output의 i번째 인덱스 성분에 의해 결정됨
		for (int i = 0; i < cctv.size(); i++) {
//			맵을 순회하면서 얻은 감시카메라 객체를 순서대로 하나씩 꺼내옴
			Camera camera = cctv.get(i);
			int oRow = camera.row;
			int oCol = camera.col;
			int type = camera.type;
//			감시카메라 객체의 타입을 이용해 cctvType의 해당하는 회전방향 정보를 불러오고, 거기에다 아까 뽑은 중복순열 경우의 수(output[i])로 인덱싱함
//			중복순열 경우의 수는 회전방향 종류를 의미함 (예를 들어 90도 회전, 180도 회전 등)
			int[] irr = cctvType.get(type)[output[i]];
//			해당 카메라가 탐색할 수 있는 가짓수만큼 반복
//			예를 들어 4번 카메라를 get했고 output[i] == 1의 경우일 때는 {1(상), 2(좌), 3(하)}을 탐색하는 상황임
			for (int j = 0; j < irr.length; j++) {
				int row = oRow + dRow[irr[j]];
				int col = oCol + dCol[irr[j]];
//				일단 한가지 경우를 골라서 6을 만나거나 인덱스 범위를 벗어날 때까지 탐색을 계속함
//				예를 들어 1(상)의 경우 위로 쭉 나아가는 거임
				while (row >= 0 && row < N && col >= 0 && col < M) {
					if (tempMap[row][col] == 6) {
						break;
					}
//					탐색이 가능한 위치면 -1로 바꿔줌
					else if (tempMap[row][col] == 0) {
						tempMap[row][col] = -1;
					}
					row += dRow[irr[j]];
					col += dCol[irr[j]];
				}
			}
		}
		
//		사각지대는 그대로 0으로 남아있는 위치이므로 카운팅해줌
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (tempMap[n][m] == 0) cnt++;
			}
		}
		
		if (cnt < min) {
			min = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] srr = br.readLine().split(" ");
		N = Integer.parseInt(srr[0]);
		M = Integer.parseInt(srr[1]);
		map = new int[N][M];
		tempMap = new int[N][M];
		
		cctv = new ArrayList<>();
		
		for (int n = 0; n < N; n++) {
			srr = br.readLine().split(" ");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(srr[m]);
				if (map[n][m] >= 1 && map[n][m] <= 5) cctv.add(new Camera(n, m, map[n][m]));
			}
		}
		
		cctvType.add(new int[0][0]);
		cctvType.add(no1);
		cctvType.add(no2);
		cctvType.add(no3);
		cctvType.add(no4);
		cctvType.add(no5);
		
		output = new int[cctv.size()];
		
		doublePermutation(0);
		
		System.out.println(min);
	}
}

class Camera {
	int row;
	int col;
	int type;
	
	public Camera (int row, int col, int type) {
		this.row = row;
		this.col = col;
		this.type = type;
	}
}