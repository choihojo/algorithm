import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		String num = srr[0];
		int n = Integer.parseInt(srr[1]);
		int n2ten = 0;
		
		for (int i = 0; i < num.length(); i++) {
			if (num.charAt(i) >= '0' && num.charAt(i) <= '9') {
				n2ten += (num.charAt(i) - '0') * Math.pow(n, num.length() - i - 1);
			}
			else if (num.charAt(i) >= 'A' && num.charAt(i) <= 'Z') {
				n2ten += (num.charAt(i) - 'A' + 10) * Math.pow(n, num.length() - i - 1);
			}
		}
		System.out.println(n2ten);
	}
}
