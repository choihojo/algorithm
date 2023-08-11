import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
//		25는 10의 배수가 아니긴 하지만 25로 먼저 잘라도 5가 있어서 그리디로 풀어도 될 듯
//		예) 25 + 5 = 10 + 10 + 10
		int[] coin = new int[] {25, 10, 5, 1};
		for (int t = 1; t <= T; t++) {
			int[] each = new int[] {0, 0, 0, 0};
			int remain = Integer.parseInt(br.readLine());
			while (remain > 0) {
				if (remain - coin[0] >= 0) {
					remain -= coin[0];
					each[0]++;
				}
				else if (remain - coin[1] >= 0) {
					remain -= coin[1];
					each[1]++;
				}
				else if (remain - coin[2] >= 0) {
					remain -= coin[2];
					each[2]++;
				}
				else if (remain - coin[3] >= 0) {
					remain -= coin[3];
					each[3]++;
				}
			}
			sb.append(each[0]).append(" ");
			sb.append(each[1]).append(" ");
			sb.append(each[2]).append(" ");
			sb.append(each[3]).append("\n");
		}
		System.out.println(sb.toString());
	}
}