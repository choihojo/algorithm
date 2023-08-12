import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] checked = new boolean[N];
		int temp = 0;
		StringBuilder sb = new StringBuilder();
		
//		여기에 굳이 이전 코드처럼 result 말고 N으로 해도 되는 이유 생각해볼 것
		if (N != 1) {
			for (int i = 1; i < N; i++) {
//				(N / 2)부터 (N - 1)까지는 어차피 약수가 아니라서 확인할 필요가 없음
//				마지막 자기자신으로 나누는 경우만 고려하면 되므로 아래 조건문 추가
				if (i >= (N / 2) &&  i < (N - 1)) {
					continue;
				}
				
				if (!checked[i]) {
					temp = i + 1;
					while (N % temp == 0) {
						sb.append(temp).append("\n");
						N /= temp;
					}
//					체크한 숫자의 배수 숫자들은 모두 체크했다고 간주함
//					이 체크는 temp가 N의 약수든 아니든 관계 없음
//					N이 100이고 temp가 7일 때 7의 배수는 모두 체크했다고 간주하는 것임
//					위의 continue 덕분에 절반까지만 체크해놓으면 됨
					while (temp <= (N / 2)) {
						checked[temp - 1] = true;
						temp += (i + 1);
					}
				}
				if (N == 1) {
//					마지막 개행문자 제거
					sb.deleteCharAt(sb.length() - 1);
					System.out.println(sb.toString());
				}
			}
		}
	}
}