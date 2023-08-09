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

        int colMin = 0;
        int colMax = M - 1;

    int rowMin = 0;
    int rowMax = N - 1;

    int cnt = 0;
    int real = 0;

    
    for (int l = 0; l < loop; l++) {
    	real = R % (2 * (colMax - colMin + 1) + 2 * (rowMax - rowMin + 1) - 4);
        for (int r = 0; r < real; r++) {
            for (int flag = 0; flag < 4; flag++) {
                if (flag % 4 == 0) {
                    int row = rowMin;
                    for (int col = colMin; col < colMax; col++) {
                        result[row][col] = input[row][col + 1];
                    }
                }
                else if (flag % 4 == 1) {
                    int col = colMin;
                    for (int row = rowMin; row < rowMax; row++) {
                        result[row + 1][col] = input[row][col];
                    }
                }
                else if (flag % 4 == 2) {
                    int row = rowMax;
                    for (int col = colMin; col < colMax; col++) {
                        result[row][col + 1] = input[row][col];
                    }
                }
                else if (flag % 4 == 3) {
                    int col = colMax;
                    for (int row = rowMin; row < rowMax; row++) {
                        result[row][col] = input[row + 1][col];
                    }
                }
            }

            for (int a = 0; a < input.length; a++) {
                for (int b = 0; b < input[0].length; b++) {
                    input[a][b] = result[a][b];
                }
            }
        }
        
        colMin++;
        colMax--;
        rowMin++;
        rowMax--;
    }
    
    

    
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < input.length; i++) {
    	for (int j = 0; j < input[1].length; j++) {
    		if (j == input[1].length - 1) sb.append(result[i][j]);
    		else sb.append(result[i][j]).append(" ");
    	}
    	if (i != input.length - 1) sb.append("\n");
    }
    
    System.out.println(sb.toString());

}
}