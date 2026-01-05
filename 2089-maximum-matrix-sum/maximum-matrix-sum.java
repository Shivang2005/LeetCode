class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int minAbsValue = Integer.MAX_VALUE;
        int negativeCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j];
                int absVal = Math.abs(val);
                
                if (val < 0) {
                    negativeCount++;
                }
                
                totalSum += absVal;
                minAbsValue = Math.min(minAbsValue, absVal);
            }
        }

        if (negativeCount % 2 != 0) {
            totalSum -= 2L * minAbsValue;
        }

        return totalSum;
    }
}