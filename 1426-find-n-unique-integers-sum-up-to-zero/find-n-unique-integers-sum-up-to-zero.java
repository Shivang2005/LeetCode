class Solution {
    public int[] sumZero(int n) {

        int[] res = new int[n];
        if (n == 1){
            res[0] = 0;
            return res;
        }

        int index = 0;
        int half = n / 2;
        for (int i = -half; i <= -1; i++) {
            res[index] = i;
            index++;
        }
        if (n % 2 != 0) {
            res[index] = 0;
            index++;
        }
        for (int i = 1; i <= half; i++) {
            res[index] = i;
            index++;
        }
        return res;
    }
}