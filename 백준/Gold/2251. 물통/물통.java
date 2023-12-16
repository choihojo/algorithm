import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static Map<Integer, Integer> resultMap;
	static int[] volume;
	static boolean[][] checkTable;
	static int[] bottleList;
	static List<Integer> resultList;
	static StringBuilder sb;

	static void dfs() {
//		체크한 적 있는 부피 리스트면 탈출
		if (checkTable[bottleList[0]][bottleList[1]]) return;
		
//		체크한 적 없는 부피 리스트면 체크하고 0번째인 A 물통 부피가 0일 경우에 C 물통의 양을 resultMap에 추가
		checkTable[bottleList[0]][bottleList[1]] = true;
		if (bottleList[0] == 0) resultMap.put(bottleList[2], 0);

//		다른 물통에 부어줄 물통을 조사함
		for (int i = 0; i < 3; i++) {
			
//			물통에 물이 존재하지 않으면 그냥 패스
			if (bottleList[i] == 0) continue;

//			물통에 물이 존재해서 다른 물통에 부을 수 있음
//			이제 i 물통으로부터 물을 받을 타겟 물통 상태에 대해 조사함
			for (int j = 0; j < 3; j++) {
				
//				자기자신한테 부을 필요는 없으니까 그냥 패스
				if (j == i) continue;
//				타겟 물통이 이미 꽉 차있으면 그냥 패스
				if (bottleList[j] == volume[j]) continue;
				
//				i 물통에 있는 물을 j 물통에 얼마나 부을 수 있는지 계산
				int diff = 0;
//				i 물통에 있는 물을 j 물통에 전부 부을 수 있음
				if (volume[j] - bottleList[j] >= bottleList[i]) {
					diff = bottleList[i];
				} else {
//					일부만 부을 수 있음
					diff = volume[j] - bottleList[j];
				}
				
//				물통 붓고 다음으로 넘어감
				bottleList[i] -= diff;
				bottleList[j] += diff;
				dfs();
//				백트래킹
				bottleList[i] += diff;
				bottleList[j] -= diff;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		resultMap = new HashMap<>();
		resultList = new ArrayList<>();
		sb = new StringBuilder();
		
//		각 물통의 부피
		volume = new int[3];
		for (int i = 0; i < 3; i++) {
			volume[i] = Integer.parseInt(st.nextToken());
		}

//		현재 상태가 측정된 적 있는지 여부 저장
		checkTable = new boolean[volume[0] + 1][volume[1] + 1];

//		처음 시작 상태
		bottleList = new int[] { 0, 0, volume[2] };
		
//		물통 붓기 완전탐색 수행
		dfs();
		
		for (int i : resultMap.keySet()) {
			resultList.add(i);
		}
		Collections.sort(resultList);
		for (int i = 0; i < resultList.size(); i++) {
			sb.append(resultList.get(i)).append(" ");
		}
		
		System.out.println(sb.deleteCharAt(sb.length() - 1));
	}
}
