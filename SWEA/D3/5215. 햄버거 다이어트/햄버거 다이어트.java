import java.io.*;
public class Solution {
	static int max = 0;
	static int[] flavour;
	static int[] calorie;
	static int N = 0;
	static int L = 0;
	static int tempFlavour = 0;
	static int tempCalorie = 0;
	
	public static void combination(int goal, int cnt, int start) {
		// 재료를 goal개 고르는 함수
		if (cnt == goal) {
			// goal개 골랐을 때 tempCalorie가 L보다 작고 tempFlavour가 max보다 크면 갱신
			if (tempCalorie <= L) {
				if (tempFlavour > max) {
					max = tempFlavour;
				}
			}
			return;
		}
		
//		이러면 속도가 tempCalorie가 L을 넘을 경우에 바로 종료하므로 속도가 빨라질 줄 알았음
//		근데 더 느려지는 거 보니 조건문 비교에 걸리는 시간이 더 긴 듯
//		if (tempCalorie >= L) {
//			if (tempCalorie > L) {
//				return;
//			}
//			else {
//				if (tempFlavour > max) {
//					max = tempFlavour;
//				}
//        		return;
//			}
//		}
		
		// cnt번째 재료를 고르는 코드
		for (int i = start; i < N; i++) {
			
			// 재료를 고르는 경우
			tempFlavour += flavour[i];
			tempCalorie += calorie[i];
			combination(goal, cnt + 1, i + 1);
			
			// 재료를 고르지 않은 경우
			tempFlavour -= flavour[i];
			tempCalorie -= calorie[i];
//			주의할 점은 아래에 combination(goal, cnt, i + 1);을 해줄 필요가 없음
//			어차피 다음 i 루프로 넘어가면 자동으로 고르지 않은 경우로 계산되는 거임
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] srr = new String[2];
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			srr = br.readLine().split(" ");
			N = Integer.parseInt(srr[0]);
			L = Integer.parseInt(srr[1]);
			
			flavour = new int[N];
			calorie = new int[N];
			
			for (int n = 0; n < N; n++) {
				srr = br.readLine().split(" ");
				flavour[n] = Integer.parseInt(srr[0]);
				calorie[n] = Integer.parseInt(srr[1]);
			}
			
			// 20개 재료 중에서 0개 고르는 경우, 1개 고르는 경우, ..., N 개 고르는 경우 고려
			for (int i = 0; i <= N; i++) {
				combination(i, 0, 0);
			}
			
			sb.append("#").append(t).append(" ").append(max);
			if (t != T) {
				sb.append("\n");
			}
			max = 0;
		}
		
		System.out.println(sb.toString());
	}
}
