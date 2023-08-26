import java.io.*;

// 막을 D장 쌓아서 얻은 두께 D, 가로 크기 W의 보호 필름
// 단면의 모든 세로방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있어야 함
// 성능검사에 통과하기 위해 막 단위(가로방향에 주입)로 약품주입가능
// 약품 종류는 A(0), B(1) 두 가지가 존재함
// 성능검사를 통과할 수 있는 약품주입횟수의 최솟값 구하기

// 일단 대충 생각해봤을 때 부분집합도 가능하고 조합 + 부분집합도 가능함
// 근데 최솟값을 구하는 의미에는 후자가 더 적절할 듯
// 전자도 답이 나오긴 하겠지만 시간이 더 오래걸릴 듯

public class Solution {
	static int T;
	static int D, W, K;
	static int[] input;
	static int[] output;
	static boolean[] selected;
	static int[][] film;
	static int[][] tempFilm;
	static int min;
	
	static void combination(int goal, int cnt, int start) {
		if (cnt == goal) {
			subset(goal, 0);
			return;
		}
		for (int i = start; i < input.length; i++) {
			output[cnt] = input[i];
			combination(goal, cnt + 1, i + 1);
		}
	}
	
	static void subset(int goal, int cnt) {
		if (cnt == goal) {
			init();
			if (check()) min = goal;
			return;
		}
		selected[cnt] = true;
		subset(goal, cnt + 1);
		selected[cnt] = false;
		subset(goal, cnt + 1);
	}
	
	static void init() {
//		매 경우의 수마다 임시배열 초기화
		tempFilm = new int[D][W];
		for (int i = 0; i < D; i++) {
			for (int j = 0; j < W; j++) {
				tempFilm[i][j] = film[i][j];
			}
		}
		
//		약품주입
		for (int i = 0; i < output.length; i++) {
//			약품주입할 i번째 막
			int idx = output[i];
//			약품 종류가 A(0)인지 B(1)인지 선택
			boolean flag = selected[i];
			if (flag) {
				for (int j = 0; j < W; j++) tempFilm[idx][j] = 0; 
			}
			else {
				for (int j = 0; j < W; j++) tempFilm[idx][j] = 1;
			}
		}
	}
	
	static boolean check() {
		A : for (int j = 0; j < W; j++) {
			int sumA = 0;
			int sumB = 0;
			for (int i = 0; i < D; i++) {
				if (tempFilm[i][j] == 0) {
					sumA++;
					sumB = 0;
				}
				else {
					sumB++;
					sumA = 0;
				}
				if (sumA == K || sumB == K) continue A;
			}
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			String[] srr = br.readLine().split(" ");
			D = Integer.parseInt(srr[0]);
			W = Integer.parseInt(srr[1]);
			K = Integer.parseInt(srr[2]);
			min = -1;
			
			input = new int[D];
			for (int i = 0; i < D; i++) input[i] = i;
			
			film = new int[D][W];
			tempFilm = new int[D][W];
			
			for (int i = 0; i < D; i++) {
				srr = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(srr[j]);
				}
			}
			
//			0개 선택에서부터 D개 선택까지 차근차근 올라감
//			만약 도중에 최솟값이 발견되면 바로 탈출함
			for (int i = 0; i <= D; i++) {
				output = new int[i];
				selected = new boolean[i];
				combination(i, 0, 0);
				if (min != -1) break;
			}
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}