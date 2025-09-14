class Solution {
    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> words = new HashSet<>(Arrays.asList(wordlist));
        Map<String, String> cap = new HashMap<>();
        Map<String, String> vowel = new HashMap<>();
        for (String w : wordlist) {
            cap.putIfAbsent(w.toLowerCase(), w);
            vowel.putIfAbsent(devowel(w.toLowerCase()), w);
        }
        String[] res = new String[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (words.contains(q)) {
                res[i] = q;
            } else {
                String low = q.toLowerCase();
                if (cap.containsKey(low)) {
                    res[i] = cap.get(low);
                } else {
                    String vow = devowel(low);
                    res[i] = vowel.getOrDefault(vow, "");
                }
            }
        }
        return res;
    }

    private String devowel(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) sb.append('*');
            else sb.append(c);
        }
        return sb.toString();
    }
}
