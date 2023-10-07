import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int T;
    static int N, W, H;
//    전체 벽돌 배열
    static int[][] map;
    static int[][] originalMap;
//    인덱싱 위한 배열
    static int[] index;
//    인덱싱 순서 뽑은 배열
    static int[] sequence;
    static int[] dRow = new int[] {0, -1, 0, 1};
    static int[] dCol = new int[] {1, 0, -1, 0};
    static boolean[][] breakMap;
    static int min;
     
    static void doublePermutation(int count) {
        if (count == N) {
//          System.out.println(Arrays.toString(sequence));
            resetMap();
            for (int i = 0; i < N; i++) {
                int oRow = findPosition(sequence[i]);
                int oCol = sequence[i];
//                System.out.println("oRow : " + oRow);
//                System.out.println("oCol : " + oCol);
                if (oRow < H) {
                    resetBreakMap();
                    shoot(oRow, oCol);
                    update();
//                    for (int[] irr : map) {
//                        System.out.println(Arrays.toString(irr));
//                    }
                }
            }
//            System.out.println(Arrays.toString(sequence));
            int temp = count();
//            System.out.println("count : " + temp);
            if (min > temp) min = temp;
             
//            System.out.println("==================================");
            return;
        }
         
        for (int i = 0; i < index.length; i++) {
            sequence[count] = index[i];
            doublePermutation(count + 1);
        }
    }
     
    static void shoot(int oRow, int oCol) {
        int power = map[oRow][oCol];
        int row = 0;
        int col = 0;
        breakMap[oRow][oCol] = true;
        for (int i = 0; i < 4; i++) {
            for (int p = 1; p < power; p++) {
                row = oRow + p * dRow[i];
                col = oCol + p * dCol[i];
                if (row >= 0 && row < H && col >= 0 && col < W) {
                    if (map[row][col] > 0) {
                        if (!breakMap[row][col]) {
//                            System.out.println("row : " + row);
//                            System.out.println("col : " + col);
                            shoot(row, col);
                        }
                    }
                    else continue;
                }
            }
        }
    }
     
    static void update() {
        int[] irr = new int[H];
        for (int w = 0; w < W; w++) {
            int count = 0;
            Arrays.fill(irr, 0);
            for (int h = H - 1; h >= 0; h--) {
                if (!breakMap[h][w]) {
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
     
    static int count() {
        int count = 0;
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                if (map[h][w] > 0) count++;
            }
        }
        return count;
    }
     
    static int findPosition(int oCol) {
        int oRow = 0;
        while (oRow < H) {
            if (map[oRow][oCol] > 0) {
                break;
            }
            oRow++;
        }
        return oRow;
    }
     
    static void resetMap() {
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                map[h][w] = originalMap[h][w];
            }
        }
    }
     
    static void resetBreakMap() {
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                breakMap[h][w] = false;
            }
        }
    }
     
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            min = Integer.MAX_VALUE;
             
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
             
            map = new int[H][W];
            originalMap = new int[H][W];
            breakMap = new boolean[H][W];
            for (int h = 0; h < H; h++) {
                st = new StringTokenizer(br.readLine());
                for (int w = 0; w < W; w++) {
                    originalMap[h][w] = Integer.parseInt(st.nextToken());
                }
            }
             
//            for (int[] irr : map) {
//                System.out.println(Arrays.toString(irr));
//            }
             
            index = new int[W];
            for (int w = 0; w < W; w++) {
                index[w] = w;
            }
             
            sequence = new int[N];
            doublePermutation(0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}