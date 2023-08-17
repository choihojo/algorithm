import java.io.*;
import java.util.Arrays;


public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] srr;
        String str;
        String[][] map;
        String[] move;
        StringBuilder sb = new StringBuilder();
        
//        R U L D
        int[] dRow = new int[] {0, -1, 0, 1};
        int[] dCol = new int[] {1, 0, -1, 0};
        
        for (int t = 1; t <= T; t++) {
//        	현재 위치 변수
            int row = 0;
            int col = 0;
//          방향 이동할 때 임시로 쓸 변수
            int aRow = 0;
            int aCol = 0;
            srr = br.readLine().split(" ");
            int H = Integer.parseInt(srr[0]);
            int W = Integer.parseInt(srr[1]);
//          전체 맵 형태를 받기 위한 변수 map
            map = new String[H][W];
            for (int h = 0; h < H; h++) {
                str = br.readLine();
                for (int w = 0; w < W; w++) {
                    map[h][w] = String.valueOf(str.charAt(w));
//                  현재 위치 찾기
                    if (map[h][w].equals("^") || map[h][w].equals("v") || map[h][w].equals("<") || map[h][w].equals(">")) {
                    	row = h;
                        col = w;
                    }
                }
            }

            int N = Integer.parseInt(br.readLine());
//          이동 명령을 받기 위한 배열 move
            move = new String[N];
            str = br.readLine();
            for (int n = 0; n < N; n++) {
                move[n] = String.valueOf(str.charAt(n));
            }
            
//          move의 크기만큼 반복문 수행
            for (int n = 0; n < N; n++) {
//            	오른쪽일 시
                if (move[n].equals("R")) {
//                	방향 먼저 변경
                    map[row][col] = ">";
                    aRow = row + dRow[0];
                    aCol = col + dCol[0];
//                  범위 초과하지 않으면 아래 조건문 진입
                    if (aRow >= 0 && aRow < H && aCol >= 0 && aCol < W) {
//                      평지일 경우 이동
                    	if (map[aRow][aCol].equals(".")) {
                            map[aRow][aCol] = ">";
                            map[row][col] = ".";
                            row = aRow;
                            col = aCol;
                        }
                    }
                }
                else if (move[n].equals("U")) {
                    map[row][col] = "^";
                    aRow = row + dRow[1];
                    aCol = col + dCol[1];
                    if (aRow >= 0 && aRow < H && aCol >= 0 && aCol < W) {
                        if (map[aRow][aCol].equals(".")) {
                            map[aRow][aCol] = "^";
                            map[row][col] = ".";
                            row = aRow;
                            col = aCol;
                        }
                    }
                }
                else if (move[n].equals("L")) {
                    map[row][col] = "<";
                    aRow = row + dRow[2];
                    aCol = col + dCol[2];
                    if (aRow >= 0 && aRow < H && aCol >= 0 && aCol < W) {
                        if (map[aRow][aCol].equals(".")) {
                            map[aRow][aCol] = "<";
                            map[row][col] = ".";
                            row = aRow;
                            col = aCol;
                        }
                    }
                }
                else if (move[n].equals("D")) {
                    map[row][col] = "v";
                    aRow = row + dRow[3];
                    aCol = col + dCol[3];
                    if (aRow >= 0 && aRow < H && aCol >= 0 && aCol < W) {
                        if (map[aRow][aCol].equals(".")) {
                            map[aRow][aCol] = "v";
                            map[row][col] = ".";
                            row = aRow;
                            col = aCol;
                        }
                    }
                }
                else if (move[n].equals("S")) {
                    aRow = row;
                    aCol = col;
//                  대포가 범위를 벗어날 때까지 지속
                	while (aRow >= 0 && aRow < H && aCol >= 0 && aCol < W) {
//                		벽돌로 된 벽일 경우 부수고 break
                		if (map[aRow][aCol].equals("*")) {
                			map[aRow][aCol] = ".";
                			break;
                		}
//                		강철로 된 벽일 경우 그냥 break
                		else if (map[aRow][aCol].equals("#")) {
                			break;
                		}
                		
//                		범위 벗어날 때까지 현재 바라보는 방향으로 계속 전진
                		if (map[row][col].equals(">")) {
                			aRow += dRow[0];
                			aCol += dCol[0];
                		}
                		else if (map[row][col].equals("^")) {
                			aRow += dRow[1];
                			aCol += dCol[1];
                		}
                		else if (map[row][col].equals("<")) {
                			aRow += dRow[2];
                			aCol += dCol[2];
                		}
                		else if (map[row][col].equals("v")) {
                			aRow += dRow[3];
                			aCol += dCol[3];
                		}
                	}
                }
                
            }
            sb.append("#").append(t).append(" ");
            for (int h = 0; h < H; h++) {
            	for (int w = 0; w < W; w++) {
            		sb.append(map[h][w]);
            	}
            	sb.append("\n");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}