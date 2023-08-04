import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 65; i <= 90; i++) {
        	map.put(String.valueOf((char) i), 0);
        }
        
        String word = br.readLine();
        word = word.toUpperCase();
        String[] spells = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
        	spells[i] = String.valueOf(word.charAt(i));
        }
        
        for (int i = 0; i < spells.length; i++) {
        	map.put(spells[i], map.get(spells[i]) + 1);
        }
        
        int max = -1;
        String maxKey = "";
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        	if (entry.getValue() > max) {
        		max = entry.getValue();
        		maxKey = entry.getKey();
        	}
        }
        
        int cnt = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        	if (max == entry.getValue()) {
        		cnt++;
        	}
        }
        
        if (cnt == 1) {
        	System.out.println(maxKey);
        }
        else {
        	System.out.println("?");
        }
    }
}