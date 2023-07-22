import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] idx = new int[3];
		int[] arr = new int[N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 3; j++) {
				idx[j] = sc.nextInt();
			}
			for (int k = (idx[0] - 1); k < idx[1]; k++) {
				arr[k] = idx[2];
			}
		}
		for (int a : arr) {
			System.out.printf("%d ", a);
		}
	}
}