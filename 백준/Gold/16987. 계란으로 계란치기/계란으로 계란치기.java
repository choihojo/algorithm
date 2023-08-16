import java.io.*;

// 우선 단순 순열로도 풀리는지 확인용
// 가지치기의 당위성 생각해볼 것
public class Main {
	static int N;
	static int[][] egg;
	static int[][] tempEgg;
	static int[] input;
	static int[] output;
	static int temp;
	static int max;
	
	static void permutation(int cnt) {
		if (cnt == N) {
			temp = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 2; j++) {
					tempEgg[i][j] = egg[i][j];
				}
			}
			for (int i = 0; i < N; i++) {
//				i번째 계란이란 output[i]번째 계란이랑 부딪혀야 함
				if (tempEgg[i][0] > 0 && tempEgg[output[i]][0] > 0) {
					tempEgg[i][0] -= tempEgg[output[i]][1];
					tempEgg[output[i]][0] -= tempEgg[i][1];
				}
			}
			for (int i = 0; i < N; i++) {
				if (tempEgg[i][0] <= 0) {
					temp++;
				}
			}
			if (temp > max) {
				max = temp;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (i != cnt) {
				output[cnt] = input[i];
				permutation(cnt + 1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr;
		N = Integer.parseInt(br.readLine());
		egg = new int[N][2];
		tempEgg = new int[N][2];
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
			for (int j = 0; j < 2; j++) {
				egg[i][j] = Integer.parseInt(srr[j]);
			}
		}
//		인덱싱 순열을 만들 때 쓸 입력배열
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = i;
		}
//		인덱싱 순열을 저장할 출력배열
		output = new int[N];
		permutation(0);
		System.out.println(max);
	}
}