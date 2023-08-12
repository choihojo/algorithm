import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] irr = new int[3][2];
		
		for (int i = 0; i < 3; i++) {
			String[] srr = br.readLine().split(" ");
			for (int j = 0; j < 2; j++) {
				irr[i][j] = Integer.parseInt(srr[j]);
			}
		}
		
		int cnt1 = 0;
		int cnt2 = 0;
		int result1 = 0;
		int result2 = 0;
		for (int i = 1; i < 3; i++) {
			if (irr[i][0] == irr[0][0]) {
				cnt1++;
			}
			if (irr[i][1] == irr[0][1]) {
				cnt2++;
			}
		}
		
		if (cnt1 == 1) {
			for (int i = 1; i < 3; i++) {
				if (irr[i][0] != irr[0][0]) {
					result1 = irr[i][0];
					break;
				}
			}
		}
		else {
			result1 = irr[0][0];
		}
		
		if (cnt2 == 1) {
			for (int i = 1; i < 3; i++) {
				if (irr[i][1] != irr[0][1]) {
					result2 = irr[i][1];
					break;
				}
			}
		}
		else {
			result2 = irr[0][1];
		}
		
		System.out.printf("%d %d", result1, result2);

	}
}