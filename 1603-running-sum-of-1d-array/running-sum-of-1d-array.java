class Solution {
    public int[] runningSum(int[] nums) {
        int runningSum = 0;
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // nums[i] += nums[i-1];
            runningSum += nums[i];
            ans[i] = runningSum;
        }
        return ans;
    }
}