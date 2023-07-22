import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		int min = arr[0];
		int max = arr[0];
		for (int a : arr) {
			if (a < min) {
				min = a;
			}
			if (a > max) {
				max = a;
			}
		}
		System.out.printf("%d %d", min, max);
	}
}
