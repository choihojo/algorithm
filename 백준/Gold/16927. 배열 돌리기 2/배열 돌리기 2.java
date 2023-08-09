
import java.io.*;

public class Main {
	static int[][] input;
	static int[][] result;
	static int R;
	static int loop;
	
    public static void main(String[] args) throws Exception {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] srr = br.readLine().split(" ");
        int N = Integer.parseInt(srr[0]);
        int M = Integer.parseInt(srr[1]);
        R = Integer.parseInt(srr[2]);

        input = new int[N][M];
        result = new int[N][M];

        for (int i = 0; i < N; i++) {
            srr = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                input[i][j] = Integer.parseInt(srr[j]);
                result[i][j] = input[i][j];
            }
        }

        loop = Math.min(N, M) / 2;
//		바깥쪽 테두리부터 시작해서 몇 번 루프를 돌아야 하는지 계산

        int colMin = 0;
        int colMax = M - 1;

    int rowMin = 0;
    int rowMax = N - 1;
    int real = 0;

    for (int l = 0; l < loop; l++) {
    	real = R % (2 * (colMax - colMin + 1) + 2 * (rowMax - rowMin + 1) - 4);
//      각 테두리에서 몇 번 돌려야 하는지 계산
//    	나머지 연산을 통해 제자리로 돌아오는 경우는 제외하여 연산 줄임
    	for (int r = 0; r < real; r++) {
//    	돌려야 되는 칸 수
            for (int flag = 0; flag < 4; flag++) {
//            반시계 방향으로 테두리 1라인씩 돌림
                if (flag % 4 == 0) {
                    int row = rowMin;
                    for (int col = colMin; col < colMax; col++) {
                        result[row][col] = input[row][col + 1];
                    }
//                    위쪽 가로라인
                }
                else if (flag % 4 == 1) {
                    int col = colMin;
                    for (int row = rowMin; row < rowMax; row++) {
                        result[row + 1][col] = input[row][col];
                    }
//                    왼쪽 세로라인
                }
                else if (flag % 4 == 2) {
                    int row = rowMax;
                    for (int col = colMin; col < colMax; col++) {
                        result[row][col + 1] = input[row][col];
                    }
//                    아래쪽 가로라인
                }
                else if (flag % 4 == 3) {
                    int col = colMax;
                    for (int row = rowMin; row < rowMax; row++) {
                        result[row][col] = input[row + 1][col];
                    }
//                    오른쪽 세로라인
                }
            }

            for (int a = 0; a < N; a++) {
                for (int b = 0; b < M; b++) {
                    input[a][b] = result[a][b];
                }
            }
//            배열을 갱신함 (1칸 옮기는 걸 여러번 반복하기 위해서는 이전 상태를 반영하고 1칸 옮겨야 함)
            
        }
        
        colMin++;
        colMax--;
        rowMin++;
        rowMax--;
    }
    
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < N; i++) {
    	for (int j = 0; j < M; j++) {
    		if (j == M - 1) sb.append(result[i][j]);
    		else sb.append(result[i][j]).append(" ");
    	}
    	if (i != N - 1) sb.append("\n");
    }
    
    System.out.println(sb.toString());

}
}