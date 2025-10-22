import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        Arrays.sort(nums);

        Set<Long> candidates = new HashSet<>();
        for (int num : nums) {
            candidates.add((long) num);
            candidates.add((long) num - k);
            candidates.add((long) num + k);
        }

        int maxFreq = 0;

        for (long t : candidates) {
            long tMinusK = t - k;
            long tPlusK = t + k;

            int potential = upper_bound(nums, tPlusK) - lower_bound(nums, tMinusK);
            
            int current = 0;
            if (t >= Integer.MIN_VALUE && t <= Integer.MAX_VALUE) {
                current = upper_bound(nums, (int) t) - lower_bound(nums, (int) t);
            }

            int achievableFreq = Math.min(potential, current + numOperations);
            maxFreq = Math.max(maxFreq, achievableFreq);
        }

        return maxFreq;
    }

    private int lower_bound(int[] a, long key) {
        int low = 0;
        int high = a.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int upper_bound(int[] a, long key) {
        int low = 0;
        int high = a.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= key) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}