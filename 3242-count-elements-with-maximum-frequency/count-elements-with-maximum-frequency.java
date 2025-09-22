import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;

        for (int num : nums) {
            int currentFreq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, currentFreq);
            if (currentFreq > maxFreq) {
                maxFreq = currentFreq;
            }
        }

        int totalCount = 0;
        for (int freq : freqMap.values()) {
            if (freq == maxFreq) {
                totalCount += maxFreq;
            }
        }

        return totalCount;
    }
}