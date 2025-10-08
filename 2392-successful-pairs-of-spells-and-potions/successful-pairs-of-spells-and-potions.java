import java.util.Arrays;

class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        
        Arrays.sort(potions);
        
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            long minPotionStrength = (success + spells[i] - 1) / spells[i];
            
            int left = 0;
            int right = m;
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (potions[mid] < minPotionStrength) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            
            result[i] = m - left;
        }
        
        return result;
    }
}