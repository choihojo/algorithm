import java.io.*;

public class Main {
	static int sum;
	static int[] irr;
	static int diff;
	static int[] remain;
	
	public static void combination(int cnt, int start) {
		if (cnt == 2) {
//			9명 중에 2명을 고르고 2명의 합이 diff와 같을 때 나머지 7명을 출력함
			if ((remain[0] + remain[1]) == diff) {
				for (int i : irr) {
//					9명 중 2명을 제외한 7명을 출력함
					if (i != remain[0] && i != remain[1]) {
						System.out.println(i);
					}
				}
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			remain[cnt] = irr[i];
			combination(cnt + 1, i + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		irr = new int[9];
		for (int i = 0; i < 9; i++) {
			irr[i] = Integer.parseInt(br.readLine());
			sum += irr[i];
		}
		
//		9명 중 7난쟁이가 아닌 나머지 2명의 합을 구함
		diff = sum - 100;
		remain = new int[2];
		
		combination(0, 0);
		
	}
}