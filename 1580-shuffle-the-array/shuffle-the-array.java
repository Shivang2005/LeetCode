class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[n*2];

        // int f = 0;
        // int s = 1;

        for (int i = 0; i < n; i++) {
            ans[2*i] = nums[i];
            ans[2*i+1] = nums[i+n];

            // f+=2;
            // s+=2;
        }
        return ans;
    }
}