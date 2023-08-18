import java.io.*;
import java.util.*;
 
public class Solution {
//	3차원 배열을 사용하지 않고 리스트를 이용해서만 풀어봄
//	구체적인 설명은 이전 풀이에 있으니 참고할 것
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String[] srr;
//      행동 선택지 : 0(이동하지 않음), 1(상), 2(우), 3(하), 4(좌) 순으로 행배열과 열배열을 만들어줌
        int[] dR = new int[] {0, -1, 0, 1, 0};
        int[] dC = new int[] {0, 0, 1, 0, -1};
//      출력에 사용할 스트링빌더 선언
        StringBuilder sb = new StringBuilder();
         
//      테스트케이스 진입
        for (int t = 1; t <= T; t++) {
            srr = br.readLine().split(" ");
//          총 충전량 charge
            int charge = 0;
//          A 시작점에 대한 행과 열
//          전체 맵 범위는 10*10인데 인덱싱 편하게 하기 위해 11*11로 선언할까 하다가 충전 범위 때문에 오히려 꼬일 수도 있을 것 같아 0부터 시작하게 함
            int aR = 0;
            int aC = 0;
//          B 시작점에 대한 행과 열
            int bR = 9;
            int bC = 9;
//          총 이동 시간 M
            int M = Integer.parseInt(srr[0]);
//          BC의 개수 A
            int A = Integer.parseInt(srr[1]);
             
//          A 이동 경로 저장
//          초기 위치(0초)부터 충전 가능하므로 M보다 1 크게 배열 설정
//          t = 20까지 있을 경우 t = 0, 1, 2, ..., 20까지 21가지 행동을 한 것임
            int[] moveA = new int[M + 1];
            srr = br.readLine().split(" ");
//          moveA[0]은 map[aR][aC]에서 0(이동하지 않음)의 행동을 한 것임 (t = 0)
//          따라서 map[1]부터 입력값을 넣어줌
            for (int i = 0; i < M; i++) {
                moveA[i + 1] = Integer.parseInt(srr[i]);
            }   
//          B 이동 경로 저장
            int[] moveB = new int[M + 1];
            srr = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                moveB[i + 1] = Integer.parseInt(srr[i]);
            }
             
//          BC 저장할 배열 선언
            int[][] spot = new int[A][4];
            for (int i = 0; i < A; i++) {
                srr = br.readLine().split(" ");
                for (int j = 0; j < 4; j++) {
                    spot[i][j] = Integer.parseInt(srr[j]);
                }
            }
            
//          위에서 입력받은 BC 배열을 3번째 성분(충전량)에 따라 객체(spot[0], spot[1], ...)를 내림차순으로 정렬
            Arrays.sort(spot, (s1, s2) -> (s2[3] - s1[3]));
             
//          A가 이동하면서 어떤 충전기 영역을 지나왔는지 기록하기 위한 리스트 배열 (그냥 리스트가 아니라 리스트의 배열임)
//          당연히 크기는 t = 0, 1, 2, ..., M까지므로 M + 1이 됨
            List<Integer>[] listA = new ArrayList[M + 1];
            for (int i = 0; i < M + 1; i++) {
                listA[i] = new ArrayList<>();
            }
//          B가 이동하면서 어떤 충전기 영역을 지나왔는지 기록하기 위한 리스트 배열
            List<Integer>[] listB = new ArrayList[M + 1];
            for (int i = 0; i < M + 1; i++) {
                listB[i] = new ArrayList<>();
            }
             
//          t = 0, 1, 2, ..., M까지 따라가면서 충전량 기록
            for (int i = 0; i < M + 1; i++) {
                 
//              A와 B가 같은 충전기 영역을 지났는지 체크하기 위한 변수
                boolean inter = false;
//              t = i일 때 한 행동에 따라 A의 위치 갱신
                aR += dR[moveA[i]];
                aC += dC[moveA[i]];
//              B의 위치 갱신
                bR += dR[moveB[i]];
                bC += dC[moveB[i]];
                 
//              A의 현재 위치가 어떤 충전기의 범위에 들어가는지 모두 범위에 추가
//              예를 들어 A 기준으로 t = i일 때 0번째, 2번째 충전기의 영역에 들어가있었다면 i번째 리스트에 충전기의 인덱스인 0, 2를 추가하는 거임
                for (int j = 0; j < A; j++) {
                    if ((Math.abs(spot[j][0] - 1 - aC) + Math.abs(spot[j][1] - 1 - aR)) <= spot[j][2]) {
                        listA[i].add(j);
                    }
                    if ((Math.abs(spot[j][0] - 1 - bC) + Math.abs(spot[j][1] - 1 - bR)) <= spot[j][2]) {
                        listB[i].add(j);
                    }
                }
                 
//              만약 교집합이 있었다면 inter를 참으로 변경
//              이 확인이 반드시 필요한 이유가 A와 B가 서로 다른 충전기의 영역에 있는데 그 두 충전기의 충전량이 같은 경우에는 반반씩 나눠갖는 게 아니라 둘 다 충전량의 전부를 가져가는 것이 맞기 때문임
//              단순히 충전값을 비교해서만은 위 경우를 고려할 수 없음
                for (int e : listA[i]) {
                    if (listB[i].contains(e)) {
                        inter = true;
                    }
                }
                 
//              만약 교집합이 없었다면 각각 현재 위치에서 최댓값(정렬했으므로 각 리스트에서 get한 0번째 원소를 인덱스로 하는 spot의 충전량)을 charge에 더해주면 됨
                if (!inter) {
                	if (listA[i].size() != 0) {
                        charge += spot[listA[i].get(0)][3];
                	}
                	if (listB[i].size() != 0) {
                        charge += spot[listB[i].get(0)][3];
                	}
                }
                else {
//                  만약 교집합이 있고 둘 다 크기가 1이라면 대체제가 없으므로 서로 반반씩 나눠가짐
//                  의미적으로 명확히 하려고 굳이 똑같은 값을 2로 나눠서 2번 더해줌
                    if (listA[i].size() == 1 && listB[i].size() == 1) {
                        charge += spot[listA[i].get(0)][3] / 2;
                        charge += spot[listB[i].get(0)][3] / 2;
                    }
                    else {
//                    	A나 B 중 최소 한 명은 속해있는 충전기가 2개 이상일 경우
//                    	A의 최대 충전량의 충전기와 B의 최대 충전량의 충전기가 겹치는지 확인해야함
                        if (listA[i].get(0) == listB[i].get(0)) {
//                          0번째가 겹치는데 A의 크기가 2 이상이고 B의 크기가 1일 경우 반드시 B는 교집합을 최댓값으로 가져가고 A는 2번째(1번) 충전량을 가져가는 게 최선임
//                          굳이 크기가 1인 경우를 하나하나 꼽아준 이유는 인덱스 초과 예외 방지를 위해서임 (논리 자체는 아래 3가지 모두 비슷함)
//                        	예를 들어 만약 listA의 크기가 1인데 get(1)을 하면 인덱스 초과 예외가 발생함
                        	if (listA[i].size() > 1 && listB[i].size() == 1) {
                        		charge += spot[listA[i].get(1)][3];
                        		charge += spot[listB[i].get(0)][3];
                        	}
//                          0번째가 겹치는데 A의 크기가 1이고 B의 크기가 2 이상일 경우 반드시 A는 교집합을 최댓값으로 가져가고 B는 2번째 충전량을 가져가는 게 최선임
                            else if (listA[i].size() == 1 && listB[i].size() > 1) {
                        		charge += spot[listA[i].get(0)][3];
                        		charge += spot[listB[i].get(1)][3];
                        	}
                            else {
//                            	0번째가 겹치는 경우 둘 중 한 명은 양보해야 함 (둘 다 크기가 2 이상이므로 경우가 2가지 나옴)
//                              두 번째 충전량을 가져갈 사람이 A가 좋을지 B가 좋을지는 일단 해보고 비교해야함
//                              따라서 두 경우를 저장할 임시 변수를 만들어주고 최댓값을 채택했음
                                int tempA = spot[listA[i].get(0)][3] + spot[listB[i].get(1)][3];
                                int tempB = spot[listA[i].get(1)][3] + spot[listB[i].get(0)][3];
                                charge += Math.max(tempA, tempB);
                            }
                        }
//                      만약 교집합이 있긴 있지만 그것이 0번째 원소가 아닐 경우(최소 둘 중 하나는 크기가 2 이상이라는 뜻임) 그냥 0번째를 둘 다 더해주면 됨
//                      예를 들어 A가 충전량 100의 충전기 0과 충전량 70의 충전기 1에 속해있고 B가 충전량 70의 충전기 2에 속해있을 때 그냥 최댓값을 더해주면 되는 것임
//                      추가적으로 A가 속해있는 충전기 0의 충전량이 70, 1의 충전량도 70인데 B가 충전기 1에 속해있을 때도 자동으로 고려됨
                        else {
                            charge += spot[listA[i].get(0)][3];
                            charge += spot[listB[i].get(0)][3];
                        }
                    }
                }
            }
            
//          출력 형식 맞춤
            sb.append("#").append(t).append(" ").append(charge).append("\n");

        }
//      마지막 개행문자 삭제
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}