import java.util.*;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int num : nums) {
            stack.add(num);
            while (stack.size() > 1) {
                int b = stack.removeLast();
                int a = stack.removeLast();
                int g = gcd(a, b);
                if (g == 1) {
                    stack.add(a);
                    stack.add(b);
                    break;
                }
                long lcm = (long) a / g * b;
                stack.add((int) lcm);
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
