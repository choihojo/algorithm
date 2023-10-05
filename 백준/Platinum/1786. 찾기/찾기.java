import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static String T;
    static String P;
    static int[] failTable;
    static int count;
    
    static void makeFailTable(String pattern) {
        int patternLength = pattern.length();
        failTable = new int[patternLength];  
        int j = 0;
        for (int i = 1;  i < patternLength; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = failTable[j - 1];
//              이렇게 하면 안 되는 이유 숙지
//            	j = 0;
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                failTable[i] = ++j;
            }
        }
    }
    
    static void kmp(String target, String pattern) {
        int targetLength = target.length();
        int patternLength = pattern.length();
        int j = 0;
        for (int i = 0; i < targetLength; i++) {
            while (j > 0 && target.charAt(i) != pattern.charAt(j)) {
                j = failTable[j - 1];
            }
            if (target.charAt(i) == pattern.charAt(j)) {
//            	System.out.println(i);
                if (j == patternLength - 1) {
                    sb.append(i - j + 1).append("\n");
                    count++;
                    j = failTable[j];
                } else {
                    j++;
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = br.readLine();
        P = br.readLine();
        makeFailTable(P);
        kmp(T, P);
//        System.out.println(Arrays.toString(failTable));
        System.out.println(count);
        System.out.println(sb.toString());
    }
}