import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int idx = 0;
		int max = 0;
		int num = 0;
		for (int i = 1; i < 10; i++) {
			num = sc.nextInt();
			if (num > max) {
				max = num;
				idx = i;
			}
		}
		System.out.println(max);
		System.out.println(idx);
	}
}