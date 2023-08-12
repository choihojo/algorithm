import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			String[] srr = br.readLine().split(" ");
			if (srr[0].equals("1")) {
				stack.push(Integer.parseInt(srr[1]));
			}
			else if (srr[0].equals("2")) {
				if (stack.isEmpty()) {
					sb.append("-1").append("\n");
				}
				else {
					sb.append(stack.pop()).append("\n");
				}
			}
			else if (srr[0].equals("3")) {
				sb.append(stack.size()).append("\n");
			}
			else if (srr[0].equals("4")) {
				if (stack.isEmpty()) {
					sb.append("1").append("\n");
				}
				else {
					sb.append("0").append("\n");
				}
			}
			else if (srr[0].equals("5")) {
				if (stack.isEmpty()) {
					sb.append("-1").append("\n");
				}
				else {
					sb.append(stack.peek()).append("\n");
				}
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}
}