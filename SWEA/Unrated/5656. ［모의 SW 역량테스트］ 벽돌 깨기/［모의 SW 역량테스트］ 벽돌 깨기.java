import java.util.*;
import java.io.*;

public class Solution {
	static int T;
	static int N, W, H;
	static int[][] map;
	static boolean[][] visited;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
	static int result;
	
	static void permutation(int cnt) {
		if (cnt == N) {
//			남은 벽돌 개수 측정
			int remain = count();
			result = result > remain ? remain : result;
			return;
		}
//		나중에 원상복구를 위해 미리 tempMap에 기존 map 정보를 저장해둠
//		이 원상복구 코드가 시간 단축의 핵심임
//		만약 순서를 중복순열로 다 뽑고 나서 구슬을 쏘면 중복되는 연산이 많이 발생함
//		예를 들어 N=4이고 1행, 3행, 4행까지 고르고 나서 마지막 쏠 위치를 고르는 상황을 가정해보겠음
//		(1행, 3행, 4행, 1행), (1행, 3행, 4행, 2행), ... 이런 식으로 여러 가지 경우의 수가 있을 것임
//		근데 이렇게 다 뽑고 나서 구슬을 쏘면 매번 쏠 때마다 (1행, 3행, 4행)까지의 과정을 중복해서 수행하게 됨
//		이걸 방지하려면 구슬을 하나 쏠 때마다 결과 배열을 tempMap에다 저장해둬야 함
//		우선 (1행, 3행, 4행)까지 쏘고 나서의 결과 배열을 tempMap에다 저장해둠
//		(1행, 3행, 4행, 1행)을 수행하고 돌아왔으면, 저장해둔 tempMap을 가지고 map에 복사하여 바로 (1행, 3행, 4행, 2행)의 과정을 수행하러 가는 것임
		int[][] tempMap = new int[H][W];
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				tempMap[h][w] = map[h][w];
			}
		}
		for (int oCol = 0; oCol < W; oCol++) {
			int oRow = findRow(oCol);
//			continue가 최선이긴 한데, continue를 쓰지 않고 이 때 개수를 세어도 됨
			if (oRow == H) continue;
			resetVisited();
			shoot(oRow, oCol);
			update();
			permutation(cnt + 1);
//			cnt번째에 oCol열에 쏘고 나서 이번엔 cnt번째에 oCol+1열에 쏴봄 (for루프이므로)
//			위에서 (1행, 3행, 4행, 1행)을 수행하고 돌아왔으면 이번에는 (1행, 3행, 4행, 2행)을 수행하러 가야 하므로 그 전에 map을 되돌려놓음
//			보통 원상복구라 하면 해당 위치 방문체크를 다시 되돌려놓고 이러는 경우가 많은데 이렇게 배열 원상복구도 할 줄 알아야 함
//			만약 보호 필름 문제와 같이 원상복구해야되는 위치와 값이 정확히 정해져 있다면 별도로 tempMap을 둘 필요는 없겠지만, 위치와 값이 달라지는 이런 경우에는 이전 시점의 배열을 tempMap에 미리 저장해둘 필요가 있음 
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					map[h][w] = tempMap[h][w];
				}
			}
		}
	}
	
//	깰 벽돌을 체크하는 코드
	static void shoot(int oRow, int oCol) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {oRow, oCol});
		visited[oRow][oCol] = true;
		int[] poll = null;
		int row = 0;
		int col = 0;
		int pRow = 0;
		int pCol = 0;
		int power = 0;
		while (!queue.isEmpty()) {
			poll = queue.poll();
			pRow = poll[0];
			pCol = poll[1];
			power = map[pRow][pCol];
			for (int p = 1; p < power; p++) {
				for (int i = 0; i < 4; i++) {
					row = pRow + p * dRow[i];
					col = pCol + p * dCol[i];
					if (row >= 0 && row < H && col >= 0 && col < W) {
						if (!visited[row][col]) {
							queue.offer(new int[] {row, col});
							visited[row][col] = true;	
						}
					}
				}
			}
		}
	}
	
//	shoot()에서 체크한 벽돌들을 모두 깨고 깨진 벽돌보다 위에 있는 벽돌을 아래로 내리는 코드 (배열 업데이트)
    static void update() {
        int[] irr = new int[H];
        for (int w = 0; w < W; w++) {
            int count = 0;
            Arrays.fill(irr, 0);
            for (int h = H - 1; h >= 0; h--) {
                if (!visited[h][w]) {
                    irr[count] = map[h][w];
                    count++;
                }
            }
            int index = 0;
            for (int h = H - 1; h >= 0; h--) {
                map[h][w] = irr[index];
                index++;
            }
        }
    }
    
//    남은 벽돌의 개수를 세는 코드
    static int count() {
        int count = 0;
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                if (map[h][w] > 0) count++;
            }
        }
        return count;
    }
	
//    구슬을 쏴야 하는 열을 정했으면 정확한 행의 위치도 구해야 함 (처음으로 0이 아닌 값이 나오는 지점을 찾으면 됨)
	static int findRow(int oCol) {
		int oRow = 0;
		while (oRow < H) {
			if (map[oRow][oCol] != 0) break;
			oRow++;
		}
		return oRow;
	}
	
//	방문체크 초기화
//	구슬을 쏠 때마다 매번 새로운 방문체크 배열을 사용해야 함 (이전에 쓴 방문체크 배열을 그대로 쓰면 아래층이 깨져서 내려온 벽돌이 또 깨질 수 있음)
    static void resetVisited() {
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                visited[h][w] = false;
            }
        }
    }
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
//			남은 벽돌의 개수인 result 초기화
			result = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			visited = new boolean[H][W];
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			permutation(0);
//			result가 MAX_VALUE로 그대로 남아있었다는 소리는 permutation()에서 개수를 세는 과정 자체가 수행되지 않았다는 것임
//			permutation에서 개수를 세는 과정이 수행되지 않았다는 소리는 cnt가 증가해서 N이 되기 전에 재귀가 끝났다는 것을 의미함
//			그렇다면 cnt가 증가하지 않는 경우는 어떤 경우인가?
//			구슬을 쏴야 하는 열이 모두 0으로 이루어져 있을 때임 (oRow == H)
//			만약 도중에 쏴야하는 행을 찾았다면 findRow() 메서드에서 oRow가 0~(H-1) 중 하나로 나왔을 것이기 때문임
//			결국 구슬을 N번 쏘지 않더라도 모든 벽돌을 깬 경우에 result가 MAX_VALUE로 남아있다는 것임 (만약 다른 열에 벽돌이 남아있었으면 해당 부분을 쏘면서 cnt가 증가할 것임)
//			그래서 result가 MAX_VALUE로 남아있다면 모든 벽돌을 깬 것이 자명하므로 0으로 바꿔줌
//			이렇게 하지 않고 좀 비효율적이겠지만 permutation()의 continue 부분에서 개수를 세는 코드를 넣어도 될 듯함
			result = result == Integer.MAX_VALUE ? 0 : result;
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}