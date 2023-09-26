import java.io.*;
import java.util.*;

// Longest Increasing Subsequence (LIS) Problem
// 1. 부분집합을 이용한 Brute-Force 방법 (생략) : O(2^N)
// 2. DP를 활용한 방법 : O(N^2)
// 3. DP + 이진 탐색을 활용한 방법 : O(N*logN)

public class Main {
    static int N;
    static int[] sequence;
    static int[] dp;
    static int binarySearch(int start, int end, int target) {
        int mid = (start + end) / 2;
        if (end < start) {
            return start;
        }
        else if (target < dp[mid]) return binarySearch(start, mid - 1, target); 
        else return binarySearch(mid + 1, end, target);
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sequence = new int[N];
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
//          sequence[i] = Integer.parseInt(st.nextToken());
//          dp[i] = 1;
      }

//      2. DP를 활용한 방법 : O(N^2)
//      for (int i = 0; i < N; i++) {
//          for (int j = 0; j < i; j++) {
//              if (sequence[i] > sequence[j] && dp[i] < dp[j] + 1) dp[i] = dp[j] + 1;
//          }
//      }
//      int max = 0;
//      for (int i = 0; i < N; i++) {
//          if (dp[i] > max) {
//              max = dp[i];
//          }
//      }
//      System.out.println(max);

//      3. DP + 이진 탐색을 활용한 방법 : O(N*logN)
      dp[0] = Integer.MIN_VALUE;
      int last = 0;
      for (int i = 0; i < N; i++) {
          int x = Integer.parseInt(st.nextToken());
          int index = binarySearch(0, last, x);
          if (index > last) {
        	  if (x > dp[last]) dp[++last] = x;
          }
          else {
        	  if (x < dp[index] && x > dp[index - 1]) dp[index] = x;
          }
      }
      System.out.println(last);
  }
}