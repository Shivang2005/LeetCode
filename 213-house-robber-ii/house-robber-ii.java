class Solution {
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int case1 = robbing(nums, 0, nums.length - 2);
        int case2 = robbing(nums, 1, nums.length - 1);

        return Math.max(case1, case2);


    }
    public int robbing(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {

            int rob = nums[i] + prev2;
            int skip = prev1;

            int curr = Math.max(rob, skip);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}