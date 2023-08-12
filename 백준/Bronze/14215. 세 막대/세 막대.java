import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int[] irr = new int[3];
		for (int i = 0; i < 3; i++) {
			irr[i] = Integer.parseInt(srr[i]);
		}
		Arrays.sort(irr);
		int min = irr[0];
		int mid = irr[1];
		int max = irr[2];
		if (max < (min + mid)) {
			System.out.println(min + mid + max);
		}
		else {
			System.out.println(min + mid + min + mid - 1);
		}
	}		
}
