import java.io.*;

// N개의 서로 다른 무게를 가진 추 (순서1, 순서2, ..., 순서N)
// 양팔저울
// 각 추를 양팔저울에 올리는 경우의 수 : 2^N * N! (왼쪽에 올리는지 오른쪽에 올리는지)
// 모든 과정에서 왼쪽 무게의 총합 >= 오른쪽 무게의 총합
// 무게는 1 이상 999 이하

public class Solution {
//	테스트 케이스
	static int T;
//	추의 개수
	static int N;
//	추의 무게 배열
	static int[] weight;
//	왼쪽 무게의 총합
	static int leftSum;
//	오른쪽 무게의 총합
	static int rightSum;
//	순열에서 쓸 방문체크 배열
	static boolean[] visited;
//	부분집합에서 왼쪽에 놓을지 오른쪽에 놓을지 결정할 때 쓸 배열
	static boolean[] isLeft;
//	순열에서 경우를 뽑았으면 저장할 배열
	static int[] output;
//	몇 가지 경우가 나오는지 카운팅할 변수
	static int result;
	
	static void permutation(int cnt) {
		if (cnt == N) {
			subset(0, 0, 0);
			return;
		}
		for (int i = 0; i < weight.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				output[cnt] = weight[i];
				permutation(cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	static void subset(int cnt, int left, int right) {
		if (cnt == N) {
			if (left >= right) result++;
			return;
		}
		if (left >= right) {
			subset(cnt + 1, left + output[cnt], right);
			subset(cnt + 1, left, right + output[cnt]);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		String[] srr;
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			srr = br.readLine().split(" ");
			weight = new int[N];
			output = new int[N];
			visited = new boolean[N];
			isLeft = new boolean[N];
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(srr[i]);
			}
			permutation(0);
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}
