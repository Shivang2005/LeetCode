import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int[] getSneakyNumbers(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int num : nums) {
            if (!seen.add(num)) {
                duplicates.add(num);
            }
        }

        return duplicates.stream().mapToInt(Integer::intValue).toArray();
    }
}