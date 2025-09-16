class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        ArrayList<Integer> stack = new ArrayList<>();
        for (int num : nums) {
            stack.add(num);
            while (stack.size() >= 2) {
                int b = stack.get(stack.size() - 1);
                int a = stack.get(stack.size() - 2);
                int g = gcd(a, b);
                if (g == 1) break;
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.add((int) ((long) a / g * b));
            }
        }
        return stack;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
