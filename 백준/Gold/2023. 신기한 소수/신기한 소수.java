import java.io.*;
import java.util.*;

import javax.sql.rowset.serial.SerialBlob;

//	생각보다 어렵다
//	에라토스테네스의 체를 쓰기엔 메모리가 너무 적다
//	순열을 돌리듯이 한 자리를 고르고 다음 자리로 넘기고 매 순간 소수 검사를 해서 가지치기를 해야될 듯
public class Main {
	static int N;
//	0으로 끝나면 반드시 약수가 존재하므로 0은 고려하지 않음
	static int[] input = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
	static int[] output;
	static String tempStr;
	static int tempInt;
	static StringBuilder sb = new StringBuilder();
			
	static void spPrime(int cnt) {
//		만약 cnt = 0, 1, ..., N - 1까지 총 N자리를 뽑고 cnt == N이 되면 스트링빌더에 추가하고 리턴
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				sb.append(output[i]);
			}
			sb.append("\n");
			return;
		}
//		input의 길이만큼 반복문 돎(cnt번째 자리에 input[i]를 넣어줌)
		for (int i = 0; i < 9; i++) {
			output[cnt] = input[i];
//			output에 구성된 숫자가 소수인지 판별하고 소수면 다음 자리를 뽑고 아니면 가지치기
			if (isPrime(cnt)) {
				spPrime(cnt + 1);
			}
		}
	}
	
//	cnt를 가지고 소수를 판별함
//	예를 들어 cnt가 2일 경우 output[0], output[1], output[2]를 가지고 만든 3자리 숫자에 대해 소수판별하는 거임
//	그 이전에 output[0]으로 만든 1자리와 output[0], output[1]로 만든 2자리는 이미 검사한 것이므로 다시 검사할 필요가 없음
	static boolean isPrime(int cnt) {
		tempStr = "";
		for (int i = 0; i <= cnt; i++) {
			tempStr += String.valueOf(output[i]);
		}
		tempInt = Integer.valueOf(tempStr);
//		tempInt가 1일 때는 아래 소수 검사로 판별하지 못하므로 따로 처리해줌
		if (tempInt == 1) return false;
//		나머지 숫자에 대해서는 아래 식으로 모두 판별가능
//		tempInt - 1까지 할 필요 없이 sqrt(tempInt)까지만 하면 됨
		for (int i = 2; i * i <= tempInt; i++) {
			if (tempInt % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		output = new int[N];
		spPrime(0);
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb);
	}
}