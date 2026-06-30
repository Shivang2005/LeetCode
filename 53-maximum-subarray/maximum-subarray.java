class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            //comparing and updating the current sum, 
            // if currentSum is negative, reset to current element
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // updating maxSum if currentSum is greater
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}