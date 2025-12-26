import java.util.Arrays;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long totalHappiness = 0;
        int n = happiness.length;

        for (int i = 0; i < k; i++) {
            int currentHappiness = happiness[n - 1 - i] - i;
            
            if (currentHappiness > 0) {
                totalHappiness += currentHappiness;
            } else {
                break;
            }
        }

        return totalHappiness;
    }
}