import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSubarray(int[] nums, int p) {
        long sum = 0;
        for (int num : nums) {
            sum = (sum + num) % p;
        }
        int rem = (int) sum;
        if (rem == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        long currentSum = 0;
        int minLen = nums.length;

        for (int i = 0; i < nums.length; i++) {
            currentSum = (currentSum + nums[i]) % p;
            int target = (int) ((currentSum - rem + p) % p);
            if (map.containsKey(target)) {
                minLen = Math.min(minLen, i - map.get(target));
            }
            map.put((int) currentSum, i);
        }

        return minLen == nums.length ? -1 : minLen;
    }
}