import java.io.*;
import java.util.*;
 
// N * N 정사각형 셀 (인덱싱 0부터 시작함)
// 약품 셀에 도착하면 미생물 절반만 남고(/2) 이동방향이 반대로 바뀜
// 두 개 이상의 군집이 한 셀에 모이는 경우 군집이 합쳐짐 (미생물은 합치고 이동방향은 기존 가장 많았던 군집의 방향)
// M시간 후 남아있는 미생물 수의 총합을 구해야함
// 상(1), 하(2), 좌(3), 우(4) 방향으로 주어짐
// 군집 정보는 : row, col, 미생물 수, 이동 방향 순으로 주어짐
 
// 미생물 합치는 상황을 구현하는 것이 핵심인 듯
// 기존 BFS대로 가면 군집 하나를 빼고 다시 큐에 넣는 식이라 합치는 상황 구현이 어려움
// 합쳐지는 군집 세포수 따로, 기존 가장 큰 영향을 줬던 세포수 따로, 기존 방향 따로 저장하면 구현 가능할 듯
 
// DFS도 아닌 것 같고 BFS도 아닌 거 같고 문제의 목적이 뭘까...
 
public class Solution {
    static int T;
//  셀의 개수, 격리 시간, 군집의 개수 순
    static int N, M, K;
    static Data[][] map;
    static Data[][] tempMap;
    static int[] dRow = {0, -1, 1, 0, 0};
    static int[] dCol = {0, 0, 0, -1, 1};
    static int sum;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        String[] srr;
        StringBuilder sb = new StringBuilder();
         
        for (int t = 1; t <= T; t++) {
            sum = 0;
            srr = br.readLine().split(" ");
            N = Integer.parseInt(srr[0]);
            M = Integer.parseInt(srr[1]);
            K = Integer.parseInt(srr[2]);
            map = new Data[N][N];
            tempMap = new Data[N][N];
            for (int k = 0; k < K; k++) {
                srr = br.readLine().split(" ");
                int row = Integer.parseInt(srr[0]);
                int col = Integer.parseInt(srr[1]);
                int num = Integer.parseInt(srr[2]);
                int type = Integer.parseInt(srr[3]);
                map[row][col] = new Data(num, num, type);
            }
             
            int row = 0;
            int col = 0;
            for (int m = 0; m < M; m++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == null) continue;
                        row = i + dRow[map[i][j].type];
                        col = j + dCol[map[i][j].type];
                        if (tempMap[row][col] == null) {
                            tempMap[row][col] = map[i][j];
                        }
                        else {
                            if (tempMap[row][col].max > map[i][j].max) tempMap[row][col].num += map[i][j].num;
                            else {
                                tempMap[row][col].num += map[i][j].num;
                                tempMap[row][col].max = map[i][j].max;
                                tempMap[row][col].type = map[i][j].type;
                            }
                        }
                        map[i][j] = null;
                    }
                }
                 
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (tempMap[i][j] == null) continue;
                        if (i == 0 || i == (N - 1) || j == 0 || j == (N - 1)) {
                            if (tempMap[i][j].type == 1) tempMap[i][j].type = 2;
                            else if (tempMap[i][j].type == 2) tempMap[i][j].type = 1;
                            else if (tempMap[i][j].type == 3) tempMap[i][j].type = 4;
                            else if (tempMap[i][j].type == 4) tempMap[i][j].type = 3;
                            tempMap[i][j].num /= 2;
                        }
                    }
                }
                 
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (tempMap[i][j] == null) continue;
//                      map[i][j] = new Data(tempMap[i][j].num, tempMap[i][j].num, tempMap[i][j].type);
//                      위 한 줄의 코드와 아래 두 줄의 코드가 사실상 같은 의미라는 걸 이해할 것 (객체 참조, 딥카피, 소프트카피)
                        map[i][j] = tempMap[i][j];
                        map[i][j].max = map[i][j].num;
                        tempMap[i][j] = null;
                    }
                }
            }
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == null) continue;
                    sum += map[i][j].num;
                }
            }
             
            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }
}
 
class Data {
    int num;
    int max;
    int type;
    public Data(int num, int max, int type) {
        super();
        this.num = num;
        this.max = max;
        this.type = type;
    }
}