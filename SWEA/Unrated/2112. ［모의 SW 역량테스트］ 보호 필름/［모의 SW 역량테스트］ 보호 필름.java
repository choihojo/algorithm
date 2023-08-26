import java.io.*;

public class Solution {
	static int T;
	static int D, W, K;
	static int min;
	static int[][] map;
	
	static void dfs(int cnt, int num) {
		if (min < num) return;
		if (check()) {
			if (min > num) min = num;
			return;
		}
//		단 하나의 약품도 주입하지 않고 끝까지 같을 때 탈출하기 위한 조건문
//		어차피 위에서 다 고려되기 때문에 여기서 따로 min값을 갱신시킬 필요는 없음
		if (cnt == D) {
			return;
		}
		
//		백업
		int[] backup = new int[W];
		for (int j = 0; j < W; j++) {
			backup[j] = map[cnt][j];
		}

//		약품 주입 X
		dfs(cnt + 1, num);
		
//		A약품 주입 (parameter가 아니라 reference 변수를 건드렸다면 원상복구해야함)
		injection(cnt, 0);
		dfs(cnt + 1, num + 1);
		reset(cnt, backup);
		
//		B약품 주입
		injection(cnt, 1);
		dfs(cnt + 1, num + 1);
		reset(cnt, backup);
	}
	
	static void injection(int cnt, int type) {
		for (int j = 0; j < W; j++) map[cnt][j] = type;
	}
	
	static void reset(int cnt, int[] backup) {
		for (int j = 0; j < W; j++) {
			map[cnt][j] = backup[j];
		}
	}
	
	static boolean check() {
		outer : for (int j = 0; j < W; j++) {
			int sumA = 0;
			int sumB = 0;
			for (int i = 0; i < D; i++) {
				if (map[i][j] == 0) {
					sumA++;
					sumB = 0;
				}
				else {
					sumB++;
					sumA = 0;
				}
				if (sumA == K || sumB == K) continue outer;
			}
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] srr;
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			srr = br.readLine().split(" ");
			D = Integer.parseInt(srr[0]);
			W = Integer.parseInt(srr[1]);
			K = Integer.parseInt(srr[2]);
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				srr = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(srr[j]);
				}
			}
			dfs(0, 0);
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}