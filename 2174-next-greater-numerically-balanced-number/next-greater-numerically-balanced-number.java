class Solution {
    public int nextBeautifulNumber(int n) {
        int num = n + 1;
        while (true) {
            if (isNumericallyBalanced(num)) {
                return num;
            }
            num++;
        }
    }

    private boolean isNumericallyBalanced(int n) {
        String s = String.valueOf(n);
        int[] counts = new int[10];
        for (char c : s.toCharArray()) {
            counts[c - '0']++;
        }

        for (int i = 0; i < 10; i++) {
            if (counts[i] > 0 && counts[i] != i) {
                return false;
            }
        }
        return true;
    }
}