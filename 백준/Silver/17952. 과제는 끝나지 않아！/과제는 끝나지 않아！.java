import java.io.*;
import java.util.*;


// 1분기 기간동안 분단위로 업무가 추가됨 (분기는 총 N분)
// 1분째, 2분째, ..., N분째까지 나옴 (받자마자 하는 거라 사실상 0분째부터임)
// 업무는 가장 최근에 주어지는 순서대로 함
// 하던 중에 새로운 업무 추가되면 중단하고 새로운 업무 진행
// 끝났으면 이전에 하던 업무를 이어서 함
// N분째 A점 걸리는시간T
public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] srr;
//		입력받을 때 1과 0 여부는 어차피 뒤에 분으로 구별돼서 안 받아도 됨
//		그래서 1분기에 받는 업무를 저장할 때는 [N][2]의 크기로 받음
//		0열은 몇 점인지 저장되고 1열은 몇 분이 남았는지를 저장함
		int[][] data = new int[N][2];
		for (int i = 0; i < N; i++) {
			srr = br.readLine().split(" ");
//			나눴을 때 입력이 3개가 아니면 굳이 받을 필요 없음 (어차피 0으로 초기화됨)
			if (srr.length == 3) {
//				받은 업무의 점수를 저장함
				data[i][0] = Integer.parseInt(srr[1]);
//				해당 업무가 끝나려면 몇 분이 남았는지 저장함
//				받자마자 바로 시작하므로 받는 순간 1분을 줄여서 저장함
//				이렇게 해야 받자마자 바로 끝내는 경우를 고려하기 편함
				data[i][1] = (Integer.parseInt(srr[2]) - 1);
			}
		}
		
//		업무를 하다가도 최근에 새로운 업무가 주어지면 바로 그 업무를 시작함
//		결국 뒤에서부터 업무가 나온 경우와 나오지 않은 경우를 세면 됨
//		만약 0열이 0이었다면 업무가 나오지 않은 것이므로 그 이전에 나온 업무를 처리할 수 있었을 거임
//		그래서 뒤에서부터 0의 개수를 세면서, 만약 0이 아니었다면 해당 업무 시간에 0의 개수만큼 빼주고 다시 0을 세가면서 거슬러올라감
		int cnt = 0;
		for (int i = (N - 1); i >= 0; i--) {
//			만약 0열이 0이었다면 업무 처리할 시간이 늘어난 것임 -> cnt++
			if (data[i][0] == 0) {
				cnt++;
			}
			else {
//				만약 1열이 0이 아니었다면 뒤에서 얻은 업무 처리시간을 바탕으로 업무 수행
				if (data[i][1] != 0) {
//					만약 1열이 cnt보다 컸다면 1열에다 cnt를 빼주고 cnt를 0으로 만듦
					if (data[i][1] >= cnt) {
						data[i][1] -= cnt;
						cnt = 0;
					}
//					cnt가 더 컸다면 cnt에다 1열을 빼주고 남는 cnt는 가지고 거슬러올라감
					else {
						cnt -= data[i][1];
						data[i][1] = 0;
					}
				}
			}
		}
		
//		업무점수의 합을 저장할 변수
		int sum = 0;
		for (int i = 0; i < N; i++) {
//			1열이 0이었다면 0열 성분(점수)을 더해줌
//			업무가 들어오지 않은 경우에는 어차피 0이라 같이 더해도 결과에는 지장 없음
			if (data[i][1] == 0) {
				sum += data[i][0];
			}
		}
		
		System.out.println(sum);
	}
}
