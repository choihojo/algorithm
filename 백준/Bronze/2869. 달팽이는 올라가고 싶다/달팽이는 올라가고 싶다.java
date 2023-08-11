import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
		int A = Integer.parseInt(srr[0]);
		int B = Integer.parseInt(srr[1]);
		int V = Integer.parseInt(srr[2]);
		
		int speed = A - B;
		int day = 1;
		if ((V - A) % speed == 0) {
			day += ((V - A) / speed);
		}
		else {
			day += ((V - A) / speed + 1);
		}
		System.out.println(day);
	}	
}