import java.util.*;

class Solution {
    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        for (int num : nums) {
            stack.add(num);
            while (stack.size() >= 2) {
                int a = stack.get(stack.size() - 2);
                int b = stack.get(stack.size() - 1);
                int gcd = gcd(a, b);
                if (gcd == 1) break;
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);
                stack.add(lcm(a, b));
            }
        }
        return stack;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}
