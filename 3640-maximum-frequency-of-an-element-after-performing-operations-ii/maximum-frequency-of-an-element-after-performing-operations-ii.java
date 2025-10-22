import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Map<Integer, Integer> cnt = new HashMap<>();
        Map<Integer, Integer> line = new TreeMap<>();

        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
            
            int start = num - k;
            int end = num + k;

            line.put(start, line.getOrDefault(start, 0) + 1);
            line.put(end + 1, line.getOrDefault(end + 1, 0) - 1);
            line.put(num, line.getOrDefault(num, 0)); 
        }

        int maxFreq = 0;
        int potential = 0;

        for (Map.Entry<Integer, Integer> entry : line.entrySet()) {
            int t = entry.getKey();
            potential += entry.getValue();
            
            int current = cnt.getOrDefault(t, 0);

            maxFreq = Math.max(maxFreq, Math.min(potential, current + numOperations));
        }

        return maxFreq;
    }
}