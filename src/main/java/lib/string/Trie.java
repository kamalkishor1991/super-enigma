package lib.string;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    public void insert(Map<Character, HashMap> map, String s, int index) {
        if (index >= s.length()) return;
        else {
            Map<Character, HashMap> cm = map.get(s.charAt(index));
            if (cm == null) {
                cm = new HashMap<>();
                map.put(s.charAt(index), (HashMap) cm);
            }
            insert(cm, s, index + 1);
        }
    }

}
