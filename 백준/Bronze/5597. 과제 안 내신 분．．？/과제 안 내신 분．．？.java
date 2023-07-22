import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[30];
		boolean[] boolArr = new boolean[30];
		int num = 0;
		int cnt = 0;
		int[] brr = new int[2];
		for (int i = 0; i < 30; i++) {
			arr[i] = (i + 1);
		}
		for (int j = 0; j < 28; j++) {
			num = sc.nextInt();
			boolArr[num - 1] = true;
		}
		for (int k = 0; k < 30; k++) {
			if (boolArr[k] == false) {
				if (brr[0] == 0) {
					brr[0] = arr[k];
				} else {
					brr[1] = arr[k];
				}
			}
		}
		System.out.println(Arrays.stream(brr).min().getAsInt());
		System.out.println(Arrays.stream(brr).max().getAsInt());
	}
}