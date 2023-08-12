import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		StringBuilder temp = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		int sum = 0;
		while ((n = Integer.parseInt(br.readLine())) != -1) {
			temp.setLength(0);
			temp.append(n).append(" = ");
			sum =0;
			for (int i = 1; i <= (n / 2); i++) {
				if (n % i == 0) {
					sum += i;
					temp.append(i).append(" + ");
				}
			}
			
			if (sum == n) {
				temp.deleteCharAt(temp.length() - 1);
				temp.deleteCharAt(temp.length() - 1);
				temp.deleteCharAt(temp.length() - 1);
				sb.append(temp).append("\n");
			}
			else {
				sb.append(n).append(" is NOT perfect.");
				sb.append("\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}	
}