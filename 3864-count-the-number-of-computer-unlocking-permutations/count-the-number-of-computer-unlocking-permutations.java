class Solution {
    public int countPermutations(int[] complexity) {
        long mod = 1_000_000_007;
        long ans = 1;
        int n = complexity.length;

        for (int i = 1; i < n; i++) {
            if (complexity[i] <= complexity[0]) {
                return 0;
            }
            ans = (ans * i) % mod;
        }

        return (int) ans;
    }
}