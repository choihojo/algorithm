import java.io.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int cnt1 = n - 1;
		int cnt2 = 1;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < cnt1; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < cnt2; j++) {
				System.out.print("*");
			}
			System.out.println("");
			cnt1 -= 1;
			cnt2 += 2;
		}
		
		int cnt3 = 1;
		int cnt4 = 2 * n - 3;
		
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < cnt3; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < cnt4; j++) {
				System.out.print("*");
			}
			System.out.println("");
			cnt3 += 1;
			cnt4 -= 2;
		}

	}
}