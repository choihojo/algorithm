import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[][] srr = new String[5][15];
    	for (int i = 0; i < 5; i++) {
    		String word = br.readLine();
    		int len = word.length();
    		for (int j = 0; j < len; j++) {
    			srr[i][j] = String.valueOf(word.charAt(j));
    		}
    		for (int j = len; j < 15; j++) {
    			srr[i][j] = "";
    		}
    	}
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < 15; i++) {
    		for (int j = 0; j < 5; j++) {
    			sb.append(srr[j][i]);
    		}
    	}
    	
    	System.out.println(sb.toString());
    }
}