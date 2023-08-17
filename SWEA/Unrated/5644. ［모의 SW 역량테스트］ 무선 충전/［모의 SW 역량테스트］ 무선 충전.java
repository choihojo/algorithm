import java.io.*;
import java.util.*;

public class Solution {
//	그냥 구현인데도 개인적으로 너무 어려웠음
//	거의 4시간 걸렸는데 어떤 부분에서 버벅였는지 태도를 점검할 필요가 있음
//	반드시 나중에 다시 풀어볼 것
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] srr;
//		행동 선택지 : 0(이동하지 않음), 1(상), 2(우), 3(하), 4(좌) 순으로 행배열과 열배열을 만들어줌
		int[] dR = new int[] {0, -1, 0, 1, 0};
		int[] dC = new int[] {0, 0, 1, 0, -1};
//		출력에 사용할 스트링빌더 선언
		StringBuilder sb = new StringBuilder();
		
//		테스트케이스 진입
		for (int t = 1; t <= T; t++) {
			srr = br.readLine().split(" ");
//			총 충전량 charge
			int charge = 0;
//			A 시작점에 대한 행과 열
//			전체 맵 범위는 10*10인데 인덱싱 편하게 하기 위해 11*11로 선언할까 하다가 충전 범위 때문에 오히려 꼬일 수도 있을 것 같아 0부터 시작하게 함
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
//			BC 개수만큼 3차원으로 확장시켜서 만듦 (BC가 8개일 경우 10*10*8로 10*10의 이차원 zx평면 위에 y축을 따라 8층씩 블럭이 쌓여있다고 생각할 수 있음)
//			Java에서 3차원 배열은 제1팔분공간의 z, x, y 순([z][x][y])이므로 zx 평면 위에 y축을 따라 블럭을 쌓는다고 생각하는 것이 의미적으로는 맞으나 xy평면 위에 z축을 따라 쌓는다고 생각해도 답에는 영향을 주지 않음
//			만약 정말 xy평면 위에 z축을 따라 쌓는다고 생각하려면 8*10*10 배열을 선언해야 되는데 이러면 z축 성분만을 따로 들고오는 것이 번거로워짐 (반면 10*10*8로 하면 map[1][2] 이런 식으로 바로 들고올 수 있음)
//			BC 크기에 따라 내림차순으로 정렬시켜서 최대 충전량을 쉽게 찾게 했음
//			예를 들어 BC가 8개까지 있는 경우 map[3][4] = {100, 70, 70, 50, 0, 0, 0, 0}으로 내림차순으로 정렬시키는 거임 (총 BC는 8개 있더라도 map[3][4]에 겹치는 BC는 4개인 경우임)
//			int[][][]가 아니라 Integer[][][]로 선언한 이유는 3차원 부분의 배열만 따로 내림차순으로 정렬하기 위해 Arrays.sort(map[i][j], lambda);를 사용하기 위해서임
//			만약 int[][][]로 선언할 경우 아래 sort식에서 map[i][j]의 성분이 객체(reference) 타입이 아니라 primitive 타입이라 lambda식 등 comparator 사용 불가능함
			Integer[][][] map = new Integer[10][10][A];
			
//			A 이동 경로 저장
//			초기 위치(0초)부터 충전 가능하므로 M보다 1 크게 배열 설정
//			t = 20까지 있을 경우 t = 0, 1, 2, ..., 20까지 21가지 행동을 한 것임
			int[] moveA = new int[M + 1];
			srr = br.readLine().split(" ");
//			moveA[0]은 map[aR][aC]에서 0(이동하지 않음)의 행동을 한 것임 (t = 0)
//			따라서 map[1]부터 입력값을 넣어줌
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
//			위에서 입력받은 BC 배열을 3번째 성분(충전량)에 따라 객체(spot[0], spot[1], ...)를 내림차순으로 정렬
//			아래에서 map의 3차원 성분을 정렬시키는 것과 잘 비교해서 이해할 것 (아래에서 int타입일 경우 comparator를 못 쓰는 이유를 이해할 수 있어야 함)
			Arrays.sort(spot, (s1, s2) -> (s2[3] - s1[3]));
			
//			3차원 좌표에 충전량 추가
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					for (int k = 0; k < A; k++) {
//						이 부분을 조심해야 되는데 문제에서 주어지는 좌표가 행과 열이 바뀌어 있어서 반대로 해야함
//						심지어 1부터 시작하기 때문에 주어지는 좌표에서 1도 빼줘야함
//						다만 dR, dC 배열, map, 시작 좌표(aR, aC, bR, bC)는 모두 행(z)-열(x) 순이기 때문에 열-행 순으로 입력으로 들어오는 BC(충전기) 좌표만 신경써주면 됨
//						그래서 열에 해당하는 (spot[k][0] - 1)와 j의 차이를 구하고 행에 해당하는 (spot[k][1] - 1)과 i의 차이를 구해 서로 더하고 범위에 해당하는 spot[k][2] 이하인지 체크했음
//						이렇게 해야 마름모 모양의 범위에 들어오는지 판단할 수 있음
						if ((Math.abs(spot[k][0] - 1 - j) + Math.abs(spot[k][1] - 1 - i)) <= spot[k][2]) {
//							만약 마름모 범위 내라면 map[i][j] 배열(z축으로 쌓인 블럭)에 충전량을 넣어주었음
//							이미 spot[i]들은 spot[i][3]에 따라 내림차순으로 정렬된 상태여서 높은 것부터 들어가지만 그렇다고 map[i][j]이 내림차순으로 바로 정렬되는 건 아님
//							왜냐면 해당 위치를 포함하는 충전기의 충전량을 넣는 것이기 때문에 map[i][j] = {100, 0, 0, 50, 0, 0, 0} 이렇게 될 수가 있음
//							위에서 든 예하고 이어지는데 충전량 70의 충전기가 해당 위치를 포함하지 않는다면 아래 else문에 따라 0으로 채워지기 때문임
							map[i][j][k] = spot[k][3];
						}
//						객체 배열이라서 따로 0으로 초기화해줘야함
//						이렇게 안 하면 해당 위치를 포함하지 않는 충전기의 인덱스를 참조할 때 NullPointerException이 발생함
						else {
							map[i][j][k] = 0;
						}
					}
				}
			}
			
//			3차원 좌표인 충전량에 따라 3차원 배열만 내림차순으로 정렬
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Arrays.sort(map[i][j], (o1, o2) -> (o2 - o1));
				}
			}
//			이렇게 하면 {{{1, 3, 2}, {4, 5, 6}, ... }}} 배열이 {{{6, 5, 4}, {3, 2, 1}, ...}}} 이렇게 되는 것이 아니라 {{{3, 2, 1}, {6, 5, 4}, ...}}} 이런 식으로 정렬되는 거임
			
//			작동확인용 코드
//			for (Integer[][] a : map) {
//				for (Integer[] b: a) {
//					System.out.println(Arrays.toString(b));
//				}
//			}
//			System.out.println("-----------------");
//			System.out.println(Arrays.toString(map[2][3]));
//			System.out.println(Arrays.toString(map[3][4]));
			
//			A가 이동하면서 어떤 충전기 영역을 지나왔는지 기록하기 위한 리스트 배열 (그냥 리스트가 아니라 리스트의 배열임)
//			당연히 크기는 t = 0, 1, 2, ..., M까지므로 M + 1이 됨
			List<Integer>[] listA = new ArrayList[M + 1];
			for (int i = 0; i < M + 1; i++) {
				listA[i] = new ArrayList<>();
			}
//			B가 이동하면서 어떤 충전기 영역을 지나왔는지 기록하기 위한 리스트 배열
			List<Integer>[] listB = new ArrayList[M + 1];
			for (int i = 0; i < M + 1; i++) {
				listB[i] = new ArrayList<>();
			}
			
//			t = 0, 1, 2, ..., M까지 따라가면서 충전량 기록
			for (int i = 0; i < M + 1; i++) {
				
//				A와 B가 같은 충전기 영역을 지났는지 체크하기 위한 변수
				boolean inter = false;
//				t = i일 때 한 행동에 따라 A의 위치 갱신
				aR += dR[moveA[i]];
				aC += dC[moveA[i]];
//				B의 위치 갱신
				bR += dR[moveB[i]];
				bC += dC[moveB[i]];
				
//				A의 현재 위치가 어떤 충전기의 범위에 들어가는지 모두 범위에 추가
//				예를 들어 t = i일 때 0번째, 2번째 충전기의 영역에 들어가있었다면 i번째 리스트에 충전기의 인덱스인 0, 2를 추가하는 거임
//				결국 t = i일 때 A의 리스트와 B의 리스트를 비교해 교집합이 있는지 체크하는 것이 목적임
				for (int j = 0; j < A; j++) {
					if ((Math.abs(spot[j][0] - 1 - aC) + Math.abs(spot[j][1] - 1 - aR)) <= spot[j][2]) {
						listA[i].add(j);
					}
					if ((Math.abs(spot[j][0] - 1 - bC) + Math.abs(spot[j][1] - 1 - bR)) <= spot[j][2]) {
						listB[i].add(j);
					}
				}
				
//				만약 교집합이 있었다면 inter를 참으로 변경
//				이 확인이 반드시 필요한 이유가 A와 B가 서로 다른 충전기의 영역에 있는데 그 두 충전기의 충전량이 같은 경우에는 반반씩 나눠갖는 게 아니라 둘 다 충전량의 전부를 가져가는 것이 맞기 때문임
//				단순히 충전값을 비교해서만은 위 경우를 고려할 수 없음
				for (int e : listA[i]) {
					if (listB[i].contains(e)) {
						inter = true;
					}
				}
				
//				만약 교집합이 없었다면 각각 현재 위치에서 최댓값(정렬했으므로 0번째 원소)을 charge에 더해주면 됨
				if (!inter) {
					charge += map[aR][aC][0];
					charge += map[bR][bC][0];
				}
				else {
//					만약 교집합이 있고 둘 다 크기가 1이라면 대체제가 없으므로 서로 반반씩 나눠가짐
//					의미적으로 명확히 하려고 굳이 똑같은 값을 2로 나눠서 2번 더해줌
					if (listA[i].size() == 1 && listB[i].size() == 1) {
						charge += map[aR][aC][0] / 2;
						charge += map[bR][bC][0] / 2;
					}
					else {
//						만약 최소 둘 중 하나는 크기가 2 이상(현재 위치가 2개 이상의 충전기에 포함)이고 0번째 원소가 교집합일 경우에는 tempA, tempB 둘 중 하나가 최댓값임
//						일단 A와 B 중 한 명은 교집합인 최댓값을 반드시 가져가야 함
//						남은 한 명은 두 번째 충전량(1번째 인덱스)을 가져가면 됨
//						근데 두 번째 충전량을 가져갈 사람이 A가 좋을지 B가 좋을지는 일단 해보고 비교해야함
//						따라서 두 경우를 저장할 임시 변수를 만들어주고 최댓값을 채택했음
						if (listA[i].get(0) == listB[i].get(0)) {
							int tempA = map[aR][aC][0] + map[bR][bC][1];
							int tempB = map[aR][aC][1] + map[bR][bC][0];
							charge += Math.max(tempA, tempB);
						}
//						만약 교집합이 있긴 있지만 그것이 0번째 원소가 아닐 경우 그냥 0번째를 둘 다 더해주면 됨
//						예를 들어 A가 충전량 100의 충전기 0과 충전량 70의 충전기 1에 속해있고 B가 충전량 70의 충전기 2에 속해있을 때 그냥 최댓값을 더해주면 되는 것임
//						추가적으로 A가 속해있는 충전기 0의 충전량이 70, 1의 충전량도 70인데 B가 충전기 1에 속해있을 때도 자동으로 고려됨
						else {
							charge += map[aR][aC][0];
							charge += map[bR][bC][0];
						}
					}
				}
			}
//			출력 형식 맞춤
			sb.append("#").append(t).append(" ").append(charge).append("\n");
			
//			디버깅용 코드
//			A와 B가 어떤 충전기 영역에 포함되는지 리스트배열로 보여줌
//			System.out.println(Arrays.toString(listA));
//			System.out.println(Arrays.toString(listB));
		}
//		마지막 개행문자 삭제
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}