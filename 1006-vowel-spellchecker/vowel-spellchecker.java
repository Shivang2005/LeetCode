import java.util.*;

class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exact = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelInsensitive = new HashMap<>();
        
        for (String w : wordlist) {
            String lower = w.toLowerCase();
            caseInsensitive.putIfAbsent(lower, w);
            vowelInsensitive.putIfAbsent(devowel(lower), w);
        }
        
        String[] res = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (exact.contains(q)) {
                res[i] = q;
                continue;
            }
            String lower = q.toLowerCase();
            if (caseInsensitive.containsKey(lower)) {
                res[i] = caseInsensitive.get(lower);
                continue;
            }
            String vow = devowel(lower);
            res[i] = vowelInsensitive.getOrDefault(vow, "");
        }
        return res;
    }

    private String devowel(String word) {
        char[] arr = word.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                arr[i] = '*';
            }
        }
        return new String(arr);
    }
}
