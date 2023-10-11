import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, map[][], max = Integer.MIN_VALUE;
	static int res[] = new int[5];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combi(0);
		toDo();
		System.out.println(max);
	}
	
	private static void combi(int cnt) {
		if(cnt == 5) {
//			System.out.println(Arrays.toString(res));
			toDo();
			count();
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			res[cnt] = i;
			combi(cnt + 1);
		}
	}
	
	private static void count() {
		int temp = tmpmap[0][0];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(temp < tmpmap[i][j]) {
					temp = tmpmap[i][j];
				}
			}
		}
		if(max < temp) {
			max = temp;
		}
	}
	
	static int tmpmap[][];
	private static void toDo() {
		tmpmap = new int[N][N];
		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
			tmpmap[i] = Arrays.copyOf(map[i], N);
		}
//		System.out.println();
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
		for(int i = 0; i < 5; i++) {
			switch (res[i]) {
			case 0:
//				System.out.println("top");
				top();
				break;
			case 1:
//				System.out.println("bottom");
				bottom();
				break;
			case 2:
//				System.out.println("left");
				left();
				break;
			case 3:
//				System.out.println("right");
				right();
				break;
			}
		}
	}

	private static void top() {
		for(int i = 0; i < N; i++) {
			int idx = 0;
			int temp[] = new int[N];
			for(int j = 0; j < N; j++) {
				boolean isget = true;
				if(tmpmap[j][i] != 0) {
					for(int k = j + 1; k < N; k++) {
						if(tmpmap[k][i] != 0) {
							if(tmpmap[k][i] == tmpmap[j][i]) {
								temp[idx] = tmpmap[k][i] * 2;
								tmpmap[k][i] = 0;
								idx++;
								isget = false;
								break;
							}else {
								temp[idx] = tmpmap[j][i];
								idx++;
								isget = false;
								break;
							}
						}
					}
					if(isget) {
						temp[idx] = tmpmap[j][i];
						idx++;
					}
				}
			}
			for(int j = 0; j < N; j++) {
				tmpmap[j][i] = temp[j];
			}
		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
	}

	private static void bottom() {
		for(int i = 0; i < N; i++) {
			int idx = N - 1;
			int temp[] = new int[N];
			for(int j = N - 1; j >= 0; j--) {
				boolean isget = true;
				if(tmpmap[j][i] != 0) {
					for(int k = j - 1; k >= 0; k--) {
						if(tmpmap[k][i] != 0) {
							if(tmpmap[k][i] == tmpmap[j][i]) {
								temp[idx] = tmpmap[k][i] * 2;
								tmpmap[k][i] = 0;
								idx--;
								isget = false;
								break;
							}else {
								temp[idx] = tmpmap[j][i];
								idx--;
								isget = false;
								break;
							}
						}
					}
					if(isget) {
						temp[idx] = tmpmap[j][i];
						idx--;
					}
				}
			}
			for(int j = 0; j < N; j++) {
				tmpmap[j][i] = temp[j];
			}
		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
	}

	private static void left() {
		for(int i = 0; i < N; i++) {
			int idx = 0;
			int temp[] = new int[N];
			for(int j = 0; j < N; j++) {
				boolean isget = true;
				if(tmpmap[i][j] != 0) {
					for(int k = j+1; k < N; k++) {
						if(tmpmap[i][k] != 0) {
							if(tmpmap[i][k] == tmpmap[i][j]) {
								temp[idx] = tmpmap[i][k] * 2;
								tmpmap[i][k] = 0;
								idx++;
								isget = false;
								break;
							}else {
								temp[idx] = tmpmap[i][j];
								idx++;
								isget = false;
								break;
							}
						}
					}
					if(isget) {
						temp[idx] = tmpmap[i][j];
						idx++;
					}
				}
			}
			for(int j = 0; j < N; j++) {
				tmpmap[i][j] = temp[j];
			}
		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
	}

	private static void right() {
		for(int i = 0; i < N; i++) {
			int idx = N - 1;
			int temp[] = new int[N];
			for(int j = N - 1; j >= 0; j--) {
				boolean isget = true;
				if(tmpmap[i][j] != 0) {
					for(int k = j - 1; k >= 0; k--) {
						if(tmpmap[i][k] != 0) {
							if(tmpmap[i][k] == tmpmap[i][j]) {
								temp[idx] = tmpmap[i][k] * 2;
								tmpmap[i][k] = 0;
								idx--;
								isget = false;
								break;
							}else {
								temp[idx] = tmpmap[i][j];
								idx--;
								isget = false;
								break;
							}
						}
					}
					if(isget) {
						temp[idx] = tmpmap[i][j];
						idx--;
					}
				}
			}
			for(int j = 0; j < N; j++) {
				tmpmap[i][j] = temp[j];
			}
		}
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(tmpmap[i]));
//		}
//		System.out.println();
	}
}
