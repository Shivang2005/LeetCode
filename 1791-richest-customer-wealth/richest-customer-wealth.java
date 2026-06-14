class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        int sum;

        for (int row = 0; row < accounts.length; row++) {
            sum = 0;
            
            for (int col = 0; col < accounts[row].length; col++){
                sum += accounts[row][col];
            }
            if (sum > max){
            max = sum;
            }
        }
        return max;
    }
}