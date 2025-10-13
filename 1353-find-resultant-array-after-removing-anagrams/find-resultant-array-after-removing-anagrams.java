import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        String prevSorted = "";
        
        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String currentSorted = new String(chars);
            
            if (!currentSorted.equals(prevSorted)) {
                result.add(word);
                prevSorted = currentSorted;
            }
        }
        
        return result;
    }
}