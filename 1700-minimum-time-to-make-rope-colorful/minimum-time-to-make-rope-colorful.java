class Solution {
    public int minCost(String colors, int[] neededTime) {
        int totalTime = 0;
        int n = colors.length();
        if (n == 0) {
            return 0;
        }

        int maxCostInGroup = neededTime[0];

        for (int i = 1; i < n; i++) {
            if (colors.charAt(i) == colors.charAt(i - 1)) {
                totalTime += Math.min(maxCostInGroup, neededTime[i]);
                maxCostInGroup = Math.max(maxCostInGroup, neededTime[i]);
            } else {
                maxCostInGroup = neededTime[i];
            }
        }

        return totalTime;
    }
}