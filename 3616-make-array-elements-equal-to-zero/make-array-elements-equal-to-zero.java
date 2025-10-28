class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        long totalSum = prefixSum[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                long leftSum = prefixSum[i];
                long rightSum = totalSum - leftSum;

                if (leftSum == rightSum) {
                    count += 2;
                } else if (leftSum == rightSum + 1) {
                    count += 1;
                } else if (rightSum == leftSum + 1) {
                    count += 1;
                }
            }
        }
        return count;
    }
}