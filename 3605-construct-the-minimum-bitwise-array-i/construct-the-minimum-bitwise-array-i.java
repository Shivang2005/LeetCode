class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            if (target == 2) {
                ans[i] = -1;
            } else {
                for (int b = 0; b < 31; b++) {
                    if (((target >> b) & 1) == 0) {
                        ans[i] = target ^ (1 << (b - 1));
                        break;
                    }
                }
            }
        }
        
        return ans;
    }
}