class Solution {
    public int minOperations(int[] nums) {
        java.util.Deque<Integer> st = new java.util.ArrayDeque<>();
        int ans = 0;
        for (int x : nums) {
            if (x == 0) {
                st.clear();
                continue;
            }
            while (!st.isEmpty() && st.peekLast() > x) st.pollLast();
            if (st.isEmpty() || st.peekLast() < x) {
                st.addLast(x);
                ans++;
            }
        }
        return ans;
    }
}
