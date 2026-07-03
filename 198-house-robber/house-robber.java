class Solution {
    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length];

        // Best money till house 0
        dp[0] = nums[0];

        // Best money till house 1
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {

            // Rob current house
            int rob = nums[i] + dp[i - 2];

            // Skip current house
            int skip = dp[i - 1];

            // Take the better option
            dp[i] = Math.max(rob, skip);
        }

        return dp[nums.length - 1];
    }
}