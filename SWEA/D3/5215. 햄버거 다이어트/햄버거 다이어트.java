import java.io.*;
public class Solution {
	static int max = 0;
	static int[] flavour;
	static int[] calorie;
	static int N = 0;
	static int L = 0;
	static int tempFlavour = 0;
	static int tempCalorie = 0;
	
	public static void subset(int cnt) {
		if (cnt == N) {
			if (tempCalorie <= L) {
				if (tempFlavour > max) {
					max = tempFlavour;
				}
			}
			return;
		}
		
//		tempCalorie가 L보다 클 경우에는 더 볼 필요가 없으므로 탐색 종료
//		속도 큰차이 없는 듯
//		if (tempCalorie >= L) {
//		if (tempCalorie > L) {
//			return;
//		}
//		else {
//			if (tempFlavour > max) {
//				max = tempFlavour;
//			}
//			return;
//		}
//	}
		
//			부분집합에서는 for문 쓸필요가 없음
//			재료 체크 (1: 선택한 경우)
			tempFlavour += flavour[cnt];
			tempCalorie += calorie[cnt];
			subset(cnt + 1);
			
//			재료 체크 (0: 선택하지 않은 경우)
			tempFlavour -= flavour[cnt];
			tempCalorie -= calorie[cnt];
			subset(cnt + 1);
		
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
			
			// 부분집합으로 한번에 구함
			subset(0);
			
			sb.append("#").append(t).append(" ").append(max);
			if (t != T) {
				sb.append("\n");
			}
			max = 0;
		}
		
		System.out.println(sb.toString());
	}
}
