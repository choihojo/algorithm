import java.io.*;

public class Solution {
	private static int max = 0;
	private static int temp = 0;
	
	private static void combination(int[] input, int[] output, int cnt, int start, int M) {
		if (cnt == output.length) {
			for (int o : output) {
				temp += o;
			}
			if (temp > max && temp <= M) {
				max = temp;
				// temp 값이 max보다 크고 과자 봉지의 무게인 M 이하이면 temp 값으로 max 갱신
			}
			temp = 0;
			return;
		}
		for (int i = start; i < input.length; i++) {
			output[cnt] = input[i];
			combination(input, output, cnt + 1, i + 1, M);
			// 조합
		}
		
	}
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		String[] srr = new String[2];
		int N = 0;
		int M = 0;

		for (int t = 1; t <= TC; t++) {
			max = 0;
			srr = br.readLine().split(" ");
			N = Integer.parseInt(srr[0]);
			M = Integer.parseInt(srr[1]);
			srr = br.readLine().split(" ");
			int[] irr = new int[N];
			for (int i = 0; i < N; i++) {
				irr[i] = Integer.parseInt(srr[i]);
			}
			// N개만큼 과자 입력받아서 irr에 저장
			combination(irr, new int[] {0, 0}, 0, 0, M);
			// irr을 input으로 하여 2개의 요소를 골라 조합
			if (max == 0) {
				max = -1;
			}
			System.out.printf("#%d %d", t, max);
			if (t != TC) {
				System.out.println("");
			}
		}
	}
}