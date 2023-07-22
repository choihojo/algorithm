import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		float[] arr = new float[N];
		float[] brr = new float[N];
		float max = 0;
		float sum = 0;
		for (int j = 0; j < N; j++) {
			arr[j] = sc.nextFloat();
		}
		for (float a : arr) {
			if (a > max) {
				max = a;
			}
		}
		for (int k = 0; k < N; k++) {
			brr[k] = (arr[k] / max) * 100;
		}
		for (float b : brr) {
			sum += b;
		}
		System.out.println(sum / N);
	}
}