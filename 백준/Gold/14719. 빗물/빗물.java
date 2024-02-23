import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int H, W;
	static int[][] irr;
	
	// 오른쪽으로 계속 전진하면서 높이 변화를 관찰해야 함

	// 시작점 찾기
	// 다음 높이가 더 높을 경우 높이 갱신해야 함
	// 다음 높이가 더 낮을 경우 그냥 스킵해야 함
	// 스킵하다가 다음 높이가 다시 높아졌을 경우 끝점 찾으러 가야 함
	
	// 끝점 찾기
	// 다음 높이가 더 높을 경우 높이 갱신해야 함
	// 다음 높이가 시작점보다 더 높아졌을 경우 해당 해당 높이가 다시 시작점이 됨
	// 다음 높이가 더 낮을 경우 그냥 스킵해야 함
	
	// 이렇게 하면 어려우니까 그냥 각 행을 쓸고가면서 개수 세는 게 나을 듯함
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		irr = new int[H][W];
		
		st = new StringTokenizer(br.readLine());
		for (int w = 0; w < W; w++) {
			int height = Integer.parseInt(st.nextToken());
			for (int h = H - height; h < H; h++) {
				irr[h][w] = 1;
			}
		}
		
		int cnt = 0;
		int result = 0;
		boolean start = false;
		
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				
				if (irr[h][w] == 1) {
					if (!start) { 
						start = true;
					}
					else {
						result += cnt;
						cnt = 0;
					}
				}
				else {
					if (!start) {
						continue;
					}
					else {
						cnt++;
					}
				}
			}
			cnt = 0;
			start = false;
		}
		
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				sb.append(irr[i][j]).append(" ");
//			}
//			sb.append("\n");
//		}
//		System.out.println(sb);
		
		System.out.println(result);
	}
}