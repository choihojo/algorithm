import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str;
		String[] srr = new String[3];
		int[] irr = new int[3];
		int min = 0;
		int mid = 0;
		int max = 0;
		StringBuilder sb = new StringBuilder();
		while (!(str = br.readLine()).equals("0 0 0")) {
			srr = str.split(" ");
			for (int i = 0; i < 3; i++) {
				irr[i] = Integer.parseInt(srr[i]);
			}
			Arrays.sort(irr);
			min = irr[0];
			mid = irr[1];
			max = irr[2];
			if (max >= (min + mid)) {
				sb.append("Invalid").append("\n");
			}
			else if (min == mid && mid == max) {
				sb.append("Equilateral").append("\n");
			}
			else if (min == mid || mid == max) {
				sb.append("Isosceles").append("\n");
			}
			else {
				sb.append("Scalene").append("\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}		
}