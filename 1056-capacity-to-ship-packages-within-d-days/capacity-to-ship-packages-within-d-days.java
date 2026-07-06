class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for (int weight : weights) {
            left = Math.max(left, weight);
        right += weight;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isPossible(weights, days, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isPossible(int[] weights, int days, int capacity) {
        int daysUsed = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                daysUsed++;
                currentLoad = weight;
            }
            else {
                currentLoad += weight;
            }
        }
        return daysUsed <= days;
    }
}
