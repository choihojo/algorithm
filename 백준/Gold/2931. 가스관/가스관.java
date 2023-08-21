import java.io.*;

//{우, 상, 좌, 하} (극좌표 순)
//{TF, TF, TF, TF} (T는 1이고 F는 0)

// 블록 | : 위에 "|+14" 중 하나, 아래에 "|+23" 중 하나
// -> {0, 1, 0, 1} -> "0101" (equals 메서드로 비교를 편하게 하기 위해 참거짓 여부를 스트링으로 나타냄)
// 블록 - : 왼쪽에 "-+12" 중 하나, 오른쪽에 "-+34" 중 하나
// -> {1, 0, 1, 0} -> "1010"
// 블록 + : 위에 "|+14" 중 하나, 아래에 "|+23" 중 하나, 왼쪽에 "-+12" 중 하나, 오른쪽에 "-+34" 중 하나
// -> {1, 1, 1, 1} -> "1111"
// 블록 1 : 오른쪽에 "-+34" 중 하나, 아래에 "|+23" 중 하나
// -> {1, 0, 0, 1} -> "1001"
// 블록 2 : 위에 "|+14" 중 하나, 오른쪽에 "-+34" 중 하나
// -> {1, 1, 0, 0} -> "1100"
// 블록 3 : 위에 "|+14" 중 하나, 왼쪽에 "-+12" 중 하나
// -> {0, 1, 1, 0} -> "0110"
// 블록 4 : 왼쪽에 "-+12" 중 하나, 아래에 "|+23" 중 하나
// -> {1, 0, 0, 1} -> "0011"

public class Main {
	static int R;
	static int C;
	static String[][] map;
//	4방위 탐색에 쓸 좌표
	static int[] dRow = new int[] {0, -1, 0, 1};
	static int[] dCol = new int[] {1, 0, -1, 0};
//	우상좌하에 올 수 있는 조합은 정해져 있으므로 미리 초기화
	static String[] fourStr = new String[] {"-+34", "|+14", "-+12", "|+23"};
//	"0101"과 같이 참거짓 스트링으로 쓸 변수이자 스트링빌더
	static StringBuilder four;
//	결과값 저장할 스트링빌더
	static StringBuilder result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] srr;
		String str;
		srr = br.readLine().split(" ");
		R = Integer.parseInt(srr[0]);
		C = Integer.parseInt(srr[1]);
		map = new String[R][C];
		for (int r = 0; r < R; r++) {
			str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = String.valueOf(str.charAt(c));
			}
		}
		
		four = new StringBuilder();
		result = new StringBuilder();
		int row = 0;
		int col = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
//				"."일 경우에 4방위 탐색
				if (map[r][c].equals(".")) {
//					4방위 참거짓 판단용 스트링빌더 초기화
					four.setLength(0);
					four.append("0000");
					for (int i = 0; i < 4; i++) {
						row = r + dRow[i];
						col = c + dCol[i];
//						우상좌하에 가능한 블록이 있었다면 해당 위치의 스트링을 1로 바꿔줌
						if (row >= 0 && row < R && col >= 0 && col < C) {
							if (fourStr[i].contains(map[row][col])) four.setCharAt(i, '1');
						}
					}
//					이 중 하나에 속하면 결과값 저장
					if (four.toString().equals("0101")) result.append(r + 1).append(" ").append(c + 1).append(" ").append("|");
					else if (four.toString().equals("1010")) result.append(r + 1).append(" ").append(c + 1).append(" ").append("-");
					else if (four.toString().equals("1111")) result.append(r + 1).append(" ").append(c + 1).append(" ").append("+");
					else if (four.toString().equals("1001")) result.append(r + 1).append(" ").append(c + 1).append(" ").append("1");
					else if (four.toString().equals("1100")) result.append(r + 1).append(" ").append(c + 1).append(" ").append("2");
					else if (four.toString().equals("0110")) result.append(r + 1).append(" ").append(c + 1).append(" ").append("3");
					else if (four.toString().equals("0011")) result.append(r + 1).append(" ").append(c + 1).append(" ").append("4");
				}
			}
		}
		
		System.out.println(result);
	}
}