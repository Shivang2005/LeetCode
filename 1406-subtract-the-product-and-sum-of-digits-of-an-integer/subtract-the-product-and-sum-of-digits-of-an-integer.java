class Solution {
    public int subtractProductAndSum(int n) {
        int temp = n;

        int product = 1;
        while(temp > 0) {
            int p = temp % 10;
            product *= p;
            temp /= 10;
        }

        temp = n;
        int sum = 0;
        while (temp > 0) {
            int s = temp % 10;
            sum += s;
            temp /= 10;
        }

        int subtractProductAndSum = product - sum;
        return subtractProductAndSum;
    }
}