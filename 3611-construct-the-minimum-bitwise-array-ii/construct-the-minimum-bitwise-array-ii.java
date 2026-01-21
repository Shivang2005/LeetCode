class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int target = nums.get(i);
            if (target == 2) {
                ans[i] = -1;
            } else {
                int temp = target;
                int count = 0;
                while ((temp & 1) == 1) {
                    temp >>= 1;
                    count++;
                }
                ans[i] = target ^ (1 << (count - 1));
            }
        }
        
        return ans;
    }
}