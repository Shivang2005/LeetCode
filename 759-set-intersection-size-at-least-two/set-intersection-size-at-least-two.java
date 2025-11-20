class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        java.util.Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(b[0], a[0]);
            }
        });

        int count = 0;
        int p1 = -1;
        int p2 = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > p2) {
                count += 2;
                p1 = end - 1;
                p2 = end;
            } else if (start > p1) {
                count++;
                p1 = p2;
                p2 = end;
            }
        }

        return count;
    }
}