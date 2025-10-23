class Solution {
    public boolean hasSameDigits(String s) {
        while (s.length() > 2) {
            StringBuilder next_s = new StringBuilder();
            for (int i = 0; i < s.length() - 1; i++) {
                int d1 = s.charAt(i) - '0';
                int d2 = s.charAt(i + 1) - '0';
                int newDigit = (d1 + d2) % 10;
                next_s.append(newDigit);
            }
            s = next_s.toString();
        }
        return s.charAt(0) == s.charAt(1);
    }
}