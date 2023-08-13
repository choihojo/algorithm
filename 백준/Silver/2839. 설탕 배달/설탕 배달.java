import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
//		5로 나누면 나머지는 0, 1, 2, 3, 4
//		0 -> 해결
//		1 -> 5 빼면 나머지 6 -> 3으로 나누면 해결
//		2 -> 5 빼면 나머지 7 -> 5 빼면 나머지 12 -> 3으로 나누면 해결
//		3 -> 해결
//		4 -> 5 빼면 나머지 9 -> 3으로 나누면 해결
		
		int five = 0;
		int three = 0;
		int remain = 0;
		
		five = N / 5;
		remain = N % 5;
		N %= 5;
		
		if (remain == 0) {
			
		}
		else if (remain == 1) {
			five -= 1;
			N += 5;
			three = 2;
		}
		else if (remain == 2) {
			five -= 2;
			N += 10;
			three = 4;
		}
		else if (remain == 3) {
			three = 1;
		}
		else if (remain == 4) {
			five -= 1;
			N += 5;
			three = 3;
		}
		
		if (five < 0) {
			System.out.println(-1);
		}
		else {
			System.out.println(five + three);
		}
		
	}		
}
