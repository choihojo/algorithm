import java.io.*;
public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 0; t < 10; t++) {
			int N = Integer.parseInt(br.readLine());
			String[] srr = br.readLine().split(" ");
			int[] box = new int[srr.length];
			for (int i = 0; i < srr.length; i++) {
				box[i] = Integer.parseInt(srr[i]);
			}
			int maxIdx = 0;
			int minIdx = 0;
			int max = box[0];
			int min = box[0];
			boolean is = false;
			
			for (int n = 0; n < N; n++) {
				for (int i = 0; i < box.length; i++) {
					if (box[i] > max) {
						max = box[i];
						maxIdx = i;
					}
					if (box[i] < min) {
						min = box[i];
						minIdx = i;
					}
				}
				if ((max - min) <= 1) {
					System.out.printf("#%d %d\n", t + 1, max - min);
					is = true;
					break;
				}
				else {
					if (maxIdx != minIdx) {
						box[maxIdx]--;
						box[minIdx]++;
						
						max = box[0];
						min = box[0];
						maxIdx = 0;
						minIdx = 0;
						for (int i = 0; i < box.length; i++) {
							if (box[i] > max) {
								max = box[i];
								maxIdx = i;
							}
							if (box[i] < min) {
								min = box[i];
								minIdx = i;
							}
						}
					}
				}
			}
			if (!(is)) {
				System.out.printf("#%d %d\n", t + 1, max - min);
			}
		}
	}
}