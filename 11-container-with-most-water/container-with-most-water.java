class Solution {
    public int maxArea(int[] height) {
        int result = 0;

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            result = Math.max(result, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}

// ## Brute Force ##

        // int result = 0;

        // for (int i = 0; i <= height.length - 1; i++) {
        //     for (int j = i + 1; j <= height.length - 1; j++) {
        //         int area = (j - i) * Math.min(height[i], height[j]);
        //         result = Math.max(result,area);
        //     }
        // }
        // return result;