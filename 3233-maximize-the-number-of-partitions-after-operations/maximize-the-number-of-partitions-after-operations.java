class Solution {
    public int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        
        // We'll use memoized DFS with state (index, mask, changed)
        // where mask represents the current set of characters in the partition
        Map<String, Integer> memo = new HashMap<>();
        
        return dfs(s, k, 0, 0, 0, memo);
    }
    
    private int dfs(String s, int k, int index, int mask, int changed, Map<String, Integer> memo) {
        if (index == s.length()) {
            return 1; // Count the last partition
        }
        
        String key = index + "," + mask + "," + changed;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int result = 0;
        int charIndex = s.charAt(index) - 'a';
        
        // Option 1: Use current character as is
        int newMask = mask | (1 << charIndex);
        int distinct = Integer.bitCount(newMask);
        
        if (distinct > k) {
            // Start new partition with current character
            result = Math.max(result, 1 + dfs(s, k, index + 1, 1 << charIndex, changed, memo));
        } else {
            // Continue in current partition
            result = Math.max(result, dfs(s, k, index + 1, newMask, changed, memo));
        }
        
        // Option 2: Change current character (if we haven't used the change yet)
        if (changed == 0) {
            // Try all possible character changes
            for (int newChar = 0; newChar < 26; newChar++) {
                if (newChar == charIndex) continue;
                
                int changedMask = mask | (1 << newChar);
                int changedDistinct = Integer.bitCount(changedMask);
                
                if (changedDistinct > k) {
                    // Start new partition with the changed character
                    result = Math.max(result, 1 + dfs(s, k, index + 1, 1 << newChar, 1, memo));
                } else {
                    // Continue in current partition
                    result = Math.max(result, dfs(s, k, index + 1, changedMask, 1, memo));
                }
            }
        }
        
        memo.put(key, result);
        return result;
    }
}