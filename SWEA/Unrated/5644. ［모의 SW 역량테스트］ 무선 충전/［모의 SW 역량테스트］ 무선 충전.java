import java.io.*;
import java.util.*;


public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] srr;
//		0(이동하지 않음), 1(상), 2(우), 3(하), 4(좌)
		int[] dR = new int[] {0, -1, 0, 1, 0};
		int[] dC = new int[] {0, 0, 1, 0, -1};
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			srr = br.readLine().split(" ");
//			총 충전량 charge
			int charge = 0;
			
//			A 시작점에 대한 행과 열
			int aR = 0;
			int aC = 0;
			
//			B 시작점에 대한 행과 열
			int bR = 9;
			int bC = 9;
			
//			총 이동 시간 M
			int M = Integer.parseInt(srr[0]);
			
//			BC의 개수 A
			int A = Integer.parseInt(srr[1]);
			
//			10*10 전체 맵
//			BC 개수만큼 3차원으로 확장시켜서 만듦
//			BC 크기에 따라 내림차순으로 정렬시켜서 최대 충전량 찾을 예정
			Integer[][][] map = new Integer[10][10][A];
			
//			A 이동 경로 저장
//			초기 위치(0초)부터 충전 가능하므로 M보다 1 크게 배열 설정
			int[] moveA = new int[M + 1];
			srr = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				moveA[i + 1] = Integer.parseInt(srr[i]);
			}
			
//			B 이동 경로 저장
			int[] moveB = new int[M + 1];
			srr = br.readLine().split(" ");
			for (int i = 0; i < M; i++) {
				moveB[i + 1] = Integer.parseInt(srr[i]);
			}
			
//			BC 저장할 배열 선언
			int[][] spot = new int[A][4];
			for (int i = 0; i < A; i++) {
				srr = br.readLine().split(" ");
				for (int j = 0; j < 4; j++) {
					spot[i][j] = Integer.parseInt(srr[j]);
				}
			}
			Arrays.sort(spot, (s1, s2) -> (s2[3] - s1[3]));
			
//			3차원 좌표에 충전량 추가
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < A; k++) {
//						이 부분을 조심해야 되는데 문제에서 주어지는 좌표가 행과 열이 바뀌어 있어서 반대로 해야함
//						심지어 1부터 시작하기 때문에 주어지는 좌표에서 1도 빼줘야함
						if ((Math.abs(spot[k][0] - 1 - j) + Math.abs(spot[k][1] - 1 - i)) <= spot[k][2]) {
							map[i][j][k] = spot[k][3];
						}
//						객체 배열이라서 따로 0으로 초기화해줘야함
						else {
							map[i][j][k] = 0;
						}
					}
				}
			}
			
//			3차원 좌표인 충전량에 따라 3차원 배열만 내림차순으로 정렬
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Arrays.sort(map[i][j], (Integer o1, Integer o2) -> (o2 - o1));
				}
			}
			
//			작동확인용 코드
//			for (Integer[][] a : map) {
//				for (Integer[] b: a) {
//					System.out.println(Arrays.toString(b));
//				}
//			}
//			System.out.println("-----------------");
//			System.out.println(Arrays.toString(map[2][3]));
//			System.out.println(Arrays.toString(map[3][4]));
			
			List<Integer>[] listA = new ArrayList[M + 1];
			for (int i = 0; i < M + 1; i++) {
				listA[i] = new ArrayList<>();
			}
			List<Integer>[] listB = new ArrayList[M + 1];
			for (int i = 0; i < M + 1; i++) {
				listB[i] = new ArrayList<>();
			}
			
//			이동과정 누적
			for (int i = 0; i < M + 1; i++) {
				boolean inter = false;
				aR += dR[moveA[i]];
				aC += dC[moveA[i]];
				
				bR += dR[moveB[i]];
				bC += dC[moveB[i]];
				
				for (int j = 0; j < A; j++) {
					if ((Math.abs(spot[j][0] - 1 - aC) + Math.abs(spot[j][1] - 1 - aR)) <= spot[j][2]) {
						listA[i].add(j);
					}
					if ((Math.abs(spot[j][0] - 1 - bC) + Math.abs(spot[j][1] - 1 - bR)) <= spot[j][2]) {
						listB[i].add(j);
					}
				}
				
				for (int e : listA[i]) {
					if (listB[i].contains(e)) {
						inter = true;
					}
				}
				
				if (!inter) {
					charge += map[aR][aC][0];
					charge += map[bR][bC][0];
				}
				else {
					if (listA[i].size() == 1 && listB[i].size() == 1) {
						charge += map[aR][aC][0] / 2;
						charge += map[bR][bC][0] / 2;
					}
					else {
						if (listA[i].get(0) == listB[i].get(0)) {
							int tempA = map[aR][aC][0] + map[bR][bC][1];
							int tempB = map[aR][aC][1] + map[bR][bC][0];
							charge += Math.max(tempA, tempB);
						}
						else {
							charge += map[aR][aC][0];
							charge += map[bR][bC][0];
						}
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(charge).append("\n");
//			System.out.println(Arrays.toString(listA));
//			System.out.println(Arrays.toString(listB));
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}