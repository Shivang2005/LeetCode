import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        // 1. Gather all critical points to test as targets
        Set<Long> targetCandidates = new HashSet<>();
        for (int num : nums) {
            targetCandidates.add((long) num);
            targetCandidates.add((long) num - k);
            targetCandidates.add((long) num + k);
        }

        int maxFreq = 0;

        // 2. Test each candidate target
        for (long target : targetCandidates) {
            // Count C: all numbers convertible to the target
            int C_start = lowerBound(nums, target - k);
            int C_end = upperBound(nums, target + k);
            int C = C_end - C_start;

            // Count E: all numbers already equal to the target
            int E = 0;
            // E is non-zero only if target is a valid integer and present
            if (target >= Integer.MIN_VALUE && target <= Integer.MAX_VALUE) {
                int E_start = lowerBound(nums, target);
                int E_end = upperBound(nums, target);
                // Check if the element actually exists at that position
                if (E_start < n && nums[E_start] == target) {
                     E = E_end - E_start;
                }
            }
            
            // 3. Apply the correct frequency formula
            int currentFreq = Math.min(C, E + numOperations);
            if (currentFreq > maxFreq) {
                maxFreq = currentFreq;
            }
        }

        return maxFreq;
    }

    // Finds the first index of an element >= key
    private int lowerBound(int[] nums, long key) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Finds the first index of an element > key
    private int upperBound(int[] nums, long key) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}