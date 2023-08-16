import java.io.*;

public class Main {
	static int[] team1 = new int[] {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] team2 = new int[] {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};
	static int[][] input;
	static int[][] output;
	static boolean flag;
	
	public static void worldCup(int round) {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (input[i][j] < output[i][j]) {
					return;
				}
			}
		}
		
		if (round == 15) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (input[i][j] != output[i][j]) {
						return;
					}
				}
			}
			flag = true;
			return;
		}
		
//		이기는 경우
		output[team1[round]][0]++;
		output[team2[round]][2]++;
		worldCup(round + 1);
		output[team1[round]][0]--;
		output[team2[round]][2]--;
		
//		비기는 경우
		output[team1[round]][1]++;
		output[team2[round]][1]++;
		worldCup(round + 1);
		output[team1[round]][1]--;
		output[team2[round]][1]--;
		
//		지는 경우
		output[team1[round]][2]++;
		output[team2[round]][0]++;
		worldCup(round + 1);
		output[team1[round]][2]--;
		output[team2[round]][0]--;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = new int[6][3];
		output = new int[6][3];
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {
			flag = false;
			String[] srr = br.readLine().split(" ");
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					input[j][k] = Integer.parseInt(srr[3 * j + k]);
				}
			}
			worldCup(0);
			
			sb.append(flag ? 1 : 0).append(" ");
		}
		
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}