class Solution {
    public int findNumbers(int[] nums) {
        int result = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            int count = 0;
            while(temp > 0) {
                temp = temp/10;
                count++;
            }
            if(count%2 == 0){
                result++;
            }
        }
        return result;
    }
}