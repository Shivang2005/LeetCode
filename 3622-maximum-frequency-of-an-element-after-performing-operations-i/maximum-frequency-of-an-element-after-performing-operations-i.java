import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int n = nums.length;
        
        Set<Long> candidateSet = new HashSet<>();
        for (int num : nums) {
            candidateSet.add((long) num);
            candidateSet.add((long) num - k);
            candidateSet.add((long) num + k);
        }

        List<Long> targets = new ArrayList<>(candidateSet);
        Collections.sort(targets);

        Map<Long, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put((long) num, freqMap.getOrDefault((long) num, 0) + 1);
        }

        int maxFreq = 0;
        int left = 0;
        int right = 0;

        for (long target : targets) {
            long lowerBound = target - k;
            long upperBound = target + k;

            while (left < n && nums[left] < lowerBound) {
                left++;
            }
            while (right < n && nums[right] <= upperBound) {
                right++;
            }

            int C = right - left;
            int E = freqMap.getOrDefault(target, 0);

            int currentFreq = Math.min(C, E + numOperations);
            if (currentFreq > maxFreq) {
                maxFreq = currentFreq;
            }
        }

        return maxFreq;
    }
}