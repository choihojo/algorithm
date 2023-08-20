
import java.io.*;

public class Main {
	static int N;
	static int r;
	static int c;
	static int target;
	static int[][] map;
	static int power;
	static int cnt = 0;
	
//	굳이 배열을 만들 필요는 없음
//	아무런 가지치기도 하지 않고 재귀를 돌리면 시간초과가 뜸
//	4분할로 4개의 재귀로 나눠지는 것이 아니라 r과 c의 값에 따라 필요한 영역에 대해서만 1개의 재귀로 진행해야 함
	static void zSearch(int row, int col, int size) {
		if (size == 2) {
			for (int i = row; i < row + size; i++) {
				for (int j = col; j < col + size; j++) {
					if (i == r && j == c) {
						target = cnt;
					}
					cnt++;
				}
			}
			return;
		}
		int mid = size / 2;
//		4분할 영역을
//		0 1
//		2 3
//		이라 할 때
//		0 영역에 있으면 cnt에 0*mid*mid을 더해줌
//		1 영역에 있으면 cnt에 1*mid*mid를 더해줌
//		2 영역에 있으면 cnt에 2*mid*mid를 더해줌
//		3 영역에 있으면 cnt에 3*mid*mid를 더해줌
//		왜냐면 0 영역 카운팅이 끝나고 1 영역 카운팅이 시작되는데 사실상 1 영역 카운팅의 시작점은 굳이 0 영역을 해볼 필요도 없이 정해져 있는 거임
//		이런 방식으로 사이즈가 2가 될 때까지 필요한 4분면 중 하나로 좁혀지고 사이즈가 2가 되면 루프를 돌면서 행과 열이 일치하는 순간의 카운팅값을 target에 저장함
		if (r < row + mid && c < col + mid) {
			cnt += 0 * mid * mid;
			zSearch(row, col, mid);
		}
		else if (r < row + mid && c >= col + mid) {
			cnt += 1 * mid * mid;
			zSearch(row, col + mid, mid);
		}
		else if (r >= row + mid && c < col + mid) {
			cnt += 2 * mid * mid;
			zSearch(row + mid, col, mid);
		}
		else if (r >= row + mid && c >= col + mid) {
			cnt += 3 * mid * mid;
			zSearch(row + mid, col + mid, mid);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr = br.readLine().split(" ");
//		N이 1이면 안 쪼개지고 바로 탐색 
//		N이 2이면 1번 4방향으로 쪼개짐
//		N이 3이면 2번 4방향으로 쪼개짐
//		N번이면 (N - 1)번 쪼개짐
		N = Integer.parseInt(srr[0]);
		r = Integer.parseInt(srr[1]);
		c = Integer.parseInt(srr[2]);
//		설마 부동소숫점 때문에 틀리는 건 아니겠지 싶어서 비트 연산으로 고쳐봄
//		-> 이게 아니라 위에서 조건 지우다가 실수로 잘못 지웠음
		power = 2 << N;
		zSearch(0, 0, power);
		System.out.println(target);
	}
}