import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];
		int a = 0;
		int b = 0;
		int temp = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = (i + 1);
		}
		for (int j = 0; j < M; j++) {
			a = sc.nextInt();
			b = sc.nextInt();
			temp = arr[a - 1];
			arr[a - 1] = arr[b - 1];
			arr[b - 1] = temp;
		}
		for (int element : arr) {
			System.out.printf("%d ", element);
		}
	}
}