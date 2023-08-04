
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int cnt = 0;
    	
    	for (int n = 0; n < N; n++) {
    		String srr = br.readLine();
    		List<String> list = new ArrayList<String>();
    		int start = 0;
    		while (start < srr.length()) {
    			if (start == srr.length() - 1) {
    				list.add(String.valueOf(srr.charAt(start)));
    				break;
    			}
    			if (srr.charAt(start) == srr.charAt(start + 1)) {
    				start++;
    			}
    			else {
    				list.add(String.valueOf(srr.charAt(start)));
    				start++;
    			}
    		}
    		Set<String> set = new HashSet<String>();
    		for (int i = 0; i < list.size(); i++) {
    			set.add(list.get(i));
    		}
    		if (list.size() == set.size()) {
    			cnt++;
    		}
    	}
    	
    	System.out.println(cnt);
    	
    	
    }
}