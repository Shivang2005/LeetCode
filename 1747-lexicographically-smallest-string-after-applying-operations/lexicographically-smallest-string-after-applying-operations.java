import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        String smallest = s;

        queue.offer(s);
        visited.add(s);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.compareTo(smallest) < 0) {
                smallest = current;
            }

            char[] currentChars = current.toCharArray();
            for (int i = 1; i < currentChars.length; i += 2) {
                int digit = currentChars[i] - '0';
                digit = (digit + a) % 10;
                currentChars[i] = (char) ('0' + digit);
            }
            String afterAdd = new String(currentChars);

            if (visited.add(afterAdd)) {
                queue.offer(afterAdd);
            }

            int n = s.length();
            String afterRotate = current.substring(n - b) + current.substring(0, n - b);
            
            if (visited.add(afterRotate)) {
                queue.offer(afterRotate);
            }
        }

        return smallest;
    }
}